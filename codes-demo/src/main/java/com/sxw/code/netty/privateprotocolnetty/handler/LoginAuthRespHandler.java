package com.sxw.code.netty.privateprotocolnetty.handler;

import com.sxw.code.netty.privateprotocolnetty.protocal.Header;
import com.sxw.code.netty.privateprotocolnetty.protocal.MessageType;
import com.sxw.code.netty.privateprotocolnetty.protocal.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 服务端响应登陆请求
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 15:54
 *
 * @author shixiangweii
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

    private String[] whiteList = {"127.0.0.1"};

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 清空保存的认证信息缓存
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    /**
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        System.out.println("LoginAuthRespHandler get : " + message);

        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {

            System.out.println("Get LOGIN RESP");

            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp;
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean[] isOk = {false};
                Arrays.stream(whiteList).forEach(wip -> {
                    if (!isOk[0] && wip.equals(ip)) {
                        isOk[0] = true;
                    }
                });
                loginResp = isOk[0] ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isOk[0]) {
                    nodeCheck.put(nodeIndex, true);
                }
            }
            System.out.println("The login res is : " + loginResp + " body [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
