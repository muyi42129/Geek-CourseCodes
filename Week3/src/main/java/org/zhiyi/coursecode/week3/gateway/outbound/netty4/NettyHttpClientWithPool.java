package org.zhiyi.coursecode.week3.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.zhiyi.coursecode.week3.gateway.channelpool.NettyChannelPoolHandler;

import java.net.InetSocketAddress;

/**
 * 负责调用业务API
 * Netty实现HttpClient
 */
public class NettyHttpClientWithPool {

    final Bootstrap strap = new Bootstrap();
    ChannelPoolMap<InetSocketAddress, FixedChannelPool> poolMap;

    public NettyHttpClientWithPool() {
        strap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);
    }

    public void connect(String ip, int port, String uri, ChannelHandlerContext ctx) {
        InetSocketAddress key = new InetSocketAddress(ip, port);
        System.out.println("contains key ： " + this.poolMap.contains(key));
        // depending on key, get different pools.
        final FixedChannelPool pool = this.poolMap.get(key);
        Future<Channel> f = pool.acquire();
        f.addListener((FutureListener<Channel>) f1 -> {
            if (f1.isSuccess()) {
                Channel ch = f1.getNow();
                DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri);
                ch.write(request);
                ch.flush();
                // Release back to pool
                pool.release(ch);
            }
        });
    }

    public void build(ChannelHandlerContext ctx) {
//        if (poolMap != null) {
//            return;
//        }
        try {
            poolMap = new AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool>() {
                @Override
                protected FixedChannelPool newPool(InetSocketAddress key) {
                    return new FixedChannelPool(strap.remoteAddress(key), new NettyChannelPoolHandler(ctx), 2);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}