package com.exam.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 题目分页查询入参。
 *
 * @author exam
 */
@Data
public class QuestionPageReq {

    /** 页码，从 1 开始 */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为 1")
    private Long pageNum = 1L;

    /** 每页条数 */
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小为 1")
    @Max(value = 100, message = "每页条数最大为 100")
    private Long pageSize = 10L;

    /** 题型（可选）：1 单选 2 多选 3 简答 */
    private Integer questionType;

    /** 难度（可选）1~5 */
    private Integer difficulty;

    /** 知识点 id（可选） */
    private Long knowledgeId;

    /** 题干关键字（可选，模糊搜索） */
    private String keyword;
}
