package interview.nulldemo.demo2;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 18:52
 *
 * @author shixiangweii
 */
public class NullTest {

    private static void testMethod() {
        System.out.println("testMethod");
    }

    /**
     * https://www.nowcoder.com/questionTerminal/e252668ee94947bea07edd7300340115
     */
    @Test
    public void testNull() {
        // null == null并不会报错
        System.out.println(null == null);
        // 吃惊了居然不会报错
        NullTest testNull = null;

        // “不应该”，并不是报错，运行也不会报错
        testNull.testMethod();

        ((NullTest) null).testMethod();

        // 不会报错输出null
        // null强转引用类型，并不会报错
        // 其实这种自己在平时开发中一直在用啊，就比如调用一个方法 func(Integer n)
        // func(null)
        // 只是这里换一种写法 (Integer) null，自己就不认识了？！
        System.out.println(((NullTest) null));
        Integer i = null;
        // NPE
        // int a = i;
        Object c = null;
        Integer d = (Integer) c;
        System.out.println(d);
    }
}
