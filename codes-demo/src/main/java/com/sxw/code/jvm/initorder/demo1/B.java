package com.sxw.code.jvm.initorder.demo1;

/**
 * https://www.nowcoder.com/questionTerminal/ab6eb06face84c4e81ab5bc6f0f7f258
 * <p>
 * 静态域：分为静态变量，静态方法，静态块。这里面涉及到的是静态变量和静态块，当执行到静态域时，按照静态域的顺序加载
 * <p>
 * 静态域只初始化一次，按照顺序
 */
public class B {

    static {
        System.out.println("静态块1");
    }

    {
        System.out.println("构造块1");
    }

    public static B t1 = new B();
    public static B t2 = new B();

    {
        System.out.println("构造块2");
    }

    static {
        System.out.println("静态块2");
    }

    public static void main(String[] args) {
        B t = new B();
    }
}