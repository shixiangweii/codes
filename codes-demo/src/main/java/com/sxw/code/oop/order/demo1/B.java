package com.sxw.code.oop.order.demo1;

class A {
    public A() {
        System.out.println("class A");
    }

    {
        System.out.println("I'm A class");
    }

    static {
        System.out.println("class A static");
    }
}

/**
 * https://www.nowcoder.com/questionTerminal/daf2411300b846c29b7d5ee2ead572df
 * 父类静态变量—>父类静态代码块—>
 * 子类静态变量—>子类静态代码块—>
 * 父类非静态变量—>父类非静态代码块—>父类构造方法—
 * >子类非静态变量—>子类非静态代码块—>子类构造方法
 */
public class B extends A {
    public B() {
        System.out.println("class B");
    }

    {
        System.out.println("I'm B class");
    }

    static {
        System.out.println("class B static");
    }

    public static void main(String[] args) {
        new B();
    }
}