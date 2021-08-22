package org.zhiyi.coursecode.week3.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

/**
 * 负责调用业务API
 * Netty实现HttpClient
 */
public class NettyHttpClient {


    public void connect(String ip, int port, String uri, ChannelHandlerContext ctx) {

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //1.用户线程创建Bootstrap
            //Bootstrap是Socket客户端创建工具类，通过API设置创建客户端相关的参数，异步发起客户端连接。
            Bootstrap b = new Bootstrap();
            //2.指定处理客户端连接、IO读写的Reactor线程组NioEventLoopGroup。可以通过构造函数指定I/O线程的个数，默认为CPU内核数的2倍。
            b.group(workerGroup);
            //3.创建用于客户端连接的NioSocketChannel
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            //4.创建默认的channel Handler pipeline，用于调度和执行网络事件。
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
//                     客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpObjectAggregator(65536));
                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(ctx));
                }
            });
            // Start the client.
            ChannelFuture f = b.connect(ip, port).sync();
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri);

            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}