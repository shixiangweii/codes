package interview.wrapperclass;

import org.junit.Test;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 15:09
 * https://www.nowcoder.com/questionTerminal/7081fbf9ccea44ca81f1b644f3550c2a
 *
 * @author shixiangweii
 */
public class WrapperTest {

    /**
     * parseInt，返回值类型
     */
    @Test
    public void test1() {
        int i = Integer.parseInt("1024");
    }

    /**
     * valueOf，返回的是对象类型
     */
    @Test
    public void test2() {
        Integer integer = Integer.valueOf("1024");
    }

    /**
     * 基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较
     */
    @Test
    public void test3() {
        System.out.println(Integer.parseInt("1024") == Integer.valueOf("1024"));
    }

    /**
     * https://www.nowcoder.com/questionTerminal/0bc5e15365ec44a7bfa976f1a2a40b20
     * https://www.nowcoder.com/questionTerminal/643b145a860f457d8a150869e1a17eba
     * 自动拆装箱的考题(自动拆装箱JDK需在1.5上）
     * 1、基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较
     * 2、两个Integer类型进行“==”比较，如果其值在-128至127，那么返回true，否则返回false, 这跟Integer.valueOf()的缓冲对象有关
     * 3、两个基本型的封装型进行equals()比较，首先equals()会比较类型，如果类型相同，则继续比较值，如果值也相同，返回true
     * 4、基本型封装类型调用equals(),但是参数是基本类型，这时候，先会进行自动装箱，基本型转换为其封装类型，再进行3中的比较。
     */
    @Test
    public void testAuto() {

        // 其实当我们在为Integer赋值的时候，java编译器会将其翻译成调用valueOf()方法
        Integer i1 = 128;
        Integer i2 = 128;
        // false
        System.out.println(i1 == i2);

        Integer i8 = 100;
        Integer i9 = 100;
        // true
        System.out.println(i8 == i9);

        int i = 0;
        Integer j = new Integer(0);
        // true
        System.out.println(i == j);
        // true
        System.out.println(j.equals(i));

        int a = 257;
        Integer b = 257;
        Integer c = 257;
        // true
        System.out.println(a == b);
        // System.out.println(a.equals(b));  编译出错，基本型不能调用equals()
        // false，类型不同直接返回false
        System.out.println(b.equals(257.0));
        // false
        System.out.println(b == c);

        Integer b2 = 57;
        Integer c2 = 57;
        // true
        System.out.println(b2 == c2);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/1bab09264a1c4528aa60ee9bca5f0976
     * http://www.cnblogs.com/nailperry/p/4780354.html
     */
    @Test
    public void testByteAdd() {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        // -128
        System.out.print(a + " ");
        add(b);
        // 127
        System.out.print(b + "");
    }

    /**
     * add
     * @param b 数字
     */
    public void add(Byte b) {
        // 看似是引用传递，但是在add函数内实现++操作，会自动拆包成byte值传递类型，
        // 所以add函数还是不能实现自增功能。也就是说add函数只是个摆设，没有任何作用
        b = b++;
    }
}
