package com.exam.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Question;
import com.exam.vo.QuestionVO;
import org.apache.ibatis.annotations.Param;

/**
 * 题目 Mapper。
 *
 * @author exam
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 分页查询题目，LEFT JOIN knowledge_point 获取知识点名称。
     *
     * @param page    分页参数
     * @param wrapper 动态查询条件（LambdaQueryWrapper）
     * @return 分页题目展示对象
     */
    IPage<QuestionVO> selectQuestionPage(Page<QuestionVO> page, @Param(Constants.WRAPPER) Wrapper<Question> wrapper);
}
