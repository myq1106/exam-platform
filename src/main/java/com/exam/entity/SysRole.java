package com.exam.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体，对应表 sys_role。
 *
 * @author exam
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {

    /** 角色 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 角色名称 */
    private String roleName;

    /** 角色标识：ADMIN/TEACHER/STUDENT */
    private String roleKey;

    /** 状态：1 启用 0 禁用 */
    private Integer status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
