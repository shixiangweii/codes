package com.sxw.test.queue;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-02
 * Time: 19:23
 * https://www.nowcoder.com/questionTerminal/fad21251b285427c99d0d5a5d74ae2fa
 *
 * @author shixiangweii
 */
public class TestQueue {

    @Test
    public void testLinkedBlockingQueue() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        // 运行汇报NPE
        // if (e == null) throw new NullPointerException();
        queue.add(null);
    }

    @Test
    public void testPriorityQueue() {
        // 运行汇报异常
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add(null);
    }
}
