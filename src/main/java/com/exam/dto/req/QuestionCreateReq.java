package com.exam.dto.req;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 教师创建题目入参。
 *
 * @author exam
 */
@Data
public class QuestionCreateReq {

    /** 题干 */
    @NotBlank(message = "题干不能为空")
    private String title;

    /** 题型：1 单选 2 多选 3 简答 */
    @NotNull(message = "题型不能为空")
    @Min(value = 1, message = "题型取值只能为 1/2/3")
    @Max(value = 3, message = "题型取值只能为 1/2/3")
    private Integer questionType;

    /** 难度 1~5 */
    @NotNull(message = "难度不能为空")
    @Min(value = 1, message = "难度取值只能为 1~5")
    @Max(value = 5, message = "难度取值只能为 1~5")
    private Integer difficulty;

    /** 关联知识点 id */
    @NotNull(message = "知识点不能为空")
    private Long knowledgeId;

    /** 选项（客观题使用），每个选项为一个 {key, text} 结构，入库前转为 JSON 字符串 */
    private List<Map<String, Object>> options;

    /** 参考答案：单选/简答为字符串，多选为数组，入库前统一转为 JSON 字符串 */
    @NotNull(message = "参考答案不能为空")
    private Object standardAnswer;

    /** 分值 */
    @NotNull(message = "分值不能为空")
    @DecimalMin(value = "0", message = "分值不能小于 0")
    private BigDecimal score;
}
