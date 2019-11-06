package com.sxw.code.util;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-18
 * Time: 22:21
 *
 * @author shixiangweii
 */
public class CodeUtils {
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
     * num是否等于0
     */
    public static boolean eq0(Integer num) {
        return INT_0.equals(num);
    }

    /**
     * num 不是null && num > 0
     * num大于0
     * num是否 >0
     * num != null && num > 0
     *
     * @param num
     * @return num为null，返回false
     */
    public static boolean gt0(Integer num) {
        return num != null && num > 0;
    }

    /**
     * @param num
     * @return num不为空，并且大于0，true
     */
    public static boolean gt0(Double num) {
        return num != null && num > 0;
    }

    public static boolean gt0(Float num) {
        return num != null && num > 0;
    }

    public static boolean gt0(Short num) {
        return num != null && num > 0;
    }

    public static boolean gt0(BigDecimal num) {
        return num != null && num.compareTo(new BigDecimal("0")) > 0;
    }

    public static int getSize(Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public static boolean yes(Integer n) {
        return n != null && INT_1.equals(n);
    }

    public static boolean yes(Boolean b) {
        return b != null && b;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

}
