package com.liuning.web.http;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * HttpClient连接池管理
 *
 * @author liuning
 * @since 2020-04-15
 */
public class HttpClientPool {

    private static CloseableHttpClient httpClient = null;

    //从连接池中获取连接超时时间
    private static final int connectionRequestTimeout = 5000;
    //建立连接超时时间
    private static final int connectionTimeout = 5000;
    //等待响应超时时间
    private static final int socketTimeout = 30000;

    //总最大连接数
    static final int maxTotal= 500;
    //每条线路最大连接数 = 本系统核心线程数 , 这样永远不会超过最大连接
    static final int defaultMaxPerRoute= 100;

    public static CloseableHttpClient getHttpClient() {
        if (null == httpClient) {
            synchronized (HttpClientPool.class) {
                if (null == httpClient) {
                    httpClient = init();
                }
            }
        }
        return httpClient;
    }

    private static CloseableHttpClient init() {

        // 设置连接池
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf).register("https", sslsf)
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 配置最大连接数
        cm.setMaxTotal(maxTotal);
        // 配置每条线路的最大连接数
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            // 如果已经重试了2次，就放弃
            if (executionCount >= 2) {
                return false;
            }
            // 如果服务器丢掉了连接，那么就重试
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // 不要重试SSL握手异常
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            // 超时
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            // 目标服务器不可达
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // SSL握手异常
            if (exception instanceof SSLException) {
                return false;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();

            return !(request instanceof HttpEntityEnclosingRequest);
        };

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectionTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        return HttpClients.custom().setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(httpRequestRetryHandler)
                .build();
    }

}
