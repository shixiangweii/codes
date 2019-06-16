package com.sxw.code.stream.reduce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-05-26
 * Time: 17:02
 *
 * @author shixiangweii
 */
public class ReduceDemo2 {

    public static void main(String[] args) {
        // 这个例子就能很体现规约的思想了
        //  承接规约结果
        // s0 header
        Spec s0 = new Spec(0, 0, 0, 0);
        Spec s1 = new Spec(1, 2, 10, 20);
        Spec s2 = new Spec(2, 2, 20, 40);
        Spec s3 = new Spec(3, 1, 40, 40);
        List<Spec> list = Arrays.asList(s0, s1, s2, s3);
        List<Spec> list2 = Arrays.asList(s0, s1, s2);
        List<Spec> list3 = Collections.singletonList(s0);
        // param.spec看成s0，最后规约的结果
        // 其实就是，f(n) = f(n-1) + An
        // f(0) 就是 s0
        Optional<Spec> reduce = list.stream().reduce((spec, spec2) -> {
            System.out.println(spec + "," + spec2);
            spec.setSum(spec.getSum() + spec2.getSum());
            return spec;
        });
        Spec spec = reduce.orElse(null);
        System.out.println(spec);

        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(spec == s0);
        System.out.println(spec == s1);
        System.out.println(spec == s2);
        System.out.println(spec == s3);
    }
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Spec {
    private Integer specId;
    private Integer num;
    private Integer price;
    private Integer sum;
}
