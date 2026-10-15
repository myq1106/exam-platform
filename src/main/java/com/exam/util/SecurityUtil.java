package com.exam.util;

import com.exam.dto.LoginUser;
import com.exam.exception.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全上下文工具类：获取当前登录用户。
 *
 * @author exam
 */
public final class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * 获取当前登录用户，未登录时抛出 401 业务异常。
     */
    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof LoginUser loginUser) {
            return loginUser;
        }
        throw new BusinessException(Result.CODE_UNAUTHORIZED, "未登录或登录已过期");
    }

    /**
     * 获取当前登录用户 id。
     */
    public static Long getCurrentUserId() {
        return getLoginUser().getUserId();
    }
}
