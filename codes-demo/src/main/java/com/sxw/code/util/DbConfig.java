package com.sxw.code.util;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-06-06
 * Time: 1:07
 *
 * @author shixiangweii
 */
@Configuration
public class DbConfig {
    @Bean
    public DataSource dataSource() {
        return new MyDruidDataSourceWrapper();
    }
}

/**
 * metadata.json只是能做到一个在.properties文件中提示的作用，本身不参与自动化配置
 * druid集成原理：
 * 使用spring根据.properties文件自动setter设置属性值的的功能，来实例化DruidDataSource
 * 具体就是就是使用“@ConfigurationProperties("spring.datasource.druid")”注解来实现自动化的属性值设置
 * 正是因为指定了"spring.datasource.druid"，所以只会自动设置druid.之后的属性值
 * 而spring.datasource.url,spring.datasource.username,spring.datasource.password都不符合"spring.datasource.druid"路径，
 * 所以在afterPropertiesSet中单独设置
 */
@ConfigurationProperties("spring.datasource.druid")
class MyDruidDataSourceWrapper extends DruidDataSource implements InitializingBean {

    private static final long serialVersionUID = -2519479315341650631L;

    @Resource
    private DataSourceProperties basicProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (super.getUsername() == null) {
            super.setUsername(basicProperties.determineUsername());
        }
        if (super.getPassword() == null) {
            super.setPassword(basicProperties.determinePassword());
        }
        if (super.getUrl() == null) {
            super.setUrl(basicProperties.determineUrl());
        }
        if (super.getDriverClassName() == null) {
            super.setDriverClassName(basicProperties.getDriverClassName());
        }
        // 根据bean生命周期，afterPropertiesSet，结束就是调用bean指定的"initMethod"，这里可以手工调用
        this.init();
    }
}
