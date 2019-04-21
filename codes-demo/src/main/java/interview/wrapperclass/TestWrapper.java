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
public class TestWrapper {
    @Test
    public void test() {
        // 是把String 变成int的基础数据类型，返回值是int
        Integer.parseInt("1024");
        // Valueof()是把String 转化成Integer对象类型
        Integer.valueOf("1024").intValue();
        System.out.println(Integer.parseInt("1024") == Integer.valueOf("1024").intValue());
    }

    /**
     * https://www.nowcoder.com/questionTerminal/643b145a860f457d8a150869e1a17eba
     * 自动拆装箱的考题(自动拆装箱JDK需在1.5上）
     * 1、基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较
     * 2、两个Integer类型进行“==”比较，如果其值在-128至127，那么返回true，否则返回false, 这跟Integer.valueOf()的缓冲对象有关
     * 3、两个基本型的封装型进行equals()比较，首先equals()会比较类型，如果类型相同，则继续比较值，如果值也相同，返回true
     * 4、基本型封装类型调用equals(),但是参数是基本类型，这时候，先会进行自动装箱，基本型转换为其封装类型，再进行3中的比较。
     */
    @Test
    public void testAuto() {
        int i = 0;
        Integer j = new Integer(0);
        System.out.println(i == j);
        System.out.println(j.equals(i));

        int a = 257;
        Integer b = 257;
        Integer c = 257;
        Integer b2 = 57;
        Integer c2 = 57;
        System.out.println(a == b);
//System.out.println(a.equals(b));  编译出错，基本型不能调用equals()
        // 首先equals()会比较类型，如果类型相同，则继续比较值，如果值也相同，返回true
        System.out.println(b.equals(257.0));
        System.out.println(b == c);
        System.out.println(b2 == c2);
    }

    /**
     * 这题厉害炸了
     *
     * @param b
     */
    public void add(Byte b) {
        /*
        看似是引用传递，但是在add函数内实现++操作，会自动拆包成byte值传递类型，
        所以add函数还是不能实现自增功能。也就是说add函数只是个摆设，没有任何作用
         */
        b = b++;
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
        System.out.print(b + "");
    }

    /**
     * https://www.nowcoder.com/questionTerminal/0bc5e15365ec44a7bfa976f1a2a40b20
     *
     * https://www.nowcoder.com/questionTerminal/0bc5e15365ec44a7bfa976f1a2a40b20
     */
    @Test
    public void testWrapper2() {
        // 其实当我们在为Integer赋值的时候，java编译器会将其翻译成调用valueOf()方法
        Integer i1 = 128;
        Integer i2 = 128;
        // false
        System.out.println(i1 == i2);

        Integer i8 = 100;
        Integer i9 = 100;
        // true
        System.out.println(i8 == i9);

        String i3 = "100";
        String i4 = "1" + new String("00");
        String i5 = "1" + "00";
        // false
        System.out.println(i3 == i4);
        // true
        System.out.println(i3 == i5);
        // false
        System.out.println(i4 == i5);


    }

}
