package com.sxw.code.oop.order;

/**
 * 不要有，那种JVM加载处理static字段的时候，不能调用构造函数，构造函数只能static处理完后才能被使用的，误解！
 * 就把他看成一个方法，函数
 * 静态成员字段初始化，就调用这个类的，构造块、构造方法，
 *
 *
 */
public class StaticFieldBlockOrder {
    public static StaticFieldBlockOrder t1 = new StaticFieldBlockOrder();

    {
        System.out.println("blockA");
    }

    static {
        System.out.println("blockB");
    }

    public static void main(String[] args) {
        StaticFieldBlockOrder t2 = new StaticFieldBlockOrder();
    }
}