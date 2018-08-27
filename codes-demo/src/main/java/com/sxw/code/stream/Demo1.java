package com.sxw.code.stream;

import com.sxw.code.stream.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-19
 * Time: 22:54
 *
 * @author shixiangweii
 */
public class Demo1 {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        // 数据源menu + 中间操作链sorted,filter + 终端操作collect
        List<String> lowCaloricDishesName =
                menu.stream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());
        System.out.println(lowCaloricDishesName);

        // 并行流
        List<String> lowCaloricDishesName2 =
                menu.parallelStream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());
        System.out.println(lowCaloricDishesName2);

        // 分组
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        // map-reduce
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        System.out.println(count);

        // 内置 count 方法可用来计算流中元素的个数
        // System.out.println(menu.stream().count());


    }
}
