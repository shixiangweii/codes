package com.sxw.code.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shixiangweii
 * @date 2019/9/12 22:02
 */
public class MultiConditionDemo {
    /**
     * 这些条件，是上层的业务条件，是当前这个组件自身逻辑的条件
     */
    private int[] arr = new int[10];
    private int cur = 0;
    private boolean doResize;
    private int count = 0;
    private volatile boolean stop = false;

    /**
     * 锁，这个锁是用来保证，上面一堆多线程共享字段的，可见性、原子性，线程安全
     */
    private Lock lock = new ReentrantLock();
    /**
     * 下面的“条件”，是线程，等待-唤醒的条件，用于控制线程同步的
     */
    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    private Condition canResize = lock.newCondition();

    /**
     * 加入
     * 不是满的才能加入
     */
    public void add() {
        lock.lock();
        try {
            while (cur == arr.length) {
                try {
                    // 等待不满的条件成立
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cur++;
            if (cur == arr.length) {
                count++;
            }
            if (count > 0 && count % 10 == 0) {
                // 扩展的条件成立l
                canResize.signalAll();
            }
            // 不空的条件成立了
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 移除
     */
    public void remove() {
        lock.lock();
        try {
            while (cur == 0) {
                try {
                    // 等待不空的条件成立
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cur--;
            // 不满的条件成立了
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 扩容
     * 用Condition多条件，就可以只有确实满足了doResize才会唤醒，执行resize
     */
    public void resize() {
        do {
            while (!doResize) {
                try {
                    // 等待扩展的条件成立
                    canResize.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int[] temp = new int[arr.length * 2];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
            // 不满的条件成立了
            notFull.signalAll();
        } while (!stop);
    }
}
