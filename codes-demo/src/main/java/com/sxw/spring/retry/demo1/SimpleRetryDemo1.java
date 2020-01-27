package com.sxw.spring.retry.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author shixi
 * 重试
 * https://www.cnblogs.com/mr-yang-localhost/p/11247286.html
 *
 * <p>
 * <p>
 * 使用场景：
 * 调用其他项目提供的Api，网络抖动调用失败
 * <p>
 * <p>
 * <p>
 * 不适合：
 * add或update等非幂等性操作
 * <p>
 * 策略
 * 补偿/回退（backoff），重试一次失败后，等待多少时间再一次重试；默认没有backoff策略，不等待就重试
 * 重试
 * 重试上下文缓存（强引用 与 软引用 缓存方式）。无状态重试 & 有状态重试（需要借助缓存保存重试的上下文信息）
 * 重试监听者（实现不同阶段的通知功能）
 * <p>
 * 兜底结果
 * 通过指定RecoveryCallback，返回一个兜底结果
 */
public class SimpleRetryDemo1 {
    private static Logger logger = LoggerFactory.getLogger(SimpleRetryDemo1.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RetryConfig.class);
        RetryTemplate retryTemplate = context.getBean(RetryTemplate.class);
        // 不指定 RecoveryCallback（兜底结果）
        try {
            Boolean execute = retryTemplate.execute(ctx -> testMethod("arg-1", 12));
            System.out.println("result : " + execute);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 指定 RecoveryCallback （兜底结果）
        try {
            Boolean execute = retryTemplate.execute(ctx -> testMethod("arg-1", 12), ctx -> callBack("arg-1", 12));
            System.out.println("兜底result : " + execute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean testMethod(String arg1, Integer arg2) throws RuntimeException {
        logger.info("调用 testMethod 方法：arg1:" + arg1 + ",arg2:" + arg2);
        throw new RuntimeException("出错啦！");
    }

    public static Boolean callBack(String arg1, Integer arg2) throws RuntimeException {
        logger.info("调用 callBack 方法：arg1:" + arg1 + ",arg2:" + arg2);
        return false;
    }
}
