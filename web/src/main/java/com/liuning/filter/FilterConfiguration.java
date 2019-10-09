package com.liuning.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<TracrIdFilter> mdcFilter() {
        FilterRegistrationBean<TracrIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TracrIdFilter());
        registrationBean.setName("traceIdFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
