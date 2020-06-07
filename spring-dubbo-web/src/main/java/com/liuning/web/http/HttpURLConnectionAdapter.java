package com.liuning.web.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.Map;


/**
 * HttpURLConnection封装
 *
 * @author liuning
 * @date 2020/02/15
 */
@Component
public class HttpURLConnectionAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 两分钟
     */
    private static final Integer CONNECTION_TIMEOUT = 120000;

    /**
     * 两分钟
     */
    private static final Integer READ_TIMEOUT = 120000;

    /**
     * 默认字符集UTF-8
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * POST方法
     */
    private static final String METHOD_POST = "POST";

    public String send(String address, String method, String content) throws Exception {
        return send(address, method, content, DEFAULT_CHARSET, null);
    }

    public String send(String address, String method, String content, Map<String, String> headerMap)
            throws Exception {
        return send(address, method, content, DEFAULT_CHARSET, headerMap);
    }

    public String send(String address, String method, String content, String charset,
                       Map<String, String> headerMap) throws Exception {
        if (method != null) {
            method = method.toUpperCase();
        } else {
            method = METHOD_POST;
        }

        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 如果是https，不做server端证书校验，不做hostname校验
        trustAll(connection);
        // 设置http header
        setHttpHeaders(connection, headerMap);
        if (null != content) {
            // 需要输出
            connection.setDoOutput(true);
        }

        // 需要输入
        connection.setDoInput(true);
        // 不允许缓存
        connection.setUseCaches(false);
        connection.setRequestMethod(method);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        OutputStream outputStream = null;
        if (null != content) {
            outputStream = connection.getOutputStream();
            outputStream.write(content.getBytes(charset));
            outputStream.flush();
        }

        return getResponse(connection.getInputStream(), outputStream, charset);
    }


    /**
     * 设置http header
     *
     * @author liuning
     */
    private void setHttpHeaders(URLConnection connection, Map<String, String> headerMap) {
        if (headerMap == null || headerMap.isEmpty()) {
            return;
        }
        for (String key : headerMap.keySet()) {
            connection.setRequestProperty(key, headerMap.get(key));
        }
    }

    /**
     * 得到http请求的返回，并关闭输入输出流
     *
     * @author liuning
     */
    private String getResponse(InputStream inputStream, OutputStream outputStream, String charset) throws Exception {
        try {
            byte[] bytes = read(inputStream, 1024);
            return (new String(bytes, charset)).trim();
        } finally {
            closeIoSteam(inputStream, "inputStream");
            closeIoSteam(outputStream, "outputStream");
        }
    }

    /**
     * 关闭io steam
     *
     * @author f
     */
    private void closeIoSteam(Closeable closeable, String desc) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            logger.error("Error occurred while close {}.", desc, e);
        }
    }

    /**
     * 将inputstream的内容读到字节数组中
     *
     * @param inputStream 输入流
     * @param bufferSize  缓冲大小
     * @return 字节数组
     * @throws IOException
     * @author liuning
     */
    public byte[] read(InputStream inputStream, int bufferSize) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[bufferSize];
        for (int num = inputStream.read(buffer); num != -1; num = inputStream.read(buffer)) {
            baos.write(buffer, 0, num);
        }
        baos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

    /**
     * 如果Https請求，那么取消双向验证
     *
     * @param connection https connection
     * @throws Exception
     */
    private void trustAll(HttpURLConnection connection) throws Exception {
        if (connection instanceof HttpsURLConnection) {
            TrustManager[] trustAllCerts = new TrustManager[1];
            TrustManager tm = new TrustAllManager();
            trustAllCerts[0] = tm;
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, null);
            ((HttpsURLConnection) connection).setSSLSocketFactory(sc.getSocketFactory());
            ((HttpsURLConnection) connection).setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        }
    }

    static class TrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
