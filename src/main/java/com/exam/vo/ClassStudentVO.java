package com.exam.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 班级学生展示对象（JOIN sys_user 后的学生信息）。
 *
 * @author exam
 */
@Data
public class ClassStudentVO implements Serializable {

    /** 关联记录 id（class_student.id） */
    private Long id;

    /** 班级 id */
    private Long classId;

    /** 学生 id（sys_user.id） */
    private Long studentId;

    /** 账号 */
    private String username;

    /** 真实姓名 */
    private String realName;

    /** 手机号 */
    private String phone;
}
