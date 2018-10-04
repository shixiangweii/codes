package com.sxw.demo;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-04
 * Time: 15:27
 *
 * @author shixiangweii
 */
public class TestLock {

    @Test
    public void testLock() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("1q2w3e4r");
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
        lock.lock(30, TimeUnit.SECONDS);
        System.out.println("get lock1");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    @Test
    public void testLock2() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("1q2w3e4r");
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
        lock.lock(10, TimeUnit.SECONDS);
        System.out.println("get lock2");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }
}
