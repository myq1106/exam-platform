package com.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.dto.LoginUser;
import com.exam.dto.req.QuestionCreateReq;
import com.exam.dto.req.QuestionPageReq;
import com.exam.dto.req.QuestionUpdateReq;
import com.exam.entity.KnowledgePoint;
import com.exam.entity.Question;
import com.exam.exception.BusinessException;
import com.exam.mapper.QuestionMapper;
import com.exam.service.KnowledgePointService;
import com.exam.service.QuestionService;
import com.exam.util.Result;
import com.exam.util.SecurityUtil;
import com.exam.vo.QuestionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 题目 Service 实现。
 *
 * @author exam
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    private final QuestionMapper questionMapper;
    private final KnowledgePointService knowledgePointService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionVO createQuestion(QuestionCreateReq req) {
        Question question = new Question();
        question.setTitle(req.getTitle());
        question.setQuestionType(req.getQuestionType());
        question.setDifficulty(req.getDifficulty());
        question.setKnowledgeId(req.getKnowledgeId());
        question.setOptions(toJsonString(req.getOptions()));
        question.setStandardAnswer(toJsonString(req.getStandardAnswer()));
        question.setScore(req.getScore());
        question.setReviewStatus(0);
        question.setCreatorId(SecurityUtil.getCurrentUserId());
        save(question);
        return toVO(question);
    }

    @Override
    public Page<QuestionVO> pageQuestions(QuestionPageReq req) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<Question>()
                .eq(req.getQuestionType() != null, Question::getQuestionType, req.getQuestionType())
                .eq(req.getDifficulty() != null, Question::getDifficulty, req.getDifficulty())
                .eq(req.getKnowledgeId() != null, Question::getKnowledgeId, req.getKnowledgeId())
                .like(StrUtil.isNotBlank(req.getKeyword()), Question::getTitle, req.getKeyword())
                .orderByDesc(Question::getCreateTime);
        Page<QuestionVO> page = new Page<>(req.getPageNum(), req.getPageSize());
        questionMapper.selectQuestionPage(page, wrapper);
        return page;
    }

    @Override
    public QuestionVO getQuestion(Long id) {
        Question question = getById(id);
        if (question == null) {
            throw new BusinessException("题目不存在");
        }
        return toVO(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionVO updateQuestion(QuestionUpdateReq req) {
        Question question = getById(req.getId());
        if (question == null) {
            throw new BusinessException("题目不存在");
        }
        checkOwnerOrAdmin(question);
        question.setTitle(req.getTitle());
        question.setQuestionType(req.getQuestionType());
        question.setDifficulty(req.getDifficulty());
        question.setKnowledgeId(req.getKnowledgeId());
        question.setOptions(toJsonString(req.getOptions()));
        question.setStandardAnswer(toJsonString(req.getStandardAnswer()));
        question.setScore(req.getScore());
        question.setReviewStatus(0);
        updateById(question);
        return toVO(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestion(Long id) {
        Question question = getById(id);
        if (question == null) {
            throw new BusinessException("题目不存在");
        }
        checkOwnerOrAdmin(question);
        // question 表无 deleted 字段，使用物理删除
        removeById(id);
    }

    /**
     * 校验当前登录用户是否为题目创建者或管理员。
     */
    private void checkOwnerOrAdmin(Question question) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        boolean isAdmin = loginUser.getRoles() != null && loginUser.getRoles().contains("ADMIN");
        if (!question.getCreatorId().equals(loginUser.getUserId()) && !isAdmin) {
            throw new BusinessException(Result.CODE_FORBIDDEN, "仅创建者或管理员可操作该题目");
        }
    }

    /**
     * 将 options / standardAnswer 统一转为 JSON 字符串入库：
     * 字符串（单选答案、简答解析）原样入库；List/Map（多选答案、选项）序列化为 JSON。
     */
    private String toJsonString(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String str) {
            return str;
        }
        return JSONUtil.toJsonStr(value);
    }

    /**
     * entity -> vo，并补充知识点名称。
     */
    private QuestionVO toVO(Question question) {
        QuestionVO vo = new QuestionVO();
        BeanUtil.copyProperties(question, vo);
        KnowledgePoint knowledgePoint = knowledgePointService.getById(question.getKnowledgeId());
        if (knowledgePoint != null) {
            vo.setKnowledgeName(knowledgePoint.getName());
        }
        return vo;
    }
}
