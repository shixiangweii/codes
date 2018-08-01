package com.sxw.code.netty.privateprotocolnetty.handler;

import com.sxw.code.netty.privateprotocolnetty.protocal.Header;
import com.sxw.code.netty.privateprotocolnetty.protocal.MessageType;
import com.sxw.code.netty.privateprotocolnetty.protocal.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 18:38
 *
 * @author shixiangweii
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("Receive client heart beat message : ---> " + message);
            NettyMessage heartBeat = buildHeatBeat();
            System.out.println("Send heart beat response message to client : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
