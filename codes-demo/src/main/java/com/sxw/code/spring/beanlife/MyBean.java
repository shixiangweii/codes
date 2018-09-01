package com.sxw.code.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author shixiangweii
 */
public class MyBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    private String name;

    public void setName(String name) {
        System.out.println("setter:setName");
        this.name = name;
    }

    public MyBean() {
        System.out.println("MyBean Constructor");
    }

    void printInfo() {
        System.out.println("use bean:prinetInfo()");
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setBeanName(String beanName) {
        System.out.println("BeanNameAware:" + beanName);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory aware");
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext aware");
    }

    @Override
    public String toString() {
        return "MyBean [name=" + name + "]";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public void myInit() {
        System.out.println("init-method");
    }
}
