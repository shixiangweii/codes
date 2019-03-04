package com.sxw.code.oop.innerclass.demo4.v1;

import com.sxw.code.oop.innerclass.demo4.inter.Contents;
import com.sxw.code.oop.innerclass.demo4.inter.Destination;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-03
 * Time: 23:35
 * https://blog.csdn.net/gaoyong_stone/article/details/79556287
 *
 * @author shixiangweii
 */
public class Goods {
    private class Content implements Contents {

        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected class GDestination implements Destination {
        private String label;

        /**
         * 构造函数私有
         *
         * @param whereTo
         */
        private GDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return null;
        }
    }

    /**
     * 使用外部类中定义方法，来使外部来使用内部类
     *
     * @param s
     * @return
     */
    public Destination dest(String s) {
        // 外部类可以访问到内部类的私有构造函数
        return new GDestination(s);
    }

    public Contents cont() {
        return new Content();
    }

}

class TestGoods {
    /**
     * 直接用 Contents c和Destination d进行操作，你甚至连这两个内部类的名字都没有看见！
     * 这样，内部类的第一个好处就体现出来了
     * 隐藏你不想让别人知道的操作，也即封装性
     *
     * @param args
     */
    public static void main(String[] args) {
        Goods p = new Goods();
        // 在外部类作用范围之外得到内部类对象的第一个方法，那就是利用其外部类的方法创建并返回
        Contents c = p.cont();
        Destination d = p.dest("Beijing");

        // outerObject=new outerClass(Constructor Parameters);
        // outerClass.innerClass innerObject=outerObject.new InnerClass(Constructor Parameters);
        // 创建非静态内部类对象时，一定要先创建起相应的外部类对象
    }
}
