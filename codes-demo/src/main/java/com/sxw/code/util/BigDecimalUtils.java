package com.sxw.code.util;

import java.math.BigDecimal;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-04-20
 * Time: 16:56
 *
 * @author shixiangweii
 */
public class BigDecimalUtils {
    /**
     * 四舍五入返回整数
     *
     * @param v value
     * @return int值
     */
    public static int getInt(BigDecimal v) {
        return get(v, 0).intValue();
    }

    /**
     * 保留2位小数
     *
     * @param v value
     * @return 小数值
     */
    public static double getDouble(BigDecimal v) {
        return get(v).doubleValue();
    }

    /**
     * 默认设置2位小数精度
     *
     * @param v value
     * @return 2位精度数据
     */
    public static BigDecimal get(BigDecimal v) {
        return get(v, 2);
    }

    /**
     * 设置精度
     *
     * @param v     值
     * @param scale 精度位数
     * @return 设置精度后的值
     */
    public static BigDecimal get(BigDecimal v, int scale) {
        return v.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
}
