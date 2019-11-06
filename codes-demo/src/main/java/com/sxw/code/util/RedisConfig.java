package com.sxw.code.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-22
 * Time: 18:56
 *
 * @author shixiangweii
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.timeout}")
    private int redisTimeout;

    @Value("${spring.redis.password}")
    private String redisAuth;

    @Value("${spring.redis.database}")
    private int redisDb;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    /**
     * spring-data-redis 1.x版本 + jedis 2.x (jedis 3.x 不兼容spring-data-redis,3.x移除了redis.client.util包)
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        // 1.x配置连接属性
        jedisConnectionFactory.setTimeout(redisTimeout);
        jedisConnectionFactory.setHostName(redisHost);
        jedisConnectionFactory.setPassword(redisAuth);
        jedisConnectionFactory.setPort(redisPort);
        jedisConnectionFactory.setDatabase(redisDb);
        return jedisConnectionFactory;
    }
}
