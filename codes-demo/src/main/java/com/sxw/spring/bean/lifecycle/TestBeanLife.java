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
     * 1)	Constructor Student()
     *
     * 2)	setter setStudentName
     *
     * 3)	BeanNameAware.setBeanName: myBean
     *
     * 4)	BeanFactoryAware.setBeanFactory
     *
     * 5)	ApplicationContextAware.setApplicationContext （注入上下文，使用例如：SpringUtils）
     *
     * 6)	BeanPostProcessor.postProcessBeforeInitialization
     *
     * 7)	    InitializingBean.afterPropertiesSet
     *
     * 8)	    init-method: myInit
     *
     * 9)	BeanPostProcessor.postProcessAfterInitialization（后置处理器，starter，绑定配置关系都是利用这个，超级有用）
     *
     * 10)	bean method printInfo()
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
