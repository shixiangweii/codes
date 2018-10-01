package com.sxw.code.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.function.Function;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-01
 * Time: 18:53
 *
 * @author shixiangweii
 */
@Service
public class RedisService {
    /**
     * 上下文
     */
    ShardedJedisPool shardedJedisPool;

    @Autowired(required = false)
    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    public String set(String key, String value) {
        /*
        return execute(new Function<JedisCommands, String>() {

            public String apply(JedisCommands shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
        */

        return execute(cmd -> cmd.set(key, value));
    }

    public String get(String key) {
        // caller的参数
        return execute(cmd -> cmd.get(key));
    }

    public Long setnx(String key, String value) {
        // caller的参数
        return execute(cmd -> cmd.setnx(key, value));
    }

    /**
     * Role-1 通用方法
     *
     * @param function 行为参数
     * @param <R>
     * @return
     */
    private <R> R execute(Function<JedisCommands, R> function) {
        ShardedJedis shardedJedis = null;
        try {
            // 上下文
            shardedJedis = shardedJedisPool.getResource();
            return function.apply(shardedJedis);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }
}
