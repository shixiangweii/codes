package com.sxw.code.util;

import java.math.BigDecimal;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-05-02
 * Time: 13:14
 *
 * @author shixiangweii
 */
public class BigDecimalUtils {
    /**
     * 默认2位小数
     */
    private static final int DEFAULT_SCALE = 2;
    /**
     * 默认四舍五入
     */
    private static final int DEFAULT_ROUND_MODE = BigDecimal.ROUND_HALF_UP;

    public static BigDecimal valueOf(int num) {
        return new BigDecimal(num);
    }

    public static BigDecimal valueOf(long num) {
        return new BigDecimal(num);
    }

    public static BigDecimal valueOf(float num) {
        return new BigDecimal("" + num);
    }

    public static BigDecimal valueOf(double num) {
        return new BigDecimal("" + num);
    }

    public static double getDouble(BigDecimal num) {
        return num.setScale(DEFAULT_SCALE, DEFAULT_ROUND_MODE).doubleValue();
    }

    public static float getFloat(BigDecimal num) {
        return num.setScale(DEFAULT_SCALE, DEFAULT_ROUND_MODE).floatValue();
    }

    public static int getInt(BigDecimal num) {
        return num.setScale(0, DEFAULT_ROUND_MODE).intValue();
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }
}
