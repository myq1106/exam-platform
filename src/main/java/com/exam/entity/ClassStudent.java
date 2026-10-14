package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 班级-学生关联实体，对应表 class_student。
 *
 * @author exam
 */
@Data
@TableName("class_student")
public class ClassStudent implements Serializable {

    /** 主键 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 班级 id */
    private Long classId;

    /** 学生 id（sys_user.id） */
    private Long studentId;
}
