package com.sxw.code.lambda.countapple.v3;

import com.sxw.code.lambda.countapple.Apple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:12
 *
 * @author shixiangweii
 */
public class AppleFilter {
    /**
     * 重用按照颜色筛选、按照重量筛选的代码
     * 一种把所有属性结合起来的笨拙尝试
     * @param inventory
     * @param color
     * @param weight
     * @param flag true按照颜色筛选；false按照重量筛选
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            boolean isTarget = (flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight);
            if (isTarget) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 客户端代码看上去糟透了。 true 和 false 是什么意思
     * 这个解决方案还是不能很好地应对变化的需求
     * 对更多的不同属性？
     * 组合属性，做更复杂的查询？
     * 多个重复的 filter 方法，或一个巨大的非常复杂的
     * 方法
     * @param args
     */
    public static void main(String[] args) {
        filterApples(Collections.emptyList(), "green", 0, true);
        filterApples(Collections.emptyList(), "", 100, false);
    }
}
