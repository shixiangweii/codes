package com.sxw.code.recursion;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 21:30
 *
 * @author shixiangweii
 */
public class Factorial {
    public static int steps = 0;

    /**
     * 头递归式真的写递归
     *
     * @param i
     * @return
     */
    public static long f1(long i) {
        steps++;
        if (i <= 1) {
            return 1;
        }
        return i * f1(i - 1);
    }

    /**
     * 尾递归，说说实话，感觉在换种套路写循环，借用系统的stack
     *
     * @param r
     * @param i
     * @param num
     * @return
     */
    public static long f2(long r, long i, long num) {
        steps++;
        r *= i;
        if (i == num) {
            return r;
        }
        return f2(r, i + 1, num);
    }

    public static void main(String[] args) {
        long num = 20L;
        long l = System.currentTimeMillis();
        try {
            steps = 0;
            System.out.println(f1(num));
            System.out.println(System.currentTimeMillis() - l);
            System.out.println(steps);
        } catch (Exception e) {
            e.printStackTrace();
        }

        l = System.currentTimeMillis();
        try {
            steps = 0;
            System.out.println(f2(1L, 1L, num));
            System.out.println(System.currentTimeMillis() - l);
            System.out.println(steps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
