package com.liuning.start.filter;

import com.liuning.web.trace.springmcv.SpringMVCTracrIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuning
 * @date 2019-10-09
 * @description 自定义filter
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<SpringMVCTracrIdFilter> mdcFilter() {
        FilterRegistrationBean<SpringMVCTracrIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SpringMVCTracrIdFilter());
        registrationBean.setName("traceIdFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.setName("corsFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
