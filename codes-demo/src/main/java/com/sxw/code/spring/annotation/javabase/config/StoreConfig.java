package com.sxw.code.spring.annotation.javabase.config;


import com.sxw.code.spring.annotation.javabase.MyDriverManager;
import com.sxw.code.spring.annotation.javabase.impl.IntegerStore;
import com.sxw.code.spring.annotation.javabase.impl.LongStore;
import com.sxw.code.spring.annotation.javabase.impl.StringStore;
import com.sxw.code.spring.annotation.javabase.inter.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.List;


@Configuration
@ImportResource("classpath:com/sxw/code/spring/annotation/javabase/config.xml")
public class StoreConfig {
    @Value("${url}")
    private String url;

    @Value("${jdbc.username}")
    private String userName;

    @Value("${password}")
    private String password;

    @Autowired
    private LongStore longStore;

    @Autowired
    private IntegerStore integerStore;

    @Autowired
    private List<IntegerStore> longStoreList;

    @Bean(name = "store", initMethod = "init", destroyMethod = "destroy")
    public Store<?> stringStore() {
        return new StringStore();
    }

    @Bean(name = "stringStoreScopeProxy")
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Store<?> stringStoreScopeProxy() {
        return new StringStore();
    }

    @Bean(name = "stringStoreScope")
    @Scope(value = "prototype")
    public Store<?> stringStoreScope() {
        return new StringStore();
    }

    @Bean
    public LongStore longStore() {
        return new LongStore();
    }

    @Bean
    public IntegerStore integerStore() {
        return new IntegerStore();
    }

    @Bean
    public MyDriverManager myDriverManager() {
        return new MyDriverManager(url, userName, password);
    }

}
