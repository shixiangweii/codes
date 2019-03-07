package com.sxw.code.oop.abstract2;

public abstract class MyClass {
    public int constInt = 5;

    /**
     * 重载的方法，可以是抽象的
     * @param a
     */
    public abstract void method(int a);

    /**
     * 重载，这个方法不是抽象的
     */
    public void method() {

    }
}