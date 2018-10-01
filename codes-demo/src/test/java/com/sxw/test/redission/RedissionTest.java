package com.sxw.test.redission;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-01
 * Time: 18:04
 *
 * @author shixiangweii
 */
public class RedissionTest {

    /**
     * netty 版本冲突
     * isIpV4StackPreferred
     */
    @Test
    public void test() {
        Config config = new Config();

        // config.setTransportMode(TransportMode.EPOLL);
        config.useClusterServers()
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

        RAtomicLong longObject = client.getAtomicLong("myLong");
        // 同步执行方式
        longObject.compareAndSet(3, 401);
        // 异步执行方式
        longObject.compareAndSetAsync(3, 401);



        RedissonReactiveClient clientReactive = Redisson.createReactive(config);
        RAtomicLongReactive longObjectReactive = clientReactive.getAtomicLong("myLong");
        // 异步流执行方式
        longObject.compareAndSet(3, 401);
    }
}
