package com.sxw.code.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://www.jianshu.com/p/ae67972d1156
 * 4大常用线程池的 ThreadPoolExecutor写法
 * Description:
 * User: shixiangweii
 * Date: 2018-12-24
 * Time: 14:17
 * <p>
 * 当一个任务被添加进线程池时，执行策略：
 * <p>
 * 1.线程数量未达到corePoolSize，则新建一个线程(核心线程)执行任务
 * 2.线程数量达到了corePools，则将任务移入队列等待
 * 3.队列已满，新建线程(非核心线程)执行任务
 * 4.队列已满，总线程数又达到了maximumPoolSize，就会由(RejectedExecutionHandler)抛出异常
 * <p>
 * corePoolSize           核心线程数最大值
 * maximumPoolSize        线程总数最大值
 * <p>
 * keepAliveTime          非核心线程闲置超时时长
 * unit                   keepAliveTime的单位
 * <p>
 * workQueue
 * BlockingQueue
 * SynchronousQueue
 * LinkedBlockingQueue
 * ArrayBlockingQueue
 * DelayQueue
 * threadFactory          创建线程的方式，主要用于线程命名
 * RejectedExecutionHandler 丢弃策略
 *
 * @author shixiangweii
 */
public class ThreadPoolUtils {

    public static ThreadPoolExecutor getOneSingleThreadPool(String threadNamePrefix) {
        return getOneFixedThreadPool(threadNamePrefix, 1);
    }

    public static ThreadPoolExecutor getOneFixedThreadPool(String threadNamePrefix, int num) {
        return new ThreadPoolExecutor(num, num,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> new Thread(r, threadNamePrefix)
        );
    }

}
