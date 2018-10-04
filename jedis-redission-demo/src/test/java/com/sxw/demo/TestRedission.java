package com.sxw.demo;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.CompletionStage;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-03
 * Time: 20:57
 *
 * @author shixiangweii
 */
public class TestRedission {

    private static Void apply(Throwable exception) {

        return null;
    }

    @Test
    public void testConfig() throws Exception {
        Config config = new Config();

        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("1q2w3e4r");

        // 普通的client
        RedissonClient client = Redisson.create(config);
        RAtomicLong longObject = client.getAtomicLong("myLong");
        // 同步执行方式
        boolean b = longObject.compareAndSet(3, 401);
        System.out.println(b);

        // 异步执行方式
        RFuture<Boolean> booleanRFuture = longObject.compareAndSetAsync(3, 401);
        booleanRFuture.whenComplete((res, exception) -> System.out.println(res));

        // 这样弄感觉又点像jquery.ajax，回调那种编程方式
        booleanRFuture = longObject.compareAndSetAsync(3, 401);

        CompletionStage<Void> completionStage = booleanRFuture.thenAccept(res -> {
            // 处理返回

        }).exceptionally(TestRedission::apply);


        // reactive client
        RedissonReactiveClient clientReactive = Redisson.createReactive(config);
        RAtomicLongReactive longObjectReactive = clientReactive.getAtomicLong("myLong");
        // 异步流执行方式
        longObjectReactive.compareAndSet(3, 401);

    }

}
