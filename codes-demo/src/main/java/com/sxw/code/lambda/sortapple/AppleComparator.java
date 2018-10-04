package com.sxw.code.lambda.sortapple;

import com.sxw.code.lambda.countapple.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 20:38
 *
 * @author shixiangweii
 */
public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("", 1), new Apple("", 2));

        // 第 1 步：传递代码
        inventory.sort(new AppleComparator());

        // 第 2 步：使用匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        // 第 3 步：使用 Lambda 表达式
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //  Comparator 具有一个叫作 comparing 的静态辅助方法
        inventory.sort(comparing((a) -> a.getWeight()));

        // 第 4 步：使用方法引用
        inventory.sort(comparing(Apple::getWeight));

        // lambda复合
        // 比较器复合
        inventory.sort(comparing(Apple::getWeight).reversed());
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        // 谓词复合
        Predicate<Apple> redApple = (a -> "red".equals(a.getColor()));
        // 从简单Lambda表达式出发，你可以构建更复杂的表达式
        // 但是具有可读性
        redApple.negate().and(a -> a.getWeight() > 100).or(a -> "green".equals(a.getColor()));

        // 函数复合
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        // 数学上会写作 g(f(x))
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));

        // 创建一个流水线：先加上
        // 抬头，然后进行拼写检查，最后加上一个落款
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("shixiangweii"));

        // 类似积分的思想？？
        System.out.println(integrate((double x) -> x + 10, 3, 7));

        // 接口默认实现类
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 99, 4);
        nums.sort((a, b) -> a.compareTo(b));

        nums.sort(Integer::compareTo);

        nums.sort(Comparator.comparingInt(value -> {
            return value.intValue();
        }));

        nums.sort(Comparator.comparingInt(Integer::intValue));
    }
}
