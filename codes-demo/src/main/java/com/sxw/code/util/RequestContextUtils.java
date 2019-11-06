package com.sxw.code.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-24
 * Time: 17:09
 *
 * @author shixiangweii
 */
public class RequestContextUtils {
    /**
     * 返回servlet request上下文
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
