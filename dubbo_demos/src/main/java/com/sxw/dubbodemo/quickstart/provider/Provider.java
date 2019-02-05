package com.sxw.dubbodemo.quickstart.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:com/sxw/dubbodemo/quickstart/provider/provider.xml"});
        context.start();
        // 按任意键退出
        System.in.read();
    }
}