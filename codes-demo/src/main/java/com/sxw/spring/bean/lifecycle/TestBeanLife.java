package com.sxw.spring.bean.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shixi
 */
public class TestBeanLife {
    /**
     * com/sxw/spring/bean/lifecycle/beans.xml
     *
     * @param args args
     */
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("com/sxw/spring/bean/lifecycle/beans.xml");
        Student bean = (Student) context.getBean("myBean");
        bean.printInfo();
    }
}
