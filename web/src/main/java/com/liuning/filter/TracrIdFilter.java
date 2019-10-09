package com.liuning.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author liuning
 * @date 2019-10-09
 * @description 生成全链路trace_id，作为MDC码，方便追踪单个请求
 */
public class TracrIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        initMDCAttrs();
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
            clearADCAttrs();
        }
    }

    @Override
    public void destroy() {

    }

    private void initMDCAttrs() {
        MDC.put("TRACE_ID", UUID.randomUUID().toString().replaceAll("-",""));
    }

    private void clearADCAttrs() {
        MDC.remove("TRACE_ID");
    }
}
