package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.dto.LoginUser;
import com.exam.entity.SysUser;
import com.exam.mapper.SysPermissionMapper;
import com.exam.mapper.SysRoleMapper;
import com.exam.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security 用户信息加载实现。
 *
 * <p>登录时根据账号查询用户、角色、权限，组装为 {@link LoginUser}。</p>
 *
 * @author exam
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysPermissionMapper sysPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> roles = sysRoleMapper.selectRoleKeysByUserId(user.getId());
        List<String> permissions = sysPermissionMapper.selectPermKeysByUserId(user.getId());
        return new LoginUser(user, roles, permissions);
    }
}
