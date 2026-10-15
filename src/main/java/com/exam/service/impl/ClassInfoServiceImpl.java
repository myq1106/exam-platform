package com.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.dto.req.ClassCreateReq;
import com.exam.dto.req.ClassJoinReq;
import com.exam.entity.ClassInfo;
import com.exam.entity.ClassStudent;
import com.exam.exception.BusinessException;
import com.exam.mapper.ClassInfoMapper;
import com.exam.mapper.ClassStudentMapper;
import com.exam.service.ClassInfoService;
import com.exam.util.SecurityUtil;
import com.exam.vo.ClassStudentVO;
import com.exam.vo.ClassVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 班级信息 Service 实现。
 *
 * @author exam
 */
@Service
@RequiredArgsConstructor
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements ClassInfoService {

    /** 邀请码生成最大重试次数 */
    private static final int INVITE_CODE_MAX_RETRY = 5;

    private final ClassStudentMapper classStudentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClassVO createClass(ClassCreateReq req) {
        Long teacherId = SecurityUtil.getCurrentUserId();
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName(req.getClassName());
        classInfo.setDescription(req.getDescription());
        classInfo.setTeacherId(teacherId);
        classInfo.setStatus(1);
        classInfo.setInviteCode(generateInviteCode());
        save(classInfo);
        return toVO(classInfo);
    }

    @Override
    public Page<ClassVO> pageMyClasses(long pageNum, long pageSize) {
        Long teacherId = SecurityUtil.getCurrentUserId();
        LambdaQueryWrapper<ClassInfo> wrapper = new LambdaQueryWrapper<ClassInfo>()
                .eq(ClassInfo::getTeacherId, teacherId)
                .orderByDesc(ClassInfo::getCreateTime);
        Page<ClassInfo> page = page(new Page<>(pageNum, pageSize), wrapper);

        Page<ClassVO> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        voPage.setRecords(page.getRecords().stream().map(this::toVO).toList());
        return voPage;
    }

    @Override
    public List<ClassStudentVO> listStudents(Long classId) {
        return classStudentMapper.selectStudentsByClassId(classId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinClass(ClassJoinReq req) {
        Long studentId = SecurityUtil.getCurrentUserId();
        ClassInfo classInfo = getOne(new LambdaQueryWrapper<ClassInfo>()
                .eq(ClassInfo::getInviteCode, req.getInviteCode()), false);
        if (classInfo == null) {
            throw new BusinessException("邀请码无效或班级不存在");
        }

        boolean exists = classStudentMapper.exists(new LambdaQueryWrapper<ClassStudent>()
                .eq(ClassStudent::getClassId, classInfo.getId())
                .eq(ClassStudent::getStudentId, studentId));
        if (exists) {
            return;
        }

        ClassStudent classStudent = new ClassStudent();
        classStudent.setClassId(classInfo.getId());
        classStudent.setStudentId(studentId);
        classStudentMapper.insert(classStudent);
    }

    /**
     * 生成全局唯一邀请码，最多重试 {@link #INVITE_CODE_MAX_RETRY} 次。
     */
    private String generateInviteCode() {
        for (int i = 0; i < INVITE_CODE_MAX_RETRY; i++) {
            String code = RandomUtil.randomString(6);
            long existingCount = count(new LambdaQueryWrapper<ClassInfo>().eq(ClassInfo::getInviteCode, code));
            if (existingCount == 0) {
                return code;
            }
        }
        throw new BusinessException("邀请码生成失败，请稍后重试");
    }

    /**
     * entity -> vo，避免直接返回 entity。
     */
    private ClassVO toVO(ClassInfo classInfo) {
        ClassVO vo = new ClassVO();
        BeanUtil.copyProperties(classInfo, vo);
        return vo;
    }
}
