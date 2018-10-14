package com.sxw.demo;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import rx.Observable;
import rx.RxReactiveStreams;

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




        // 下面这种异步流的执行方式，就是一种新的程序的执行路径了把
        // RxJava
        // Reactive Streams

        // reactive client
        RedissonReactiveClient clientReactive = Redisson.createReactive(config);
        RAtomicLongReactive longObjectReactive = clientReactive.getAtomicLong("myLong");
        // 异步流执行方式
        Publisher<Boolean> csPublisher = longObjectReactive.compareAndSet(3, 401);
        csPublisher.subscribe(new Subscriber<Boolean>() {

            String str = "";

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                // 请求
                s.request(1);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("noNext:" + aBoolean);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });


        RMapReactive<String, Integer> map = clientReactive.getMap("mapMap");
        Observable<Integer> observable = RxReactiveStreams.toObservable(map.put("1", 39999));
        observable.subscribe(new rx.Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer);
            }
        });


        Thread.sleep(30 * 1000L);
    }

}
