package com.sxw.code.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-01
 * Time: 20:11
 *
 * @author shixiangweii
 */
@Service
public class RedisClusterService {
    private JedisCluster jedisCluster;

    @Autowired(required = false)
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    /**
     * 像集群这种就不用再用行为参数封装
     *
     * @param script
     * @param keyCount
     * @param params
     * @return
     */
    public Object eval(String script, int keyCount, String... params) {
        return jedisCluster.eval(script, keyCount, params);
    }
}
