package com.sxw.spring.retry.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.SoftReferenceMapRetryContextCache;
import org.springframework.retry.support.RetryTemplate;


/**
 * @author shixi
 */
@Configuration
public class RetryConfig {

    @Bean
    public RetryTemplate getRetryTemplate() {
        RetryTemplate template = new RetryTemplate();
        // 回退策略：固定等待时间
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        // 设置2s重试一次
        backOffPolicy.setBackOffPeriod(2000);
        template.setBackOffPolicy(backOffPolicy);
        // 上下文缓存：软引用
        SoftReferenceMapRetryContextCache contextCache = new SoftReferenceMapRetryContextCache();
        template.setRetryContextCache(contextCache);
        // 简单重试策略：默认3次
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        template.setRetryPolicy(retryPolicy);
        // 重试次数用完后抛出最后一次异常
        // exhaust 用完; 花光; 耗尽;
        template.setThrowLastExceptionOnExhausted(true);
        return template;
    }
}
