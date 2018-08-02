package com.sxw.code.netty.privateprotocolnetty;

import com.sxw.code.netty.privateprotocolnetty.codec.NettyMessageDecoder;
import com.sxw.code.netty.privateprotocolnetty.codec.NettyMessageEncoder;
import com.sxw.code.netty.privateprotocolnetty.constant.NettyConstant;
import com.sxw.code.netty.privateprotocolnetty.handler.HeartBeatRespHandler;
import com.sxw.code.netty.privateprotocolnetty.handler.LoginAuthRespHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Description:服务端
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 18:53
 *
 * @author shixiangweii
 */
public class NettyServer {
    public void bind() throws Exception {
        // 接收请求线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 处理网络读写线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());


                        // ch.pipeline().addLast(new NettyMessageDecoder2(1024 * 1024, 4, 4, -8, 0));
                        // ch.pipeline().addLast(new NettyMessageEncoder2());


                        ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                        ch.pipeline().addLast(new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });
        b.bind(NettyConstant.REMOTE_IP, NettyConstant.PORT).sync();
        System.out.println("Netty server start ok...");
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}
