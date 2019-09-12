package com.sxw.code.concurrent.condition;

/**
 * @author shixiangweii
 * @date 2019/9/12 22:06
 */
public class MultiSyncDemo {
    private int[] arr = new int[10];
    private int cur = 0;
    private boolean doResize;
    private int count = 0;
    private volatile boolean stop = false;

    /**
     * 加入
     */
    public synchronized void add() {
        while (cur == arr.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cur++;
        if (cur == arr.length) {
            count++;
        }
        if (count > 0 && count % 10 == 0) {
            doResize = true;
        }
        this.notifyAll();
    }

    /**
     * 移除
     */
    public synchronized void remove() {
        while (cur == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cur--;
        this.notifyAll();
    }

    /**
     * 扩容
     * 这边最大的问题就是,resize的条件是doResize，但是doResize要满10次，才会达到条件
     * 这意味着，add,remove释放的锁notify，也会唤醒resize的等待，但是这个时候，很大可能，doResize还是false
     * 这就是无谓的打断
     */
    public synchronized void resize() {
        do {
            while (!doResize) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int[] temp = new int[arr.length * 2];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
            this.notifyAll();
        } while (!stop);
    }
}
