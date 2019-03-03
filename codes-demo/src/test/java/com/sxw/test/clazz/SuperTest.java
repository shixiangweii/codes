package com.sxw.test.clazz;

import java.util.Date;

/**
 * https://www.nowcoder.com/questionTerminal/35a08d98faba40e6a8946fcb88c6c091
 */
public class SuperTest extends Date {
    private static final long serialVersionUID = 1L;

    private void test() {
        /**
         * 链接：https://www.nowcoder.com/questionTerminal/35a08d98faba40e6a8946fcb88c6c091
         来源：牛客网

         TestSuper和Date的getClass都没有重写，他们都是调用Object的getClass，而Object的getClass作用是返回的是运行时的类的名字。这个运行时的类就是当前类，所以
         super.getClass().getName()
         返回的是test.SuperTest，与Date类无关
         要返回Date类的名字需要写super.getClass().getSuperclass()
         */
        System.out.println(super.getClass().getName());
    }

    public static void main(String[] args) {
        new SuperTest().test();
    }
}