package com.sxw.code.redis;

import com.sxw.code.lambda.countapple.v5.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-01
 * Time: 20:16
 *
 * @author shixiangweii
 */
@Component
public class JedisClient {
    private JedisPool jedisPool;

    @Autowired(required = false)
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Object eval(String script, int keyCount, String... params) {
        return execute(jedis -> jedis.eval(script, keyCount, params));
    }

    private <R> R execute(Function<Jedis, R> function) {
        Jedis resource = null;
        try {
            resource = jedisPool.getResource();
            return function.apply(resource);
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

}
