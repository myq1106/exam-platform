package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Question;
import com.exam.mapper.QuestionMapper;
import com.exam.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * 题目 Service 实现。
 *
 * @author exam
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
}
