package com.exam.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 题目展示对象（页面展示用）。
 *
 * @author exam
 */
@Data
public class QuestionVO implements Serializable {

    /** 题目 id */
    private Long id;

    /** 题干 */
    private String title;

    /** 题型：1 单选 2 多选 3 简答 */
    private Integer questionType;

    /** 难度 1~5 */
    private Integer difficulty;

    /** 关联知识点 id */
    private Long knowledgeId;

    /** 选项 JSON */
    private String options;

    /** 参考答案/解析 */
    private String standardAnswer;

    /** 分值 */
    private BigDecimal score;

    /** 审核状态：0 待审核 1 已审核 */
    private Integer reviewStatus;

    /** 创建时间 */
    private LocalDateTime createTime;
}
