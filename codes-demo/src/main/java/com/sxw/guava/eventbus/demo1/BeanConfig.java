package com.sxw.guava.eventbus.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shixi
 */
@Configuration
public class BeanConfig {

    @Bean
    public TimeTaskEventListener timeTaskEventListener() {
        TimeTaskEventListener timeTaskEventListener = new TimeTaskEventListener();
        return timeTaskEventListener;
    }
}
