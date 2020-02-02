package com.sxw.spring.bean.lifecycle;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author shixi
 */
public class Student implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    private String studentName;

    /**
     * 构造方法
     */
    public Student() {
        Counter.log("Constructor Student()");
    }

    /**
     * Bean init方法
     */
    public void myInit() {
        Counter.log("init-method: myInit");
    }

    /**
     * 业务方法
     */
    void printInfo() {
        Counter.log("bean method printInfo()");
    }

    /**
     * setter
     *
     * @param studentName studentName
     */
    public void setStudentName(String studentName) {
        Counter.log("setter setStudentName");
        this.studentName = studentName;
    }

    @Override
    public void setBeanName(@NotNull String beanId) {
        Counter.log("BeanNameAware.setBeanName: " + beanId);
    }

    @Override
    public void setBeanFactory(@NotNull BeanFactory beanFactory) throws BeansException {
        Counter.log("BeanFactoryAware.setBeanFactory");
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        Counter.log("ApplicationContextAware.setApplicationContext");
    }

    @Override
    public void afterPropertiesSet() {
        Counter.log("InitializingBean.afterPropertiesSet");
    }

    @Override
    public String toString() {
        return "Student [studentName=" + studentName + "]";
    }
}
