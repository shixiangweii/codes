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
 * 通用方法
 * 行为参数接口
 * 上下文
 * 调用者的自己个性化的参数
 * 调用者调用
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
        // script, keyCount, params ，这总就是调用者自己的个性化的参数，把其抽象，
        // 把整个行为，看成"Function"，
        // 其实封装后又2种参数，一种是每个调用者独特的个性化的参数，
        // 还一种就是通用方法种的，抽取的共用的参数
        // 其实就是想传递一段代码做参数，而其中比较又意思的一点是，
        // script, keyCount, params，这种参数，可以在匿名类种被访问，
        // 而lambda其实关注的是具体的动作，

        // 其实这里，把Jedis作为行为参数的接口中的一个参数，其实这个接口也是具体问题，具体分析
        return execute(jedis -> jedis.eval(script, keyCount, params));
    }

    private <R> R execute(Function<Jedis, R> function) {
        // 先观察比较所有的操作，抽取出一个通用的步骤
        // 在从这个通用的步骤种，找出需要行为参数化的部分
        // 然后针对这个行为参数化，设计接口，定义参数，究竟应该怎么传
        // 其实还是和设计类，和抽象函数一样，第一步还是找出看所有的逻辑，找出相同的逻辑，并进行抽象
        // 找相同点，在不同种找出相同点
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
