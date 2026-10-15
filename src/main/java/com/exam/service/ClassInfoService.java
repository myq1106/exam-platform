package com.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.dto.req.ClassCreateReq;
import com.exam.dto.req.ClassJoinReq;
import com.exam.entity.ClassInfo;
import com.exam.vo.ClassStudentVO;
import com.exam.vo.ClassVO;

import java.util.List;

/**
 * 班级信息 Service。
 *
 * @author exam
 */
public interface ClassInfoService extends IService<ClassInfo> {

    /**
     * 教师创建班级，返回班级展示对象。
     */
    ClassVO createClass(ClassCreateReq req);

    /**
     * 教师分页查询自己创建的班级。
     */
    Page<ClassVO> pageMyClasses(long pageNum, long pageSize);

    /**
     * 查询班级下的学生列表（JOIN sys_user）。
     */
    List<ClassStudentVO> listStudents(Long classId);

    /**
     * 学生通过邀请码加入班级。
     */
    void joinClass(ClassJoinReq req);
}
