package com.sxw.guava.eventbus.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shixi
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        TimeTask timeTask = new TimeTask();
        TimeEventBus.post(new TimeExpiringEvent(timeTask));
        Thread.sleep(30 * 1000L);
    }
}
