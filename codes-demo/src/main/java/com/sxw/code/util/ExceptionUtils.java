package com.sxw.code.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author shixiangweii
 */
public class ExceptionUtils {

    /**
     * 获取异常的堆栈信息
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            return sw.toString();
        }
    }
}