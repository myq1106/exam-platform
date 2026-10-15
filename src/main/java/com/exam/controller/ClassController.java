package com.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.req.ClassCreateReq;
import com.exam.dto.req.ClassJoinReq;
import com.exam.service.ClassInfoService;
import com.exam.util.Result;
import com.exam.vo.ClassStudentVO;
import com.exam.vo.ClassVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 班级管理接口。
 *
 * @author exam
 */
@Tag(name = "班级管理")
@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassInfoService classInfoService;

    /**
     * 教师创建班级。
     */
    @Operation(summary = "教师创建班级")
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/create")
    public Result<ClassVO> create(@Validated @RequestBody ClassCreateReq req) {
        return Result.success(classInfoService.createClass(req));
    }

    /**
     * 教师分页查询自己创建的班级。
     */
    @Operation(summary = "教师分页查询自己的班级")
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/page")
    public Result<Page<ClassVO>> page(@RequestParam(defaultValue = "1") long pageNum,
                                      @RequestParam(defaultValue = "10") long pageSize) {
        return Result.success(classInfoService.pageMyClasses(pageNum, pageSize));
    }

    /**
     * 教师查看班级学生列表。
     */
    @Operation(summary = "教师查看班级学生列表")
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/{classId}/students")
    public Result<List<ClassStudentVO>> students(@PathVariable Long classId) {
        return Result.success(classInfoService.listStudents(classId));
    }

    /**
     * 学生通过邀请码加入班级。
     */
    @Operation(summary = "学生加入班级")
    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/join")
    public Result<Void> join(@Validated @RequestBody ClassJoinReq req) {
        classInfoService.joinClass(req);
        return Result.success();
    }
}
