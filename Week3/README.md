# 第3周作业

准备工作：业务API可以使用[NettyHttpServer.java](https://github.com/muyi42129/Geek-CourseCodes/blob/main/Week3/src/main/java/org/zhiyi/coursecode/week3/NettyHttpServer.java)，模拟2个服务：

* http://localhost:8801     http://localhost:8802
---

1.（必做）整合你上次作业的 httpclient/okhttp；

* [HttpClientHandler.java](https://github.com/muyi42129/Geek-CourseCodes/blob/main/Week3/src/main/java/org/zhiyi/coursecode/week3/gateway/outbound/httpclient4/HttpClientHandler.java)
* 网关程序中使用的NettyHttpClient，没有使用这个Client
---
2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
* [NettyHttpClient.java](https://github.com/muyi42129/Geek-CourseCodes/blob/main/Week3/src/main/java/org/zhiyi/coursecode/week3/gateway/outbound/netty4/NettyHttpClient.java)
---
3.（必做）实现过滤器。
* [HeaderHttpRequestFilter.java](https://github.com/muyi42129/Geek-CourseCodes/blob/main/Week3/src/main/java/org/zhiyi/coursecode/week3/gateway/filter/HeaderHttpRequestFilter.java)
---
4.（选做）实现路由。
* [RoundRibbonHttpEndpointRouter.java](https://github.com/muyi42129/Geek-CourseCodes/blob/main/Week3/src/main/java/org/zhiyi/coursecode/week3/gateway/router/RoundRibbonHttpEndpointRouter.java)
---
5.（选做）跑一跑课上的各个例子，加深对多线程的理解

---
6.（选做）完善网关的例子，试着调整其中的线程池参数

---
