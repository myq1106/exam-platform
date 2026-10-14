package com.exam.controller;

import com.exam.dto.req.LoginReq;
import com.exam.dto.resp.LoginResp;
import com.exam.service.AuthService;
import com.exam.util.Result;
import com.exam.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证接口：登录、当前用户信息、退出登录。
 *
 * @author exam
 */
@Tag(name = "认证管理")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 用户登录，返回 JWT token。
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginResp> login(@Validated @RequestBody LoginReq req) {
        return Result.success(authService.login(req));
    }

    /**
     * 获取当前登录用户信息。
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public Result<UserVO> me() {
        return Result.success(authService.getCurrentUser());
    }

    /**
     * 退出登录（JWT 无状态，前端删除本地 token 即可）。
     */
    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result<Void> logout() {
        return Result.success();
    }
}
