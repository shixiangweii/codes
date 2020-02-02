package com.sxw.spring.bean.lifecycle;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author shixi
 */
public class MyBeanPostProcessorImpl implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, String beanName) throws BeansException {
        Counter.log("BeanPostProcessor.postProcessAfterInitialization");
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(@NotNull Object bean, String beanName) throws BeansException {
        Counter.log("BeanPostProcessor.postProcessBeforeInitialization");
        return bean;
    }

}
