package com.sxw.code.netty.timeserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-31
 * Time: 8:46
 *
 * @author shixiangweii
 */
public class TimeServer {

    public static final int PORT = 8888;

    public static final String HOST = "127.0.0.1";

    /**
     * @throws Exception exp
     */
    private void bind() throws Exception {
        // 服务端NIO线程组（实际上就是Reactor线程组）
        // 接收客户端连接线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 网络读写线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // nio服务端启动辅助类
            ServerBootstrap b = new ServerBootstrap();
            // 设置NioServerSocketChannel，对应jdk中ServerSocketChannel
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 设置TCP参数,backlog 1024
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 绑定I/O事件处理类，类似Reactor模式中Handler
                    .childHandler(new ChildChannelHandler());

            System.out.println("Server waiting port bind...");
            // 执行端口绑定，同步等待成功
            // sync()方法，同步阻塞等待绑定操作完成
            ChannelFuture f = b.bind(PORT).sync();


            System.out.println("Server port bind OK, wait to close...");
            // 等待服务端监听端口关闭
            // 阻塞等待服务器链路关闭，在退出main
            f.channel().closeFuture().sync();
        } finally {
            // 释放线程池资源退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 处理网络“I/O”事件
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            // 添加LineBasedFrameDecoder，StringDecoder，注意顺序
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    /**
     * @param args args
     * @throws Exception exp
     */
    public static void main(String[] args) throws Exception {
        new TimeServer().bind();
        System.out.println("Server end");
    }
}