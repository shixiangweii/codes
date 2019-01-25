package com.sxw.test.basejava;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-25
 * Time: 9:35
 *
 * @author shixiangweii
 */
public class TryCatchReturnDemo {
    public static void main(String[] args) {
        System.out.println(new B().getValue());
    }

    static class A {
        protected int value;

        public A(int v) {
            setValue(v);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            try {
                value++;
                // 暂存return数值
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                this.setValue(value);
                // value修改后的值，非return值
                System.out.println(value);
            }
            return value;
        }
    }

    static class B extends A {
        public B() {
            super(5);
            setValue(getValue() - 3);
        }

        /**
         * 注意执行，流程，继承方法重写的问题
         *
         * @param value
         */
        @Override
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
