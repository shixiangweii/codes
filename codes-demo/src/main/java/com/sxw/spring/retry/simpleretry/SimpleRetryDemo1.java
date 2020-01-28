package com.sxw.spring.retry.simpleretry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author shixi
 * 重试
 * https://www.cnblogs.com/mr-yang-localhost/p/11247286.html
 * <p>
 * https://blog.csdn.net/u011116672/article/details/77823867
 * <p>
 * 指定异常重试
 * https://blog.csdn.net/elim168/article/details/90320848
 *
 *
 * <p>
 * <p>
 * 使用场景：
 * 调用其他项目提供的Api，网络抖动调用失败
 * <p>
 * <p>
 * <p>
 * 不适合：非幂等 & 本身就是错的、再重试也没用
 * add或update等非幂等性操作
 * 数据库操作异常DataAccessException，不能执行重试，因为是显然的数据库权限不满足，再怎么重试都没用；
 * 比如，外部结果，返回 99999 签名错误，这种时候再重试也没用
 * <p>
 * 策略
 * 补偿/回退（backoff），
 * 重试一次失败后，等待多少时间再一次重试；默认没有backoff策略，不等待就重试
 * 每次重试是立即重试还是等待一段时间后重试
 * <p>
 * 重试
 * 重试上下文缓存（强引用 与 软引用 缓存方式）。无状态重试 & 有状态重试（需要借助缓存保存重试的上下文信息）
 * <p>
 * 无状态在整个重试过程中是一个RetryContext，其不会进行保存
 * 有状态的重试因为RetryContext是保存的，其可以跨或不跨线程在多次execute()调用中应用同一个RetryContext
 *
 * <p>
 * 事务回滚和熔断
 * 熔断的意思不在当前循环中处理重试，而是全局重试模式（不是线程上下文）
 * 熔断会跳出循环，那么必然会丢失线程上下文的堆栈信息
 * 需要一种“全局模式”保存这种信息，目前的实现放在一个cache（map实现的）中，下次从缓存中获取就能继续重试了
 * <p>
 * <p>
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
            // ctx RetryContext
            // 通过它可以获取到尝试次数，也可以通过其setAttribute()和getAttribute()来传递一些信息
            Boolean execute = retryTemplate.execute(ctx -> {
                System.out.println(ctx.getRetryCount());
                return testMethod("arg-1", 12);
            });
            System.out.println("result : " + execute);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 指定BackOff策略 RecoveryCallback （兜底结果）
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

    /**
     * 兜底回调接口，重试失败后执行的逻辑
     *
     * @param arg1
     * @param arg2
     * @return
     * @throws RuntimeException
     */
    public static Boolean callBack(String arg1, Integer arg2) throws RuntimeException {
        logger.info("调用 callBack 方法：arg1:" + arg1 + ",arg2:" + arg2);
        return false;
    }
}
