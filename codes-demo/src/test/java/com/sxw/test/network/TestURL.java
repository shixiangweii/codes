package com.sxw.test.network;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-24
 * Time: 17:53
 *
 * @author shixiangweii
 */
public class TestURL {

    /**
     * https://www.nowcoder.com/questionTerminal/566c5439ab04493c9c683a01430d697e
     * @throws MalformedURLException
     */
    @Test
    public void testURL() throws MalformedURLException {
        // 抛出异常，但是这个异常属于IOException，不管网址是否存在，最后都会返回该网址的一个连接，打印出来就是该网址
        URL u =new URL("http://www.123SXWD.com");
        System.out.println(u);
    }
}
