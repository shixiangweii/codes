package com.sxw.code.recursion;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 21:51
 *
 * @author shixiangweii
 */
public class Fibonacci {
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
            return i;
        }
        return f1(i - 1) + f1(i - 2);
    }

    /**
     * 尾递归，说说实话，感觉在换种套路写循环，借用系统的stack
     *
     * @param a
     * @param b
     * @param num
     * @return
     */
    public static long f2(long a, long b, long num) {
        steps++;
        if (num <= 0) {
            return a;
        }
        return f2(b, a + b, num - 1);
    }

    public static void main(String[] args) {
        long num = 45L;
        long l = System.currentTimeMillis();
        try {
            steps = 0;
            System.out.println("res:\t" + f1(num));
            System.out.println("time:\t" + (System.currentTimeMillis() - l));
            System.out.println("steps:\t" + steps);
        } catch (Exception e) {
            e.printStackTrace();
        }

        l = System.currentTimeMillis();
        try {
            steps = 0;
            System.out.println("res:\t" + f2(0L, 1L, num));
            System.out.println("time:\t" + (System.currentTimeMillis() - l));
            System.out.println("steps:\t" + steps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
