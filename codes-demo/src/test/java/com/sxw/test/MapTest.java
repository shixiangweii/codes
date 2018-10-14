package com.sxw.test;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-03
 * Time: 11:06
 *
 * @author shixiangweii
 */
public class MapTest {

    int max = 3000000;
    ConcurrentMap<String, String> mark = new ConcurrentHashMap<>(max);

    @Test
    public void mapTest() {
        // 300万还是很快的
        int max = 3000000;
        ConcurrentMap<String, String> mark = new ConcurrentHashMap<>(max);
        for (int i = 0; i < max; i++) {
            mark.put(i + "-oHV1V0UWQy29k8fU-xgkXL4N7A-E", "oHV1V0UWQy29k8fU-xgkXL4N7A-E");
        }
        System.out.println(mark.size());
    }

    @Test
    public void testSyncByMark() {
        String userMark = "shixiangweii789";


        // set nx
        String syncMark = mark.putIfAbsent(userMark, userMark);

        if (syncMark == null) {
            syncMark = userMark;
        } else {
            syncMark = mark.get(userMark);
        }

        synchronized (syncMark) {
            System.out.println("start");
            System.out.println("end");
        }

    }

    /**
     * （一）await是及物动词，后面直接接宾语；wait虽然也可用作及物动词，但在现代英语中，一般作不及物动词用，与for，to，till，until等词连用。
     * （二）await的宾语大都是抽象名词，如：decision，reply，arrival，announcement，return等；wait for的宾语一般是人或事物。
     */
    @Test
    public void testMapSync() throws Exception {
        int threadNum = 100;

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch resGetLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(new TestThread(countDownLatch, "sxw", resGetLatch), "t-" + i);
            t.start();
        }

        //Thread.sleep(3000L);

        countDownLatch.countDown();

        // 一定要注意这里最后等待结果的 睡眠时间，
        // 因为那边sleep 10ms
        // 而这里一共 100个线程，所以 100*10 ，一共睡 1000ms，1s
        // 所以这里3000ms还是可以满足的
        // Thread.sleep(3000L);

        resGetLatch.await();

        System.out.println(MapSync.get());
    }

}

class TestThread implements Runnable {

    private CountDownLatch countDownLatch;
    private String name;
    private CountDownLatch resCount;

    public TestThread(CountDownLatch countDownLatch, String name, CountDownLatch resCount) {
        this.countDownLatch = countDownLatch;
        this.name = name;
        this.resCount = resCount;
    }

    @Override
    public void run() {
        try {
            //System.out.println(Thread.currentThread().getName() + " wait...");
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MapSync.sum(name);
        //MapSync.synSum(name);

        // 执行结束
        resCount.countDown();
    }
}
