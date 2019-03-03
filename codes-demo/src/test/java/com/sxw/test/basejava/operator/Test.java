package com.sxw.test.basejava.operator;

/**
 * https://www.nowcoder.com/questionTerminal/9dfb7470f3014166947cba07a69133c3
 *
 */
public class Test {
    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String args[]) {
        x--;
        myMethod();
        System.out.println(x + y + ++x);

        // https://www.nowcoder.com/questionTerminal/0e08a0ce18ea47af8a961366f1f35edc

        x+=y++;
        x=++y;
        //x++=y++;
        //++x=++y;
    }

    public static void myMethod() {
        y = x++ + ++x;
    }
}