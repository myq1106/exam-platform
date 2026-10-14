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
 * 知识点实体，对应表 knowledge_point。
 *
 * @author exam
 */
@Data
@TableName("knowledge_point")
public class KnowledgePoint implements Serializable {

    /** 知识点 id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 知识点名称 */
    private String name;

    /** 父知识点 id，支持层级 */
    private Long parentId;

    /** 难度 1~5 */
    private Integer difficulty;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
