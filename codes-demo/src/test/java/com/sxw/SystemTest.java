package com.sxw;

import org.junit.Test;

import java.util.Random;

/**
 * @author shixiangweii
 * @date 2019/7/28 7:42
 */
public class SystemTest {

    @Test
    public void test() {
        // 896771195836000
        // 1017697681799700
        long x = System.nanoTime();
        System.out.println(x);
        int i = new Random(x).nextInt(10000) + 10000;
        System.out.println(x + "" + i);
    }
}
