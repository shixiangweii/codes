package com.sxw.code.jvm.polymorphic;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-23
 * Time: 14:07
 *
 * @author shixiangweii
 */
public class SingleDoublePai {
    public static void main(String[] args) {
        Father father = new Father();
        Father child = new Child();

        // 动态单分派，方法覆盖
        // 编译期已经了确定了目标方法的参数类型（编译期根据参数的静态类型进行静态分派），因此唯一可以影响到虚拟机选择的因素只有此方法的接受者的实际类型是 Father 还是 Child
        father.doSomething(new Eat());
        child.doSomething(new Drink());
    }
}

class Eat {
}

class Drink {
}

class Father {
    public void doSomething(Eat arg) {
        System.out.println("爸爸在吃饭");
    }

    public void doSomething(Drink arg) {
        System.out.println("爸爸在喝水");
    }
}

class Child extends Father {
    @Override
    public void doSomething(Eat arg) {
        System.out.println("儿子在吃饭");
    }

    @Override
    public void doSomething(Drink arg) {
        System.out.println("儿子在喝水");
    }
}
