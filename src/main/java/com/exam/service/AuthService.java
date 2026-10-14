package com.exam.service;

import com.exam.dto.req.LoginReq;
import com.exam.dto.resp.LoginResp;
import com.exam.vo.UserVO;

/**
 * 认证 Service：登录、当前用户信息。
 *
 * @author exam
 */
public interface AuthService {

    /**
     * 登录：校验账号密码，签发 JWT。
     *
     * @param req 登录入参
     * @return 登录结果（token + 用户信息）
     */
    LoginResp login(LoginReq req);

    /**
     * 获取当前登录用户信息。
     */
    UserVO getCurrentUser();
}
