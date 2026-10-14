package com.exam.exception;

import com.exam.util.Result;
import lombok.Getter;

/**
 * 业务异常。业务逻辑校验失败时抛出，由全局异常处理器统一捕获返回。
 *
 * @author exam
 */
@Getter
public class BusinessException extends RuntimeException {

    /** 错误码 */
    private final Integer code;

    public BusinessException(String message) {
        this(Result.CODE_ERROR, message);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /** 快捷构建一个系统错误 */
    public static BusinessException of(String message) {
        return new BusinessException(message);
    }
}
