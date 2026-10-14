package com.exam.service.impl;

import com.exam.dto.LoginUser;
import com.exam.dto.req.LoginReq;
import com.exam.dto.resp.LoginResp;
import com.exam.entity.SysUser;
import com.exam.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link AuthServiceImpl} 单元测试（Mockito）。
 *
 * @author exam
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void login_shouldReturnTokenAndUser() {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("teacher1");
        user.setPassword("encoded-password");

        LoginUser loginUser = new LoginUser(user, List.of("TEACHER"), List.of("question:list"));

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(loginUser);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtil.generateToken("teacher1", 1L, List.of("TEACHER"), List.of("question:list")))
                .thenReturn("mock-token");

        LoginReq req = new LoginReq();
        req.setUsername("teacher1");
        req.setPassword("123456");

        LoginResp resp = authService.login(req);

        assertNotNull(resp);
        assertEquals("mock-token", resp.getToken());
        assertEquals("teacher1", resp.getUser().getUsername());
        verify(authenticationManager).authenticate(any());
    }
}
