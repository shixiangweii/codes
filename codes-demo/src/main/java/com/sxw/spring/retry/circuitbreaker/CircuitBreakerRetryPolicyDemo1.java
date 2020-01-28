package com.sxw.spring.retry.circuitbreaker;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 熔断重试策略模式
 *
 * openTimeout openWindow  熔断器电路打开的超时时间
 * resetTimeout timeout
 *
 * [0,openWindow) [openWindow, timeout]
 *
 * 字面意思，“熔断器打开”，都是中国字能看懂，但到底背后是什么意思，比喻指代的是什么意思
 *
 * 当重试失败，且在“熔断器打开”（断路机制起作用）时间窗口[0,openWindow) 内，立即熔断
 * 当重试失败，且超过timeout，熔断器电路重新闭合（断路机制关闭）
 * 在熔断器半打开状态[openWindow, timeout] 时，只要重试成功则重置上下文，断路器闭合
 *
 * @author shixi
 */
public class CircuitBreakerRetryPolicyDemo1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RetryConfig.class);

    }
}
