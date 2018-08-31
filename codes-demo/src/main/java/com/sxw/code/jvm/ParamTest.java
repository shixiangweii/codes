package com.sxw.code.jvm;

/**
 * Description: JVM参数配置测试
 * User: shixiangweii
 * Date: 2018-08-31
 * Time: 8:57
 *
 * @author shixiangweii
 */
public class ParamTest {
    public static void main(String[] args) {

        byte[] b = new byte[300 * 1024 * 1024];
        System.out.println("分配了1M空间给数组");

        System.gc();

        //系统的最大空间
        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");

        //系统的空闲空间
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");

        //当前可用的总空间
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
    }
}
