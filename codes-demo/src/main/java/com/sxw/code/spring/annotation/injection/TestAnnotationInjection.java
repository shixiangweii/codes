package com.sxw.code.spring.annotation.injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shixiangweii
 */
public class TestAnnotationInjection {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "com/sxw/code/spring/annotation/injection/applicationContext-annotation.xml");
        context.start();
        context.close();
    }
}
