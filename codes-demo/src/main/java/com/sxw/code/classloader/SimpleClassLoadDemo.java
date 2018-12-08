package com.sxw.code.classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Description: 简单测试加载文件系统中jar的demo
 * User: shixiangweii
 * Date: 2018-12-08
 * Time: 17:24
 *
 * @author shixiangweii
 */
public class SimpleClassLoadDemo {

    public static void main(String[] args) throws Exception {
        File jarFile = new File("file/yyb-data-api-1.0-SNAPSHOT.jar");
        ClassLoader loader = new URLClassLoader(
                new URL[]{jarFile.toURI().toURL()},
                SimpleClassLoadDemo.class.getClassLoader());

        try {
            // 加载不到，会报异常
            Class<?> mainLoaderClazz = SimpleClassLoadDemo.class.getClassLoader().loadClass("com.wanbang.yybdata.dto.SevenDayDataReqDto");
            System.out.println(mainLoaderClazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Class<?> clazz = loader.loadClass("com.wanbang.yybdata.dto.SevenDayDataReqDto");

        System.out.println(clazz.getName());

        // 只要定义好接口，直接这里面向接口编程，转换
        // 纯业务逻辑分离出来打成独立的jar包，然后通过通过ClassLoader配合文件监听系统 比如apache的commons-vfs来做java的热部署
        // ((Runnable)clazz.newInstance()).run();
    }

}

