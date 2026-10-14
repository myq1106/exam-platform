package com.exam.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Question;
import com.exam.exception.BusinessException;
import com.exam.service.QuestionService;
import com.exam.util.Result;
import com.exam.vo.QuestionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题目接口（演示不同角色的资源访问控制）。
 *
 * @author exam
 */
@Tag(name = "题目管理")
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 分页查询题目（教师、管理员可见）。
     */
    @Operation(summary = "分页查询题目")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/page")
    public Result<Page<QuestionVO>> page(@RequestParam(defaultValue = "1") long pageNum,
                                         @RequestParam(defaultValue = "10") long pageSize,
                                         @RequestParam(required = false) Integer questionType,
                                         @RequestParam(required = false) Integer difficulty) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(questionType != null, Question::getQuestionType, questionType)
                .eq(difficulty != null, Question::getDifficulty, difficulty)
                .orderByDesc(Question::getCreateTime);
        Page<Question> page = questionService.page(new Page<>(pageNum, pageSize), wrapper);

        Page<QuestionVO> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        voPage.setRecords(page.getRecords().stream().map(this::toVO).toList());
        return Result.success(voPage);
    }

    /**
     * 查询题目详情（三种角色均可，需登录）。
     */
    @Operation(summary = "查询题目详情")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping("/{id}")
    public Result<QuestionVO> getById(@PathVariable Long id) {
        Question question = questionService.getById(id);
        if (question == null) {
            throw new BusinessException("题目不存在");
        }
        return Result.success(toVO(question));
    }

    private QuestionVO toVO(Question question) {
        QuestionVO vo = new QuestionVO();
        BeanUtil.copyProperties(question, vo);
        return vo;
    }
}
