package com.sxw.test.basejava;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-15
 * Time: 8:17
 *
 * @author shixiangweii
 */
public class TestBaseJava {
    /**
     * https://www.nowcoder.com/questionTerminal/5ce602538d784f51a531bf9760592773
     * count0=count1++的执行步骤：
     * tmp=count1；
     * count1++；
     * count0=tmp；
     */
    @Test
    public void test() {
        int count = 0;
        // count = count++  原理是
        // temp = count；
        // count = count+1 ；
        // count = temp；   因此count始终是0 这仅限于java 与c是不一样的
        count = count++;
        System.out.println(count);

        count = ++count;
        System.out.println(count);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/344945bc01fd49b5beca0cf5f6edea78
     * <p>
     * <p>
     * <p>
     * 表达式的数据类型自动提升, 关于类型的自动提升，注意下面的规则。
     * <p>
     * ①所有的byte,short,char型的值将被提升为int型；
     * <p>
     * ②如果有一个操作数是long型，计算结果是long型；
     * <p>
     * ③如果有一个操作数是float型，计算结果是float型；
     * <p>
     * ④如果有一个操作数是double型，计算结果是double型；
     * 而声明为final的变量会被JVM优化，第6行相当于 b6 = 10
     */
    @Test
    public void test2() {
        byte b1 = 1, b2 = 2, b3, b6;
        final byte b4 = 4, b5 = 6;
        b6 = b4 + b5;
        // 进行计算时候将他们提升为int类型
        // b3 = (b1 + b2);
        // System.out.println(b3 + b6);
    }

    @Test
    public void test3() {
        int a = 1, b = 2;
        String c = "3";
        // 33
        // 先计算 a+b 得到3，然后拼接"3"
        System.out.println(a + b + c);
    }

    /**
     * https://www.nowcoder.com/questionTerminal/f838b38081b942fba7ab2869f71ad071
     * https://uploadfiles.nowcoder.com/images/20161228/371702_1482914740802_DA5DCDA5D64F7FDA8923B5548989F185
     */
    @Test
    public void test4() {
        long test = 012;
        float f = -412;
        // boolean类型不能和任何类型进行转换，会报出类型异常错误
        // int other =(int)true;
        double d = 0x12345678;
        // byte的取值范围是-128—127
        // byte b=128;

        double dd = 5.3e12;
        System.out.println(dd);

       // float ff = 5.3e12;
        // 包装类型，不是不能直接复制，是"自动装箱的目标必须严格对应它拆箱后的类型"
        // 所以说网上的人水平不一定很高，别人的说的不一定就是对的
        // https://www.nowcoder.com/questionTerminal/904af36af29f491fab5fa335fdb7dcf3
       // Double oD=3;
        Double oD=3.0d;
        Float oDf=3.0f;

        // 数值类型，居然是可以直接赋值给obj的
        Object ojb = 3;

        // https://www.nowcoder.com/questionTerminal/f838b38081b942fba7ab2869f71ad071
        // （八进制整型）
        long test1=012;
        // 自动转  -412.0f  单精度 浮点数
        float f1=-412;
        // int other =(int)true;
        // 十六进制整数，自动转 双精度浮点数
        double d2=0x12345678;
        //byte b=128;
    }

    /**
     * https://www.cnblogs.com/yesiamhere/p/6675067.html
     */
    @Test
    public void testOp() {
        int i = 5;
        int j = 10;

        System.out.println(~j);
        System.out.println(i + ~j);
    }

    /**
     * 记住标识符只有英文，数字，下划线和$，而且数字不能做开头
     * https://www.nowcoder.com/questionTerminal/e8898ef5492d461d8fa881a93ed2fabb
     *
     * 标识符可以由字母、数字、下划线（_）、美元符（$）组成，但不能包含 @、%、空格等其它特殊字符，不能以数字开头
     * 标识符不能是 Java 关键字和保留字
     * 标识符是严格区分大小写的
     */
    @Test
    public void testValName() {

      //  int Tree&Glasses;
        int FirstJavaApplet;
        int First_Applet;
        int $Usdollars;
        // int car.taxi
    }

    /**
     * https://www.nowcoder.com/questionTerminal/18f100abfe294eceb6c421d2f6501c1d
     *
     * https://www.nowcoder.com/questionTerminal/1daccd2f23c74f1886f0dada4a5c6c78
     */
    @Test
    public void testRange() {
            // 其实自己都不知道还有这样的语法，匿名类
            Object o = new Object() {
                // 使用了匿名内部类，并重写了Object中的重写了equals()方法
                public boolean equals(Object obj) {
                    return true;
                }
            };
            System.out.println(o.equals("Fred"));

            new Runnable(){

                @Override
                public void run() {

                }
            };
            // 内部类&匿名类
        // 是2个语法概念
            // 居然还有这样的语法，匿名类的语法，这样就不用再声明一个XXXClass extends ArrayList，然后重写equals方法
            new ArrayList<String>() {
                public String toString() {
                    return "";
                }
            };
    }
}
