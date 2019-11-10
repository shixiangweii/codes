package com.sxw.spring.annotation;

import com.sxw.spring.annotation.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author shixi
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println("====================All definitionNames Start====================");
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        System.out.println("====================All definitionNames End====================");
    }
}
