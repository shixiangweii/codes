package com.sxw.code.spring.cache.ehcache3;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * Description: 通用的读写使用CacheManager
 * User: shixiangweii
 * Date: 2018-08-31
 * Time: 14:34
 *
 * @author shixiangweii
 */
public class CacheManagerDemo {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("preConfigured",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class,
                        String.class,
                        ResourcePoolsBuilder.heap(10))).build();
        cacheManager.init();
        Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
        Cache<Integer, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class,
                        String.class,
                        ResourcePoolsBuilder.heap(10)).build());
        for (int i = 0; i <= 20; i++) {
            //写
            myCache.put(i, "@" + i);
            //读
            String value = myCache.get(i);
            System.out.println("get at " + i + ":" + value);
        }
        cacheManager.removeCache("preConfigured");
        cacheManager.close();
    }
}
