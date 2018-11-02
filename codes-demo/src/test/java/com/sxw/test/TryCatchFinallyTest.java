package com.sxw.test;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 15:32
 *
 * @author shixiangweii
 */
public class TryCatchFinallyTest {

    public static String func() {
        String name = "";
        try {
            name = "try";
            return name;
        } finally {
            name = "finally";
        }
    }

    @Test
    public void test() {
        // 返回值还是"try"
        System.out.println(func());
    }
}
