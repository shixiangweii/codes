package com.sxw.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-14
 * Time: 17:47
 *
 * @author shixiangweii
 */
public class MapSync {

    private static long counter = 0L;

    private static final ConcurrentMap<String, String> LOCK_MAP = new ConcurrentHashMap<>(300);

    public static void sum(String name) {
        int c = (int) counter;
        c += 2;
        try {
            // 放大线程不安全的效果，睡眠10ms
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter = c;
    }

    public static void synSum(String name) {
        String key = LOCK_MAP.get(name);
        if (key == null) {
            String res = LOCK_MAP.putIfAbsent(name, name);
            if (res == null) {
                key = name;
            } else {
                key = res;
            }
        }
        synchronized (key) {
            sum(name);
        }
    }

    public static long get() {
        return counter;
    }
}
