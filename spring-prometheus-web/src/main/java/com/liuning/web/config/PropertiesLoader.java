package com.liuning.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * 将属性加入到environment中
 */
public class PropertiesLoader implements EnvironmentPostProcessor {

    private static final String PROPERTIES_NAME = "PATH";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Properties properties = new Properties();

        String osName = System.getProperty("os.name");
        System.out.println(environment.getProperty("server.port"));
        if (osName.startsWith("Mac OS")) {
            properties.put("log.app.home", "/Users/liuning/tomcat/logs/spring-prometheus");
        } else if (osName.startsWith("Windows")) {
            properties.put("log.app.home", "/home/tomcat/logs/spring-prometheus");
        } else {
            // unix or linux
        }

        PropertiesPropertySource propertiesPropertySource =
                new PropertiesPropertySource(PROPERTIES_NAME, properties);
        environment.getPropertySources().addLast(propertiesPropertySource);
    }
}
