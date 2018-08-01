package com.sxw.code.netty.privateprotocolnetty.protocal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description: 消息数据结构——控制类消息：心跳、握手请求/应答，也使用这个数据结构
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 11:00
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public final class NettyMessage {
    /**
     * 消息头
     */
    private Header header;
    /**
     * 消息体
     */
    private Object body;
}
