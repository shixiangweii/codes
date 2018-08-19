package com.sxw.code.lambda.countapple.v5;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-19
 * Time: 20:12
 *
 * @author shixiangweii
 */
@FunctionalInterface
public interface Function<T, R> {
    /**
     * 接口
     *
     * @param t t
     * @return res
     */
    R apply(T t);
}
