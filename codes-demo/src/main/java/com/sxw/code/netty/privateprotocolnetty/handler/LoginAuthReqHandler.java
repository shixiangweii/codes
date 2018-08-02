package com.sxw.code.netty.privateprotocolnetty.handler;

import com.sxw.code.netty.privateprotocolnetty.protocal.Header;
import com.sxw.code.netty.privateprotocolnetty.protocal.MessageType;
import com.sxw.code.netty.privateprotocolnetty.protocal.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Description: 客户端发送握手请求，登陆请求
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 15:17
 *
 * @author shixiangweii
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 激活
     * 发起握手（登陆）请求
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyMessage msg = buildLoginReq();
        System.out.println("Channel active send LOGIN_REQ, type " + msg.getHeader().getType());
        ctx.writeAndFlush(msg);
    }

    /**
     * 解析 login resp
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        System.out.print("Channel read msg : " + msg);
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            System.out.println(" , is LOGIN_RESP, body : " + message.getBody());
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                ctx.close();
            } else {
                System.out.println("Login is ok : " + message);
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
}
