package com.exam.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户展示对象（页面展示用，不含密码等敏感字段）。
 *
 * @author exam
 */
@Data
public class UserVO implements Serializable {

    /** 用户 id */
    private Long id;

    /** 账号 */
    private String username;

    /** 真实姓名 */
    private String realName;

    /** 手机号 */
    private String phone;

    /** 状态：1 正常 0 禁用 */
    private Integer status;

    /** 角色标识集合 */
    private List<String> roles;

    /** 权限标识集合 */
    private List<String> permissions;

    /** 创建时间 */
    private LocalDateTime createTime;
}
