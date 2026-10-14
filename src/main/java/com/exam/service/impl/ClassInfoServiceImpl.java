package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.ClassInfo;
import com.exam.mapper.ClassInfoMapper;
import com.exam.service.ClassInfoService;
import org.springframework.stereotype.Service;

/**
 * 班级信息 Service 实现。
 *
 * @author exam
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements ClassInfoService {
}
