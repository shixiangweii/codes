package com.sxw.demo.mq;


import com.sxw.demo.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * 消息生产者
 *
 * @author yamikaze
 */
public class Producer extends Thread {

    static final String MESSAGE_KEY = "message:queue";
    private Jedis jedis;
    private String producerName;
    private volatile int count;

    private Producer(String name) {
        this.producerName = name;
        init();
    }

    private void init() {
        jedis = JedisUtil.getJedis();
    }

    private void putMessage(String message) {
        Long size = jedis.lpush(MESSAGE_KEY, message);
        System.out.println(producerName + ": 当前未被处理消息条数为:" + size);
        count++;
    }

    private int getCount() {
        return count;
    }

    @Override
    public void run() {
        try {
            while (true) {
                putMessage(System.currentTimeMillis() + "-msg");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer("myProducer");
        producer.start();

        for (; ; ) {
            System.out.println("main : 已存储消息条数:" + producer.getCount());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
