package com.sxw.code.lambda.countapple.v1;

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
     * 删选绿色的苹果
     *
     * @param inventory 库存
     * @return 绿色的苹果
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}
