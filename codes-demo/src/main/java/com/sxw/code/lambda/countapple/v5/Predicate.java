package com.sxw.code.lambda.countapple.v5;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:53
 *
 * @author shixiangweii
 */
@FunctionalInterface
public interface Predicate<T> {
    /**
     * 谓词
     * 函数式接口就是只定义 "一个" 抽象方法的接口，例如 Comparator 和 Runnable
     * @param t
     * @return
     */
    boolean test(T t);
}