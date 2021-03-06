package com.sxw.code.netty.timeserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-31
 * Time: 8:50
 *
 * @author shixiangweii
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    /**
     * 使用基础的解析的方式
     * <p>
     * // 类似jdk中ByteBuffer
     * ByteBuf buf = (ByteBuf) msg;
     * byte[] req = new byte[buf.readableBytes()];
     * buf.readBytes(req);
     * String body = new String(req, "UTF-8");
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 使用StringDecoder，可以直接转String
        String body = msg.toString();
        System.out.println("Receive order : " + body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
        // 使用LineBasedFrameDecoder， 发送的消息要加上“\r\n”
        ByteBuf resp = Unpooled.copiedBuffer((currentTime + System.getProperty("line.separator")).getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 发送缓冲区中的消息全部写到SocketChannel
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭ctx，释放和ctx相关联的句柄等资源
        ctx.close();
    }
}
