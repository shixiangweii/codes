package com.sxw.code.spring.beanlife;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shixiangweii
 */
public class TestBeanLife {
    /**
     MyBean Constructor
     setter:setName
     BeanNameAware:myBean
     beanFactory aware
     applicationContext aware
     postProcessBeforeInitialization
     afterPropertiesSet
     init-method
     postProcessAfterInitialization
     use bean:prinetInfo()
     * @param args args
     */
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("com/sxw/code/spring/beanlife/beans.xml");
        MyBean bean = (MyBean) context.getBean("myBean");
        bean.printInfo();
    }
}
