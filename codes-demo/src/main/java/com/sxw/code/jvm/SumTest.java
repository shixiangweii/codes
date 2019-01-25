package com.sxw.code.jvm;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-24
 * Time: 17:43
 *
 * @author shixiangweii
 */
public class SumTest {
    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String args[]) {
        // -1
        x--;
        myMethod();
        // 3
        // 1 + 0 + 2
        System.out.println(x + y + ++x);
    }

    public static void myMethod() {
        y = x++ + ++x;
    }
}
