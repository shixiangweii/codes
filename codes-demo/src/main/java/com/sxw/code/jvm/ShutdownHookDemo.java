package com.sxw.code.jvm;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 9:46
 * JVM关闭钩子测试
 *
 * @author shixiangweii
 */
public class ShutdownHookDemo {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("auto clean temporary file1")));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("auto clean temporary file2")));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("auto clean temporary file3")));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("auto clean temporary file4")));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("auto clean temporary file5")));
    }
}
