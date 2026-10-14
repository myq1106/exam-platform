package com.exam.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 题目实体，对应表 question。支持单选、多选、简答。
 *
 * @author exam
 */
@Data
@TableName("question")
public class Question implements Serializable {

    /** 题目 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 题干 */
    private String title;

    /** 题型：1 单选 2 多选 3 简答 */
    private Integer questionType;

    /** 难度 1~5 */
    private Integer difficulty;

    /** 关联知识点 id */
    private Long knowledgeId;

    /** 选项 JSON，客观题使用 */
    private String options;

    /** 参考答案，简答为参考解析 */
    private String standardAnswer;

    /** 题目分值 */
    private BigDecimal score;

    /** 审核状态：0 待审核 1 已审核 */
    private Integer reviewStatus;

    /** 创建教师 id */
    private Long creatorId;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
