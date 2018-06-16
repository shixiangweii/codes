package com.sxw.code.lambda.countapple.v5;

import com.sxw.code.lambda.countapple.Apple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:59
 *
 * @author shixiangweii
 */
public class Filter {
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * 4个有用的东西：
     * 泛型，OOP，匿名类/lambda函数式编程，AOP(反射、Proxy)
     * @param args
     */
    public static void main(String[] args) {

        filter(Collections.emptyList(), (Apple apple) -> "red".equals(apple.getColor()));

        filter(Collections.emptyList(), (Integer i) -> i % 2 == 0);
    }
}
