package org.zhiyi.coursecode.week3.gateway.channelpool;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import org.zhiyi.coursecode.week3.gateway.outbound.netty4.NettyHttpClientOutboundHandler;

public class NettyChannelPoolHandler implements ChannelPoolHandler {

    ChannelHandlerContext ctx;

    public NettyChannelPoolHandler(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void channelReleased(Channel channel){
        System.out.println("channelReleased. Channel ID: " + channel.id());
    }

    @Override
    public void channelAcquired(Channel channel) {
        System.out.println("channelAcquired. Channel ID: " + channel.id());

    }

    @Override
    public void channelCreated(Channel channel) {

        System.out.println("channelCreated. Channel ID: " + channel.id());
        SocketChannel socketChannel = (SocketChannel) channel;
        socketChannel.config().setKeepAlive(true);
        socketChannel.config().setTcpNoDelay(true);

        socketChannel.pipeline()
        .addLast(new HttpResponseDecoder())
        .addLast(new HttpRequestEncoder())
        .addLast(new HttpObjectAggregator(65536))
        .addLast(new NettyHttpClientOutboundHandler(ctx));
    }
}
