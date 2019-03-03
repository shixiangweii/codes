package com.sxw.code.oop.nulldemo;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 18:52
 *
 * @author shixiangweii
 */
public class TestNull {

    private static void testMethod() {
        System.out.println("testMethod");
    }

    /**
     * https://www.nowcoder.com/questionTerminal/e252668ee94947bea07edd7300340115
     */
    @Test
    public void testNull() {
        // 吃惊了居然不会报错
        TestNull testNull = null;
        // “不应该”，并不是报错，运行也不会报错
        testNull.testMethod();

        ((TestNull)null).testMethod();
    }
}
