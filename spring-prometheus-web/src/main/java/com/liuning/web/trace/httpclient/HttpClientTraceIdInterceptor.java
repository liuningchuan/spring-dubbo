package com.liuning.web.trace.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * HttpClient拦截器，传递全局链路TraceId
 *
 * @author liuning
 * @since 2020-08-24 23:16
 * @see com.liuning.web.http.HttpClientPool
 */
public class HttpClientTraceIdInterceptor implements HttpRequestInterceptor {
    public HttpClientTraceIdInterceptor() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        String traceId = MDC.get("TraceId");
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }

        httpRequest.setHeader("SA-TRACEID", traceId);
    }
}
