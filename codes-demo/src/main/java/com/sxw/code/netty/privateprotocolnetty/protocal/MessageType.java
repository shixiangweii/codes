package com.sxw.code.netty.privateprotocolnetty.protocal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 15:30
 *
 * @author shixiangweii
 */
@Getter
@AllArgsConstructor
public enum MessageType {
    /**
     * login req
     * 握手请求
     * 登陆
     */
    LOGIN_REQ((byte) 3),
    /**
     * login resp
     * 握手应答
     * 登陆反馈
     */
    LOGIN_RESP((byte) 4),
    /**
     * 心跳请求
     */
    HEARTBEAT_REQ((byte) 5),
    /**
     * 心跳响应
     */
    HEARTBEAT_RESP((byte) 6);

    private byte value;

    public byte value() {
        return this.getValue();
    }
}
