package com.sxw.code.stream.reduce;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Description: 其实就是聚合函数，比如求和，求最大最小值
 * User: shixiangweii
 * Date: 2018-08-27
 * Time: 15:25
 *
 * @author shixiangweii
 */
public class ReduceDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        Optional<Integer> sumOptional = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sumOptional.orElse(0));

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(max.orElse(0) + "," + min.orElse(0));


    }

}
