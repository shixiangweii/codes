package com.sxw.code.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 有界队列
 * User: shixiangweii
 * Date: 2018-08-25
 * Time: 12:36
 *
 * @author shixiangweii
 */
public class BoundedQueue<T> {
    private Object[] items;

    private int addIndex, removeIndex, count;

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        // 手动枷锁
        lock.lock();
        // try-finally
        try {
            // 使用while等待条件不成立
            while (count == items.length) {
                // 在“队列未满”的条件上等待
                notFull.await();
            }
            // 因为lock所以这些成员字段都有可见性
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            // 通知，释放不为空的信号
            notEmpty.signal();
        } finally {
            // 最后都解锁
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }
}
