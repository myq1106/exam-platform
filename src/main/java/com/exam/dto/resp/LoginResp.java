package com.exam.dto.resp;

import com.exam.vo.UserVO;
import lombok.Data;

/**
 * 登录响应出参：token + 用户信息。
 *
 * @author exam
 */
@Data
public class LoginResp {

    /** JWT token */
    private String token;

    /** 用户信息 */
    private UserVO user;
}
