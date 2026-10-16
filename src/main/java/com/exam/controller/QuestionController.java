package com.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.req.QuestionCreateReq;
import com.exam.dto.req.QuestionPageReq;
import com.exam.dto.req.QuestionUpdateReq;
import com.exam.service.QuestionService;
import com.exam.util.Result;
import com.exam.vo.QuestionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题库管理接口。
 *
 * @author exam
 */
@Tag(name = "题库管理")
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Validated
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 教师创建题目。
     */
    @Operation(summary = "教师创建题目")
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/create")
    public Result<QuestionVO> create(@Validated @RequestBody QuestionCreateReq req) {
        return Result.success(questionService.createQuestion(req));
    }

    /**
     * 分页查询题目（登录即可）。
     */
    @Operation(summary = "分页查询题目")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/page")
    public Result<Page<QuestionVO>> page(@Valid QuestionPageReq req) {
        return Result.success(questionService.pageQuestions(req));
    }

    /**
     * 查看题目详情（登录即可）。
     */
    @Operation(summary = "查看题目详情")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public Result<QuestionVO> getById(@PathVariable Long id) {
        return Result.success(questionService.getQuestion(id));
    }

    /**
     * 修改题目（仅创建者或管理员）。
     */
    @Operation(summary = "修改题目")
    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/update")
    public Result<QuestionVO> update(@Validated @RequestBody QuestionUpdateReq req) {
        return Result.success(questionService.updateQuestion(req));
    }

    /**
     * 删除题目（仅创建者或管理员）。
     */
    @Operation(summary = "删除题目")
    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return Result.success();
    }
}
