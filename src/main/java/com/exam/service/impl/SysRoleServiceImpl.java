package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.SysRole;
import com.exam.mapper.SysRoleMapper;
import com.exam.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色 Service 实现。
 *
 * @author exam
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
