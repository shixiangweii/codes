package com.sxw.code.spring.cache.ehcache3;

import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.UserManagedCacheBuilder;

/**
 * Description: 3.0的读写新泛型方法UserManagedCache
 * User: shixiangweii
 * Date: 2018-08-31
 * Time: 14:41
 *
 * @author shixiangweii
 */
public class UserManagedCacheDemo {

    public static void main(String[] args) {
        UserManagedCache<Integer, String> userManagedCache = UserManagedCacheBuilder.newUserManagedCacheBuilder(
                Integer.class, String.class).build(false);
        userManagedCache.init();

        for (int i = 0; i <= 20; i++) {
            //写
            userManagedCache.put(i, "#" + i);
            //读
            String value = userManagedCache.get(i);
            System.out.println("get at " + i + ":" + value);
        }

        userManagedCache.close();
    }
}
