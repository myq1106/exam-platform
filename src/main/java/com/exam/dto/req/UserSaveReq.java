package com.exam.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 用户新增/修改入参。
 *
 * @author exam
 */
@Data
public class UserSaveReq {

    /** 账号 */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /** 密码（新增时必填） */
    private String password;

    /** 真实姓名 */
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    /** 手机号 */
    private String phone;

    /** 状态：1 正常 0 禁用 */
    private Integer status;

    /** 角色 id 集合 */
    private List<Long> roleIds;
}
