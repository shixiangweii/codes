package com.sxw.code.oop.inherit.demo4;

public class A implements B {
    public static void main(String args[]) {
        int i;
        A a1 = new A();
        // 不应该，不是报错
        i = a1.k;
        // 10
        System.out.println("i=" + i);
    }
}

interface B {
    int k = 10;

}