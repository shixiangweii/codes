package com.sxw.code.lambda.countapple.v4;

import com.sxw.code.lambda.countapple.Apple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:28
 *
 * @author shixiangweii
 */
public class AppleFilter {
    /**
     * 行为参数化
     *
     * @param inventory
     * @param p         行为——“谓词”,筛选的集合的逻辑提取出来作为参数
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            // 仅仅传递代码，具体在filterApples再把具体的上下文参数传入到ApplePredicate
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * filterApples 方法的行为取决于你通过 ApplePredicate
     * 象传递的代码。换句话说，你把 filterApples 方法的行为参数化了
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
         * 现在的问题式XXXXXPredicate的类太多了，不同的规则需要建立很多的类，而且这些类只在这里有用到
         * 只是这里用下就要新建一个.java文件，很烦
         * 而且每次传入参数的时候还要 new XXXXXXPredicate()很烦
         * 这真是很
         * 啰嗦，很费时间
         * 需定义多个 ApplePredicate
         * 类，从而去掉不必要的代码
         */
        filterApples(Collections.emptyList(), new AppleRedAndHeavyPredicate());

        // 匿名类(其实到能做到“匿名类”这一步就差不多了，就很可以了，至少在jdk7中已经式做的很好了)
        List<Apple> redApples = filterApples(Collections.emptyList(), new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        // lambda
        redApples = filterApples(Collections.emptyList(), apple -> "red".equals(apple.getColor()));

        // 其实无论是lambda还是匿名类，对于之前自己的想法：颜色，重量，形状，接口就一个参数Apple apple真的够吗？
        // 自己还是想的是，在接口参数列表中定义用户筛选的数值————>自己的理解还是不对
        // 既然都使用匿名类，使用lambda了，那具体的筛选逻辑都分装在lambda表达式里面了，都在匿名类里面了
        // 所以一个Apple apple作为参数已经够了————>自己还是理解的不到位，没有想过可以把那种比较值的逻辑分装到内部，而不是
        // 写在参数列表里面，这个比较的参数本身就是比较的逻辑中的一部分，本来就没必要放在参数列表里面
        // 由此————>很多别人用的很6的功能，为什么到自己这里，自己感觉好像没什么卵用，就是因为自己理解的还是不深刻，就类似上
        // 面的参数列表的问题，自己想的还是太麻烦了，也就是自己的思维还是没有达到使用这个工具、语法应该有的思维的抽象的层次
    }

}
