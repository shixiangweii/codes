package com.sxw.test.basejava;

import org.junit.Test;

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
        // count = count++  原理是 temp = count； count = count+1 ； count = temp；   因此count始终是0 这仅限于java 与c是不一样的
        count = count++;
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
        long test=012;
        float f=-412;
        // boolean类型不能和任何类型进行转换，会报出类型异常错误
        // int other =(int)true;
        double d=0x12345678;
        // byte的取值范围是-128—127
        // byte b=128;
    }
}
