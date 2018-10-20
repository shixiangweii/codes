package com.sxw.test.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Test
    public void testFailCache() {
        LoadingCache<String, AtomicInteger> failedCache =
                CacheBuilder.newBuilder().softValues()
                        .maximumSize(10000)
                        .build(new CacheLoader<String, AtomicInteger>() {
                            @Override
                            public AtomicInteger load(String key) throws Exception {
                                return new AtomicInteger(0);
                            }
                        });
    }

}
