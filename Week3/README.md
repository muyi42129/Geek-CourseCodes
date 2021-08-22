# 第3周作业

1.（必做）整合你上次作业的 httpclient/okhttp；

这里使用直接从Apache HttpClient 转换成了NettyClient ，HttpClient周一提交上来。

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
