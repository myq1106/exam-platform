package com.exam.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录请求入参。
 *
 * @author exam
 */
@Data
public class LoginReq {

    /** 账号 */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;
}
