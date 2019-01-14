package com.sxw.test.lock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * https://www.nowcoder.com/questionTerminal/134aea222e0c4add9a9b0c63267a5e3c
 * http://ifeve.com/java_lock_see/
 *
 * User: shixiangweii
 * Date: 2019-01-13
 * Time: 11:12
 *
 * @author shixiangweii
 */
public class TestLock {
    /**
     * 自旋锁
     *
     * 自旋锁以及Java中的自旋锁的实现
     *
     */
    @Test
    public void testAtomic() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // atomicInteger类中的getAndAdd
        // 当前获取锁的线程，执行更新的方法，其他线程自旋等待
        atomicInteger.getAndSet(10);
        System.out.println(atomicInteger);
    }

    /**
     * http://www.cnblogs.com/mmmmar/p/8624242.html
     * CAS（Compare And Swap）是一种常见的“乐观锁”
     */
    @Test
    public void testCAS() {

    }
}
