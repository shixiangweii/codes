package com.sxw.spring.annotation.config;

import com.sxw.spring.annotation.bean.Blue;
import com.sxw.spring.annotation.bean.Cat;
import com.sxw.spring.annotation.bean.Red;
import com.sxw.spring.annotation.condition.CustomCondition;
import com.sxw.spring.annotation.condition.CustomImportBeanDefinitionRegistrar;
import com.sxw.spring.annotation.condition.CustomSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author shixi
 * Import导入组件，id默认是组件的全类名
 * 4种方式：
 * 1）一般bean
 * 2) @Configuration类
 * 3）ImportSelector
 * 4) ImportBeanDefinitionRegistrar
 * <p>
 * registrar 登记员
 * registry 登记
 */
@Conditional({CustomCondition.class})
@Configuration
@Import({Blue.class, Red.class, OtherConfig.class, CustomSelector.class, CustomImportBeanDefinitionRegistrar.class})
public class MainConfig {
    @Bean
    public Cat oneCat() {
        return new Cat();
    }
}
