package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-角色关联实体，对应表 sys_user_role。
 *
 * @author exam
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    /** 主键 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 用户 id */
    private Long userId;

    /** 角色 id */
    private Long roleId;
}
