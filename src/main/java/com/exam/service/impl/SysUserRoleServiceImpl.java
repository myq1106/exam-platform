package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.SysUserRole;
import com.exam.mapper.SysUserRoleMapper;
import com.exam.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户-角色关联 Service 实现。
 *
 * @author exam
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
