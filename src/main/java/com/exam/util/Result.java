package com.exam.util;

import com.exam.trace.TraceIdUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局统一返回结果封装。
 *
 * <p>所有 controller 统一返回 {@link Result}，前端通过 code 判断业务是否成功。</p>
 *
 * @param <T> 数据类型
 * @author exam
 */
@Data
public class Result<T> implements Serializable {

    /** 成功 */
    public static final int CODE_SUCCESS = 200;
    /** 未认证 */
    public static final int CODE_UNAUTHORIZED = 401;
    /** 无权限 */
    public static final int CODE_FORBIDDEN = 403;
    /** 系统异常 */
    public static final int CODE_ERROR = 500;

    /** 状态码 */
    private Integer code;
    /** 提示信息 */
    private String message;
    /** 返回数据 */
    private T data;
    /** 链路追踪 id */
    private String traceId;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(CODE_SUCCESS);
        result.setMessage("操作成功");
        result.setData(data);
        result.setTraceId(TraceIdUtil.getTraceId());
        return result;
    }

    public static <T> Result<T> fail(String message) {
        return fail(CODE_ERROR, message);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setTraceId(TraceIdUtil.getTraceId());
        return result;
    }
}
