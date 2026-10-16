package com.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.dto.req.QuestionCreateReq;
import com.exam.dto.req.QuestionPageReq;
import com.exam.dto.req.QuestionUpdateReq;
import com.exam.entity.Question;
import com.exam.vo.QuestionVO;

/**
 * 题目 Service。
 *
 * @author exam
 */
public interface QuestionService extends IService<Question> {

    /**
     * 教师创建题目，返回题目展示对象。
     */
    QuestionVO createQuestion(QuestionCreateReq req);

    /**
     * 分页查询题目（动态条件 + 关联知识点名称）。
     */
    Page<QuestionVO> pageQuestions(QuestionPageReq req);

    /**
     * 查询题目详情。
     */
    QuestionVO getQuestion(Long id);

    /**
     * 修改题目（仅创建者或管理员），修改后重置为待审核。
     */
    QuestionVO updateQuestion(QuestionUpdateReq req);

    /**
     * 删除题目（仅创建者或管理员）。
     */
    void deleteQuestion(Long id);
}
