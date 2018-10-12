package com.sxw.demo.pubsub;

import com.sxw.demo.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * 消息发布方
 *
 * @author yamikaze
 */
public class Publisher {

    static final String CHANNEL_KEY = "channel:message";
    private Jedis jedis;

    private Publisher() {
        jedis = JedisUtil.getJedis();
    }

    private void publishMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return;
        }
        jedis.publish(CHANNEL_KEY, message);
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publishMessage("Hello Redis!" + System.currentTimeMillis());
        publisher.publishMessage("exit");
    }
}
