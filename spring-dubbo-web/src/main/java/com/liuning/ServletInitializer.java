package com.liuning;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SpringBootServletInitializer是一个支持
 * Spring Boot的Spring WebApplicationInitializer实现。
 *
 * @author LiuNing
 * @date 2019/12/01 23:29
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.setProperty("app-name", "spring-dubbo");
        return builder.sources(StartApplication.class);
    }
}
