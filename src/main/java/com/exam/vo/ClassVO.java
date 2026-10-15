package com.exam.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 班级展示对象（页面展示用）。
 *
 * @author exam
 */
@Data
public class ClassVO implements Serializable {

    /** 班级 id */
    private Long id;

    /** 班级名称 */
    private String className;

    /** 任课教师 id */
    private Long teacherId;

    /** 班级描述 */
    private String description;

    /** 邀请码 */
    private String inviteCode;

    /** 状态：1 正常 0 归档 */
    private Integer status;

    /** 创建时间 */
    private LocalDateTime createTime;
}
