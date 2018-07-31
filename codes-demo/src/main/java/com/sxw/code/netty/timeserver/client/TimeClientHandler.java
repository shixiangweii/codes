package com.sxw.code.netty.timeserver.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-31
 * Time: 9:30
 *
 * @author shixiangweii
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf firstMessage;

    TimeClientHandler() {
        byte[] req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // todo 直接在这个事件里，用Thread.sleep，循环，会报错“msg cannot release” 异常
        ctx.writeAndFlush(firstMessage);
    }

    /**
     * 使用基础的解析方式
     * ByteBuf buf = (ByteBuf) msg;
     * byte[] req = new byte[buf.readableBytes()];
     * buf.readBytes(req);
     * System.out.println("Now is : " + new String(req, "UTF-8"));
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Now is : " + msg);
    }

}
