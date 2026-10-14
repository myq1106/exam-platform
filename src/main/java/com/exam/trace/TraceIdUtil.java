package com.exam.trace;

import org.slf4j.MDC;

/**
 * 链路追踪 id 工具类。
 *
 * <p>配合 {@link TraceIdFilter} 使用，从 MDC 中获取当前请求的 traceId。</p>
 *
 * @author exam
 */
public final class TraceIdUtil {

    /** MDC key */
    public static final String TRACE_ID = "traceId";

    private TraceIdUtil() {
    }

    /**
     * 获取当前线程的 traceId，未设置时返回 null。
     */
    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }
}
