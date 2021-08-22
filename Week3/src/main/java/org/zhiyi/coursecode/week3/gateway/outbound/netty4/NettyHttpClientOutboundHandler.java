package org.zhiyi.coursecode.week3.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {

    ChannelHandlerContext ctx; //回写xtx

    public NettyHttpClientOutboundHandler(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("readComplete.. begin response handle");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx1, Object msg) throws Exception {

        HttpContent content = (HttpContent)msg;
        ByteBuf buf = content.content();
        FullHttpResponse response = null;
        try {
            byte[] body = buf.toString(io.netty.util.CharsetUtil.UTF_8).getBytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", body.length);

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            //response.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.write(response);
            ctx.flush();
            ctx1.close();
        }
    }

}