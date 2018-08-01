package com.sxw.code.netty.privateprotocolnetty.handler;

import com.sxw.code.netty.privateprotocolnetty.protocal.Header;
import com.sxw.code.netty.privateprotocolnetty.protocal.MessageType;
import com.sxw.code.netty.privateprotocolnetty.protocal.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 17:33
 *
 * @author shixiangweii
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatTask(ctx), 0, 5000, TimeUnit.SECONDS
            );
        } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("Client receive server heart beat message : ---> " + message);
        } else {
            // 将一个event向前递交给下一个相邻的handler
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable {
        /**
         * 对子线程中传入主中的一些参数一定要用final
         */
        private final ChannelHandlerContext ctx;

        /**
         * 构造参数中的也要用final
         *
         * @param ctx
         */
        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeartBeat();
            System.out.println("Client send heart beat message to server : ---> " + heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeartBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            message.setHeader(header);
            header.setType(MessageType.HEARTBEAT_REQ.value());
            return message;
        }
    }

}
