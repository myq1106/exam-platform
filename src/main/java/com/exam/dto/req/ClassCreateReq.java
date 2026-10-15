package com.exam.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 教师创建班级入参。
 *
 * @author exam
 */
@Data
public class ClassCreateReq {

    /** 班级名称 */
    @NotBlank(message = "班级名称不能为空")
    private String className;

    /** 班级描述 */
    private String description;
}
