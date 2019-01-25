package com.sxw.code.oop.inherit.demo3;

/**
 * Description:
 *
 * https://www.nowcoder.com/questionTerminal/0514f9318d3547bbbd601128f1a85e63
 *
 * 一道很贱的题目
 * User: shixiangweii
 * Date: 2019-01-25
 * Time: 9:49
 *
 * @author shixiangweii
 */
public class Console {
}

class Human {

    public Human(String name) {

    }
}

class Man extends Human {

    public Man() {
        // super语句必须是构造方法内部，第一行
        super("");
        System.out.println();
        // this();
    }

/*    public Man(String text) {

    }*/
}

