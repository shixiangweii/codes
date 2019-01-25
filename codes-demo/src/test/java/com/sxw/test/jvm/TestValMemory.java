package com.sxw.test.jvm;

import org.junit.Test;

/**
 * https://www.nowcoder.com/questionTerminal/51123ddacab84a158e121bc5fe3917eb
 * Description:
 * User: shixiangweii
 * Date: 2019-01-24
 * Time: 17:10
 *
 * @author shixiangweii
 */
public class TestValMemory {

    @Test
    public void testMemory() {

    }
}

class A {
    //  a 为成员变量的引用，在堆区
    // “aa”为未经 new 的常量，在常量区
    private String a = "aa";

    public boolean methodB() {
        // b 为局部变量的引用，在栈区
        // “bb”为未经 new 的常量，在常量区
        String b = "bb";
        // c 为局部变量的引用，在栈区
        // “cc”为未经 new 的常量，在常量区
        final String c = "cc";
        return false;
    }
}