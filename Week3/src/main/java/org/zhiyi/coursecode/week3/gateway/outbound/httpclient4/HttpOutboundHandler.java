package org.zhiyi.coursecode.week3.gateway.outbound.httpclient4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.zhiyi.coursecode.week3.gateway.filter.HeaderHttpResponseFilter;
import org.zhiyi.coursecode.week3.gateway.filter.HttpRequestFilter;
import org.zhiyi.coursecode.week3.gateway.filter.HttpResponseFilter;
import org.zhiyi.coursecode.week3.gateway.outbound.netty4.NettyHttpClient;
import org.zhiyi.coursecode.week3.gateway.router.HttpEndpointRouter;
import org.zhiyi.coursecode.week3.gateway.router.RoundRibbonHttpEndpointRouter;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HttpOutboundHandler {

    private static NettyHttpClient nettyHttpClient;
    private static ExecutorService proxyService;
    private List<String> backendUrls; //http://host:port

    static {
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();

        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RoundRibbonHttpEndpointRouter();
    public HttpOutboundHandler(List<String> backends) {
        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
        if (null == nettyHttpClient) {
            nettyHttpClient = new NettyHttpClient();
        }
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
    
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        filter.filter(fullRequest, ctx);
        proxyService.submit(()->fetchGet(fullRequest, ctx, backendUrl));
    }
    
    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        String[] hosts = url.substring(url.indexOf("//") +2).split(":");
        nettyHttpClient.connect(hosts[0], Integer.parseInt(hosts[1]), inbound.uri(), ctx);
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    
}
