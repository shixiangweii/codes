package com.sxw.code.util;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-18
 * Time: 22:21
 *
 * @author shixiangweii
 */
public class CodeUtil {
    public static final Integer INT_0 = 0;
    public static final Integer INT_1 = 1;
    public static final Integer INT_2 = 2;
    public static final Integer INT_3 = 3;
    public static final Integer INT_4 = 4;
    public static final Integer INT_5 = 5;
    public static final Integer INT_6 = 6;
    public static final Integer INT_7 = 7;
    public static final Integer INT_8 = 8;
    public static final Integer INT_9 = 9;

    public static final Short SHORT_0 = 0;
    public static final Short SHORT_1 = 1;
    public static final Short SHORT_2 = 2;
    public static final Short SHORT_3 = 3;
    public static final Short SHORT_4 = 4;
    public static final Short SHORT_5 = 5;
    public static final Short SHORT_6 = 6;
    public static final Short SHORT_7 = 7;
    public static final Short SHORT_8 = 8;
    public static final Short SHORT_9 = 9;

    public static final Long LONG_0 = 0L;
    public static final Long LONG_1 = 1L;
    public static final Long LONG_2 = 2L;
    public static final Long LONG_3 = 3L;
    public static final Long LONG_4 = 4L;
    public static final Long LONG_5 = 5L;
    public static final Long LONG_6 = 6L;
    public static final Long LONG_7 = 7L;
    public static final Long LONG_8 = 8L;
    public static final Long LONG_9 = 9L;

    public static final Float FLOAT_0 = 0f;

    public static final Double DOUBLE_0 = 0d;

    /**
     * 等于0
     */
    public static boolean eq0(Integer num) {
        return INT_0.equals(num);
    }

    /**
     * 大于0
     * num是否 >0
     *
     * @param num
     * @return num为null，返回false
     */
    public static boolean gt0(Integer num) {
        return num != null && num > 0;
    }
}
