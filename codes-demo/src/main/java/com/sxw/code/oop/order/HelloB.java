package com.sxw.code.oop.order;

/**
 *   1.静态代码块 2.构造代码块3.构造方法
 *   执行顺序是1>2>3
 *   1.静态代码块：是在类的加载过程的第三步初始化的时候进行的，主要目的是给类变量赋予初始值
 *   2.构造代码块：是独立的，必须依附载体才能运行，Java会把构造代码块放到每种构造方法的前面，用于实例化一些共有的实例变量，减少代码量。
 *   3.构造方法：用于实例化变量
 *   1是类级别的，2、3是实例级别的，自然1要优先23.
 *   对子类得主动使用会导致对其父类得主动使用，所以尽管实例化的是子类，但也会导致父类的初始化和实例化，且优于子类执行
 *
 *
 * Java程序初始化工作可以在许多不同的代码块中来完成，它们的执行顺序如下：
 * 父类的静态变量、
 * 父类的静态代码块、
 * 子类的静态变量、
 * 子类的静态代码块、
   父类的非静态变量、
   父类的非静态代码块、
   父类的构造函数、
   子类的非静态变量、
   子类的非静态代码块、
   子类的构造函数。
 *
 *
 * https://www.nowcoder.com/questionTerminal/6f126682f29544708bb73a08e4e2bd0a
 *
 * 父类静态域——》子类静态域——》父类成员初始化——》父类构造块——》1父类构造方法——》2子类成员初始化——》子类构造块——》3子类构造方法；
 *
 * https://www.nowcoder.com/questionTerminal/288fb5d649614bdfa8409080f167c920
 *
 *
 *
 */
public class HelloB extends HelloA {
    public HelloB() {
    }

    {
        // 4、字类构造块
        System.out.println("I’m B class");
    }

    /**
     * 2、子类静态代码块
     */
    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        new HelloB();
    }
}

class HelloA {
    public HelloA() {
    }

    {
        // 3、父类构造块
        System.out.println("I’m A class");
    }
    // 1、父类静态代码块
    static {
        System.out.println("static A");
    }
}
