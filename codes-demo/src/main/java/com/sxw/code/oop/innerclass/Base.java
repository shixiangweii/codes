package com.sxw.code.oop.innerclass;

import com.sxw.code.oop.innerclass.Enclosingone.InsideOne;

public class Base {

    private String baseName = "base";

    public Base() {
        callName();
    }

    public void callName() {
        System.out.println(baseName);
    }

    static class Sub extends Base {
        private String baseName = "sub";

        public void callName() {
            // https://www.nowcoder.com/test/question/done?tid=20811894&qid=3212#summary
            System.out.println(baseName);
        }
    }

    class Sub2 {

    }

    /**
     * new Sub();在创造派生类的过程中首先创建基类对象，然后才能创建派生类。
     * 创建基类即默认调用Base()方法，在方法中调用callName()方法，由于派生类中存在此方法，
     * 则被调用的callName（）方法是派生类中的方法，此时派生类还未构造，所以变量baseName的值为null
     *
     * @param args
     */
    public static void main(String[] args) {
        Base b = new Sub();
        Enclosingone eo = new Enclosingone();


        Enclosingone.InsideOne ei = eo.new InsideOne();

        // 最前面用import导入
        InsideOne ei2 = eo.new InsideOne();
    }
}


class Enclosingone {
    public class InsideOne {
    }
}