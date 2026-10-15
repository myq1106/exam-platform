package com.exam.dto;

import com.exam.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录用户主体：实现 {@link UserDetails}，作为 Spring Security 的认证主体。
 *
 * <p>承载用户实体 + 角色标识 + 权限标识。</p>
 *
 * @author exam
 */
@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {

    /** 用户实体 */
    private SysUser user;

    /** 角色标识集合，如 ["ADMIN"] */
    private List<String> roles;

    /** 权限标识集合，如 ["question:list"] */
    private List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        }
        if (permissions != null) {
            permissions.forEach(perm -> authorities.add(new SimpleGrantedAuthority(perm)));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 获取当前登录用户 id（委托给 {@link SysUser}）。
     */
    public Long getUserId() {
        return user.getId();
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() != null && user.getStatus() == 1;
    }
}
