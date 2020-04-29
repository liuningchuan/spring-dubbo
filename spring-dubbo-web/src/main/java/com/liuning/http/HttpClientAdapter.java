package com.liuning.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient封装
 *
 * @author liuning
 * @since 2020-01-20
 */
@Component
public class HttpClientAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientAdapter.class);

    /**
     * GET请求
     *
     * @param url url
     */
    public void get(String url) {

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            logger.info("Response Status is" + response.getStatusLine());
            if (responseEntity != null) {
                logger.info("Response Content Length :" + responseEntity.getContentLength());
                logger.info("Response Content :" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            logger.error("Httpclient Get error" + e);
        }
    }

    /**
     * post请求
     */
    public void post() {
        HttpPost httpPost = new HttpPost("http://locahost:8080");
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            List<NameValuePair> formparams = new ArrayList<>();
            formparams.add(new BasicNameValuePair("username","name"));
            formparams.add(new BasicNameValuePair("password","word"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,"UTF-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            logger.info("Response Status is" + response.getStatusLine());
            if (responseEntity != null) {
                logger.info("Response Content Length :" + responseEntity.getContentLength());
                logger.info("Response Content :" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            logger.error("HttpClient Post error");
        }
    }

    public static String sendGet(String uriPath, List<NameValuePair> ns)
            throws URISyntaxException, ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URIBuilder uri = new URIBuilder();
        uri.setPath(uriPath);
        uri.addParameters(ns);
        URI u = uri.build();
        HttpGet httpget = new HttpGet(u);
        CloseableHttpResponse response = httpclient.execute(httpget);
        return EntityUtils.toString(response.getEntity());
    }

    public static String sendPost(String uriPath, List<NameValuePair> ns)
            throws URISyntaxException, ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URIBuilder uri = new URIBuilder();
        uri.setPath(uriPath);
        uri.addParameters(ns);
        URI u = uri.build();
        HttpPost httpPost = new HttpPost(u);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

}
