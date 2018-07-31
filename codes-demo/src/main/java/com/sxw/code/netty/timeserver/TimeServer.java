package com.sxw.code.netty.timeserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-31
 * Time: 8:46
 *
 * @author shixiangweii
 */
public class TimeServer {
    /**
     * @param port
     * @throws Exception
     */
    private void bind(int port) throws Exception {
        // 服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            System.out.println("Server waiting port bind...");
            // 执行端口绑定，同步等待成功
            ChannelFuture f = b.bind(port).sync();


            System.out.println("Server port bind OK, wait to close...");
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 释放线程池资源退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     *
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new TimeServer().bind(8888);
        System.out.println("Server end");
    }
}