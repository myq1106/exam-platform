package com.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.exam.dto.LoginUser;
import com.exam.dto.req.LoginReq;
import com.exam.dto.resp.LoginResp;
import com.exam.service.AuthService;
import com.exam.util.JwtUtil;
import com.exam.util.SecurityUtil;
import com.exam.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 认证 Service 实现。
 *
 * @author exam
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResp login(LoginReq req) {
        // 交给 Spring Security 认证（内部会调用 UserDetailsServiceImpl + PasswordEncoder）
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 签发 JWT
        String token = jwtUtil.generateToken(loginUser.getUsername(), loginUser.getUser().getId(),
                loginUser.getRoles(), loginUser.getPermissions());

        LoginResp resp = new LoginResp();
        resp.setToken(token);
        resp.setUser(toUserVO(loginUser));
        return resp;
    }

    @Override
    public UserVO getCurrentUser() {
        return toUserVO(SecurityUtil.getLoginUser());
    }

    /**
     * LoginUser -> UserVO，entity 不直接返回前端。
     */
    private UserVO toUserVO(LoginUser loginUser) {
        UserVO vo = new UserVO();
        BeanUtil.copyProperties(loginUser.getUser(), vo);
        vo.setRoles(loginUser.getRoles());
        vo.setPermissions(loginUser.getPermissions());
        return vo;
    }
}
