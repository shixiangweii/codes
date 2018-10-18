package com.sxw.test.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-17
 * Time: 17:24
 *
 * @author shixiangweii
 */
public class CacheDemo {

    @Test
    public void test() {
        Cache<String, String> myCache = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                // TTL
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(10000)
                .build();

    }


}
