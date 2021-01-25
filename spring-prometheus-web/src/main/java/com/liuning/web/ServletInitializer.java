package com.liuning.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * SpringBootServletInitializer是一个支持，让应用在tomcat容器中以war包的形式运行
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

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
