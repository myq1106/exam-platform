package com.exam.exception;

import com.exam.util.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器。
 *
 * <p>统一捕获各类异常，返回 {@link Result} 结构，避免异常信息直接暴露给前端。</p>
 *
 * @author exam
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常。
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 无权限访问（方法级 @PreAuthorize 抛出）。
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("无权限访问: {}", e.getMessage());
        return Result.fail(Result.CODE_FORBIDDEN, "无权限访问该资源");
    }

    /**
     * 请求体参数校验失败（@RequestBody + @Validated）。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = extractFieldError(e.getBindingResult().getFieldError());
        log.warn("参数校验失败: {}", message);
        return Result.fail(400, message);
    }

    /**
     * 表单参数绑定校验失败（@ModelAttribute）。
     */
    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e) {
        String message = extractFieldError(e.getBindingResult().getFieldError());
        log.warn("参数绑定失败: {}", message);
        return Result.fail(400, message);
    }

    /**
     * 方法级参数校验失败（@RequestParam/@PathVariable + @Validated）。
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolation(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .findFirst()
                .map(v -> v.getMessage())
                .orElse("参数校验失败");
        log.warn("参数校验失败: {}", message);
        return Result.fail(400, message);
    }

    /**
     * 登录账号或密码错误。
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<Void> handleBadCredentials(BadCredentialsException e) {
        log.warn("登录失败: 用户名或密码错误");
        return Result.fail(Result.CODE_UNAUTHORIZED, "用户名或密码错误");
    }

    /**
     * 账号被禁用。
     */
    @ExceptionHandler(DisabledException.class)
    public Result<Void> handleDisabled(DisabledException e) {
        log.warn("登录失败: 账号被禁用");
        return Result.fail(Result.CODE_UNAUTHORIZED, "账号已被禁用");
    }

    /**
     * 兜底异常。
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.fail(Result.CODE_ERROR, "系统繁忙，请稍后重试");
    }

    private String extractFieldError(FieldError fieldError) {
        if (fieldError == null) {
            return "参数校验失败";
        }
        return fieldError.getDefaultMessage();
    }
}
