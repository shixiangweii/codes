package com.sxw.spring.annotation.condition;

import com.sxw.spring.annotation.bean.White;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author shixi
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean beanDefinition = registry.containsBeanDefinition("com.sxw.spring.annotation.bean.Blue");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(White.class);
        registry.registerBeanDefinition("myRed", rootBeanDefinition);
    }
}
