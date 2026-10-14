package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.SysRolePermission;
import com.exam.mapper.SysRolePermissionMapper;
import com.exam.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色-权限关联 Service 实现。
 *
 * @author exam
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {
}
