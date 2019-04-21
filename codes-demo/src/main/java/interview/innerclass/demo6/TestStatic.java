package interview.innerclass.demo6;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 12:09
 * https://www.cnblogs.com/KingIceMou/p/7823918.html
 * <p>
 * "public static class TestStatic",
 * "static class T2"
 * 都是不合法，static不能修饰外部类，可以修饰内部类，匿名内部类
 *
 * @author shixiangweii
 */
public class TestStatic {
    static int i;

    static {

    }

    static class A {
        static int b;
    }

    class B {
        // 内部类不是被定义成静态内部类，那么在定义成员变量或者成员方法的时候，是不能够被定义成静态成员变量与静态成员方法的
        // static int c;
    }
}

