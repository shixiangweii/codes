package com.sxw.test.thread.join;

/**
 * Description:
 * https://www.nowcoder.com/questionTerminal/d8a288fc416c4d638dfb042e1be239fc
 * 有三个线程T1,T2,T3,下面方法可以确保它们按顺序执行的有()该线程继续执行?
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 11:21
 *
 * @author shixiangweii
 */
public class TestDemo1 {

    /**
     * 其实不考虑java程序入口的问题，
     * main方法，不就是TestDemo1的没有返回值的静态方法
     *
     * @param args
     */
    public static void main(String[] args) {
        // lambda这个语法糖确实够甜
        final Thread t1 = new Thread(() -> System.out.println("t1"));
        final Thread t2 = new Thread(() -> {
            try {
                // 闭包
                // 如果此时t1还没有执行start,则t1.isAlive为false
                // join会立刻返回
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        final Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        });

        t2.start();
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        t3.start();
    }
}
