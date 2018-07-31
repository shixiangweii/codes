package com.sxw.code.netty.timeserver.client;

import com.sxw.code.netty.timeserver.TimeServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-31
 * Time: 9:29
 * 2018-07-31 11:18:17 [nioEventLoopGroup-2-1] WARN  ChannelOutboundBuffer:line:151 - Failed to release a message.
 * io.netty.util.IllegalReferenceCountException: refCnt: 0, decrement: 1
 * at io.netty.buffer.AbstractReferenceCountedByteBuf.release(AbstractReferenceCountedByteBuf.java:115) ~[netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.util.ReferenceCountUtil.release(ReferenceCountUtil.java:68) ~[netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.ChannelOutboundBuffer.safeRelease(ChannelOutboundBuffer.java:559) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.ChannelOutboundBuffer.remove(ChannelOutboundBuffer.java:338) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.ChannelOutboundBuffer.failFlushed(ChannelOutboundBuffer.java:498) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.AbstractChannel$AbstractUnsafe.flush0(AbstractChannel.java:654) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe.flush0(AbstractNioChannel.java:271) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.AbstractChannel$AbstractUnsafe.flush(AbstractChannel.java:621) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.DefaultChannelPipeline$HeadHandler.flush(DefaultChannelPipeline.java:1065) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.ChannelHandlerInvokerUtil.invokeFlushNow(ChannelHandlerInvokerUtil.java:148) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.DefaultChannelHandlerInvoker$14.run(DefaultChannelHandlerInvoker.java:314) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:318) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:353) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:794) [netty-all-5.0.0.Alpha1.jar:5.0.0.Alpha1]
 * at java.lang.Thread.run(Thread.java:745) [?:1.8.0_65]
 * <p>
 * <p>
 * 草泥马，貌似纠结的了半天就是一个message不能重复使用的问题，草草草
 *
 * @author shixiangweii
 */
public class TimeClient {

    /**
     * 单个线程的线程池
     */
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            r -> new Thread(r, "td-time-client" + r.hashCode()));

    private void connect() throws Exception {
        // 客户端线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            // 像这种泛型的接口，lambda还是不支持的
            ChannelHandler handler = new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            };
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(handler);
            // 执行连接操作，同步等待成功
            // 发起异步连接b.connect(host, port)
            // 同步等待成功.sync()
            System.out.println("Client waiting connect...");
            ChannelFuture f = b.connect(TimeServer.HOST, TimeServer.PORT).sync();
            THREAD_POOL_EXECUTOR.execute(new ClientSend(f, 5));
            // 等待客户端链路关闭
            System.out.println("Client connect OK, wait to close...");
            // 阻塞了
            f.channel().closeFuture().sync();
        } finally {
            // 释放线程组退出
            group.shutdownGracefully();
        }
    }

    /**
     * 尝试了，各种，main下，channelActive下，connect中开线程池都会报错，异常"Failed to release a message"
     *
     * @param args args
     * @throws Exception exp
     */
    public static void main(String[] args) throws Exception {
        // 因为实例化后就阻塞了，所以这一下的代码不会执行
        new TimeClient().connect();
    }
}

class ClientSend implements Runnable {

    private final ChannelFuture channelFuture;

    private int times;

    ClientSend(ChannelFuture channelFuture, int times) {
        this.channelFuture = channelFuture;
        this.times = times;
    }

    @Override
    public void run() {
        Channel channel = channelFuture.channel();
        for (int i = 0, interval = 1; i < times; i++) {
            try {
                byte[] req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
                ByteBuf message = Unpooled.buffer(req.length);
                message.writeBytes(req);
                channel.writeAndFlush(message);
                Thread.sleep(interval * 100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}