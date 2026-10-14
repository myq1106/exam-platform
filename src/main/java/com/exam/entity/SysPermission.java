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
 * 权限实体，对应表 sys_permission。
 *
 * @author exam
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {

    /** 权限 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 权限名称 */
    private String permName;

    /** 权限标识，如 question:list */
    private String permKey;

    /** 类型：1 菜单 2 接口 */
    private Integer type;

    /** 父权限 id */
    private Long parentId;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
