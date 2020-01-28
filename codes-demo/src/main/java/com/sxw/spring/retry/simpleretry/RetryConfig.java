package com.sxw.spring.retry.simpleretry;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.SoftReferenceMapRetryContextCache;
import org.springframework.retry.support.RetryTemplate;

import java.util.Map;


/**
 * @author shixi
 */
@Configuration
public class RetryConfig {
    /**
     * https://blog.csdn.net/elim168/article/details/90320848
     *
     * @return
     */
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
        // 简单重试策略：默认3次 包括第一次调用
        // SimpleRetryPolicy默认将对所有异常进行尝试，最多尝试3次
        Map<Class<? extends Throwable>, Boolean> retryableExceptions = Maps.newHashMap();
        // 指定可以重试的异常类型
        retryableExceptions.put(IllegalStateException.class, true);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(10, retryableExceptions);
        template.setRetryPolicy(retryPolicy);
        // 重试次数用完后抛出最后一次异常
        // exhaust 用完; 花光; 耗尽;
        template.setThrowLastExceptionOnExhausted(true);
        return template;
    }
}
