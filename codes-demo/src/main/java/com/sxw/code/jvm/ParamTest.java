package com.sxw.code.jvm;

/**
 * Description: JVM参数配置测试
 * User: shixiangweii
 * Date: 2018-08-31
 * Time: 8:57
 *
 * @author shixiangweii
 *
 *
 * Runtime封装Java应用运行时的环境。通过Runtime实例，使得应用程序和其运行环境相连接。
 * Runtime是在应用启动期间自动建立，应用程序不能够创建Runtime，但是我们可以通过Runtime.getRuntime()来获得当前应用的Runtime对象引用，
 * 通过该引用我们可以获得当前运行环境的相关信息，比如空闲内存、
 * 最大内存以及为当前虚拟机添加关闭钩子（addShutdownHook（）），执行指定命令（exec（））等等
 *
 *
 *
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
