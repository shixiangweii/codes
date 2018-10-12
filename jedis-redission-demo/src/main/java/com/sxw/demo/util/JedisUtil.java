package com.sxw.demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-12
 * Time: 15:27
 *
 * @author shixiangweii
 */
public class JedisUtil {

    private static JedisPool jedisPool;

    static {
        try {
            int maxTotal = 300;
            int maxIdle = 200;
            int maxWait = 10000;
            int port = 6379;
            int timeout = 10000;
            String host = "127.0.0.1";
            String auth = "1q2w3e4r";
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, host, port, timeout, auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void releaseResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

}
