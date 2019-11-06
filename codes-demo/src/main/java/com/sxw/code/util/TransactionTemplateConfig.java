package com.sxw.code.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Description: 编程式事务模板配置
 * User: shixiangweii
 * Date: 2019-04-08
 * Time: 21:32
 *
 * @author shixiangweii
 */
@Configuration
public class TransactionTemplateConfig {

    @Bean
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager dataSourceTransactionManager) {
        return new TransactionTemplate(dataSourceTransactionManager);
    }
}
