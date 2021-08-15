package org.zhiyi.coursecode.week2.nio;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpClient01 {

    public static void main(String[] args) throws IOException{

        HttpGet httpGet = new HttpGet("http://localhost:8801");
        httpGet.setConfig(RequestConfig.custom()
                .setRedirectsEnabled(false)
                .setConnectTimeout(5000)
                .setSocketTimeout(2000)
                .build());

        CloseableHttpClient httpclient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(2, false))// 设置重试策略
                .build();

        CloseableHttpResponse httpResponse = null;
        try {
            /*
            I/O exception (java.net.SocketException) caught when processing request to {}->http://localhost:8801: Software caused connection abort: recv failed
            这里偶尔会抛出异常：Software caused connection abort: recv failed;
            但通过重试后仍能成功Retrying request to {}->http://localhost:8801;
            目前的解决方案是设置重试策略，当抛出IOException时HttpClient默认的重试策略重试次数是3次。
             */
            httpResponse = httpclient.execute(httpGet);
            System.out.println(httpResponse.getProtocolVersion());              // HTTP/1.1
            System.out.println(httpResponse.getStatusLine().getStatusCode());   // 200
            System.out.println(httpResponse.getStatusLine().getReasonPhrase()); // OK
            System.out.println(httpResponse.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                System.out.println("==>" + EntityUtils.toString(httpEntity));
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }

    }

}