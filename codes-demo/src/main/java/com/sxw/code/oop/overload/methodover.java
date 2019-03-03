package com.sxw.code.oop.overload;

public class methodover {
    public void setVar(int a, int b, float c) {
    }

    /**
     * 重载，访问权限修饰是没有要求的吗，不要和重写搞混了
     * @param a
     * @param c
     * @param b
     */
    private void setVar(int a, float c, int b) {
    }

}