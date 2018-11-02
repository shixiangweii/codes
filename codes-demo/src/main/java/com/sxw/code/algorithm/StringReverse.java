package com.sxw.code.algorithm;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 14:42
 *
 * @author shixiangweii
 */
public class StringReverse {

    static String reverse(String org) {
        if (org == null || org.length() <= 1) {
            return org;
        }
        // 递归就是自己调用自己，关键是这个递归表达式
        // 在二分法里面，这种“分&合”的思想很有用
        // reverse(n) = [n-1] + reverse(n-1)
        return org.charAt(org.length() - 1) + reverse(org.substring(0, org.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(reverse("123456789"));
        System.out.println(reverse("@#$%   "));
    }
}
