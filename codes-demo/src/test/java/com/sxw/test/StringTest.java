package com.sxw.test;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 11:21
 *
 * @author shixiangweii
 */
public class StringTest {

    @Test
    public void testStringEqual() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;

        // false,s1字面量，在常量池中，s2是new出来的，在堆中，引用不一样
        System.out.println(s1 == s2);

        // true s1常量池，s5,字面量拼接，常量池
        System.out.println(s1 == s5);

        // false s1常量池，s6 相当于StringBuffer拼接后toString
        System.out.println(s1 == s6);

        // true s1常量池，s6获取常量池中的版本，因为常量池中有s1，所以true
        System.out.println(s1 == s6.intern());

        // false 相当于 s2 == s1 的比较结果
        System.out.println(s2 == s2.intern());
    }
}
