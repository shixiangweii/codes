package com.sxw.test.basejava;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-15
 * Time: 8:57
 *
 * @author shixiangweii
 */
public class InitializationDemo {

    private int a;

    public static void main(String[] args) {
        // https://www.nowcoder.com/questionTerminal/036b784ff0dc4cc5b2e046dbf6b1113a
        // 成员变量有初始值，而局部变量没有初始值得。本体中的s定义在方法中所以为局部变量-没有初始值。变量没有初始值就使用了，编译通不过
        //String s;
        //System.out.println("s=" + s);

        InitializationDemo initializationDemo = new InitializationDemo();
        // 变量虽然为private，但因为main函数在该类中，所以即使private也仍可使用
        System.out.println(initializationDemo.a);


    }
}
