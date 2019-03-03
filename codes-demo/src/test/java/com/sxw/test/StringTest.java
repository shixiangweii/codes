package com.sxw.test;

import org.junit.Test;

/**
 * https://www.nowcoder.com/questionTerminal/5a9f57aee208466484118b27f3f09c2e
 * <p>
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 11:21
 *
 * \
 * https://www.nowcoder.com/questionTerminal/093bfa948d144ce3b0a68b938ae8b4ec
 *
 * @author shixiangweii
 */
public class StringTest {

    @Test
    public void testStringConcat() {
        System.out.println("is " + 100 + 5);
        System.out.println(100 + 5 + " is" + 9 + 1);
        System.out.println("is " + (100 + 5));
    }

    @Test
    public void testStringEqual() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;

        // false,s1字面量，在常量池中，s2是new出来的，在堆中，引用不一样
        System.out.println(s1 == s2);

        // true s1常量池，s5,字面量拼接，常量池
        System.out.println(s1 == s5);

        // false s1常量池，s6 相当于StringBuffer拼接后toString
        System.out.println(s1 == s6);

        // true s1常量池，s6获取常量池中的版本，因为常量池中有s1，所以true
        System.out.println(s1 == s6.intern());

        // false 相当于 s2 == s1 的比较结果
        System.out.println(s2 == s2.intern());
    }

    /**
     * jdk7
     * 方法区，永久代
     */
    static String str0 = "0123456789";
    static String str1 = "0123456789";

    /**
     * 用 new 在堆(heap)上的内存
     * "56789"
     */
    String str2 = str1.substring(5);
    /**
     * new 新建
     */
    String str3 = new String(str2);
    /**
     * new 新建的
     */
    String str4 = new String(str3.toCharArray());

    @Test
    public void testString() {
        str0 = null;
    }

  //  链接：https://www.nowcoder.com/questionTerminal/10afeae4a5c24a35898c0771fb5995b1
   // 来源：牛客网

    private static final String MESSAGE="taobao";
    public static void main(String [] args) {
        String a ="tao"+"bao";
        String b="tao";
        String c="bao";
        System.out.println(a==MESSAGE);
        System.out.println((b+c)==MESSAGE);
    }
}
