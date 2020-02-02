package com.sxw.spring.bean.lifecycle;

/**
 * @author shixi
 */
class Counter {
    private static int c = 0;

    static synchronized void log(String text) {
        System.out.println((++c) + ")\t" + text);
    }
}
