package com.liuning.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * SpringBootServletInitializer是一个支持，让应用在tomcat容器中以war包的形式运行
 * Spring Boot的Spring WebApplicationInitializer实现。
 *
 * @author LiuNing
 * @date 2019/12/01 23:29
 */
@EnableRetry
@SpringBootApplication(scanBasePackages = {"com.liuning.service.*",
        "com.liuning.web.*",
        "com.liuning.dao.*"
})
public class Application extends SpringBootServletInitializer {

    /**
     * You are not expected to understand this.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.setProperty("app-name", "spring-dubbo");
        return builder.sources(Application.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}

