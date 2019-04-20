package interview.order.demo2;

/**
 * https://www.nowcoder.com/questionTerminal/c2bfb1512dfa4a7eab773a5871a52402
 *
 */
public class Base {
    private String baseName = "base";

    public Base() {
        callName();
    }

    public void callName() {
        /**
         * 链接：https://www.nowcoder.com/questionTerminal/c2bfb1512dfa4a7eab773a5871a52402
         来源：牛客网

         new Sub();在创造派生类的过程中首先创建基类对象，然后才能创建派生类。
         创建基类即默认调用Base()方法，在方法中调用callName()方法，
         由于派生类中存在此方法，则被调用的callName（）方法是派生类中的方法，
         此时派生类还未构造，所以变量baseName的值为null

         多态运行时，因为是字类，所以用的还是字类定义的变量，!!
         看源码的时候也要注意这个多态
         */
        System.out.println(baseName);
    }

    static class Sub extends Base {
        private String baseName = "sub";

        public void callName() {
            System.out.println(baseName);
        }
    }

    public static void main(String[] args) {
        Base b = new Sub();
    }
}