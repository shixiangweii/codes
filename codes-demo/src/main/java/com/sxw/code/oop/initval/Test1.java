package com.sxw.code.oop.initval;

/**
 * https://www.nowcoder.com/questionTerminal/0ddad0548cb7404d8aa6ef72912e0c7d
 */
public class Test1 {
    /**
     * static字段会在“准备”阶段基本的初始化
     */
    static String a;

    public static void main(String[] args) {
        System.out.println(a);
    }
}

class Test2 {
    /**
     * https://www.nowcoder.com/questionTerminal/036b784ff0dc4cc5b2e046dbf6b1113a
     * main方法不一定要在修饰符public的类中出现
     * @param args
     */
    public static void main(String[] args) {
        String a;
        // 编译不通过，have not been initialized
        // System.out.println(a);

        // 成员变量有初始值，而局部变量没有初始值得。本体中的s定义在方法中所以为局部变量-没有初始值。变量没有初始值就使用了，编译通不过
        String s;
      // System.out.println("s="+s);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/deec39e906544bf788593b4a5ede42db
     * 一个类中，可以有多个main方法，这是重载，但是public static void main(String[] args)的方法只能有一个
     */
    public static void main() {

    }

}