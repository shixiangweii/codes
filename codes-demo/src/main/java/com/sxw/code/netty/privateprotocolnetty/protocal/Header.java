package com.sxw.code.netty.privateprotocolnetty.protocal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 11:01
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public final class Header {
    /**
     * 16进制
     * abef 标识为“Netty”协议  ABEF
     * 01  主版本              00000001
     * 01  次版本              00000001
     */
    private int crcCode = 0xabef0101;
    /**
     * 消息长度
     */
    private int length;
    /**
     * 会话ID
     */
    private long sessionID;
    /**
     * 消息类型
     */
    private byte type;
    /**
     * 消息优先级
     */
    private byte priority;
    /**
     * 附件
     */
    private Map<String, Object> attachment = new HashMap<>(16);
}
