package com.exam.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 教师修改题目入参，在创建入参基础上增加题目 id。
 *
 * @author exam
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionUpdateReq extends QuestionCreateReq {

    /** 题目 id */
    @NotNull(message = "题目 id 不能为空")
    private Long id;
}
