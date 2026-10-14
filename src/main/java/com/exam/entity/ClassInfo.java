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
 * 班级信息实体，对应表 class_info。
 *
 * @author exam
 */
@Data
@TableName("class_info")
public class ClassInfo implements Serializable {

    /** 班级 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 班级名称 */
    private String className;

    /** 任课教师 id（sys_user.id） */
    private Long teacherId;

    /** 班级描述 */
    private String description;

    /** 状态：1 正常 0 归档 */
    private Integer status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
