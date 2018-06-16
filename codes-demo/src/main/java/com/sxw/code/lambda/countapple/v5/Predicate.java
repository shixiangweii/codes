package com.sxw.code.lambda.countapple.v5;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:53
 *
 * @author shixiangweii
 */
public interface Predicate<T> {
    /**
     * 谓词
     * @param t
     * @return
     */
    boolean test(T t);
}