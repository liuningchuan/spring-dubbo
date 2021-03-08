package com.liuning.start;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner
 *
 * @author liuning
 * @since 2021-03-08 23:34
 */
@Component
public class SpringBootPostRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Spring Boot Init Successful!");
    }
}
