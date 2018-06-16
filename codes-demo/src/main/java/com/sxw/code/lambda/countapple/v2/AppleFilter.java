package com.sxw.code.lambda.countapple.v2;

import com.sxw.code.lambda.countapple.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:02
 *
 * @author shixiangweii
 */
public class AppleFilter {
    /**
     * 把颜色变成参数，按照传入的颜色筛选苹果
     *
     * @param color     颜色
     * @param inventory 库存
     * @return 特定颜色的苹果
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 重量作为参数，筛选合适重量的苹果 DRY（Don’t Repeat Yourself，不要重复自己）
     *
     * @param inventory 库存
     * @param weight    重量
     * @return 符合重量限制的苹果
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }
}
