package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限关联实体，对应表 sys_role_permission。
 *
 * @author exam
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    /** 主键 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 角色 id */
    private Long roleId;

    /** 权限 id */
    private Long permissionId;
}
