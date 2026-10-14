package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.SysPermission;
import com.exam.mapper.SysPermissionMapper;
import com.exam.service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限 Service 实现。
 *
 * @author exam
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
}
