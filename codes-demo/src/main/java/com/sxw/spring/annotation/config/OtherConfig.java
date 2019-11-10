package com.sxw.spring.annotation.config;

import com.sxw.spring.annotation.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shixi
 */
@Configuration
public class OtherConfig {
    @Bean
    public Dog oneDog() {
        return new Dog();
    }
}
