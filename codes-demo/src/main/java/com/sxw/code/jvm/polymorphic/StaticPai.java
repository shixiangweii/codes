package com.sxw.code.jvm.polymorphic;

/**
 * http://wiki.jikexueyuan.com/project/java-vm/polymorphism.html
 * Description:
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 14:05
 *
 * @author shixiangweii
 */
public class StaticPai {
    public void say(Human hum) {
        System.out.println("I am human");
    }

    public void say(Man hum) {
        System.out.println("I am man");
    }

    public void say(Woman hum) {
        System.out.println("I am woman");
    }

    public static void main(String[] args) {

        Human man = new Man();
        Human woman = new Woman();

        // 静态多分派，方法重载
        // 静态分派的最典型应用就是多态性中的方法重载
        StaticPai sp = new StaticPai();
        // 方法的调用者（回忆上面关于宗量的定义，方法的调用者属于宗量）都为 sp 的前提下，使用哪个重载版本，完全取决于传入参数的数量和数据类型（方法的参数也是数据宗量）
        // 刻意定义了两个静态类型相同、实际类型不同的变量，可见编译器
        // 在重载时是通过参数的静态类型而不是实际类型作为判定依据的
        // javac 编译器就根据参数的静态类型决定使用哪个重载版本
        // 这里的静态类型就是human
        sp.say(man);
        sp.say(woman);
    }
}

class Human {
}

class Man extends Human {
}

class Woman extends Human {
}

