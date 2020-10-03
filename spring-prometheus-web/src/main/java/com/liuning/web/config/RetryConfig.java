package com.liuning.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * spring重试机制
 *
 * @author liuning
 * @since 2020-10-03 18:06
 */
@Configuration
public class RetryConfig {

    /**
     * 最大重试次数
     */
    @Value("${retry.max.attempts:3}")
    private int retryMaxAttempts;

    /**
     * 重试间隔
     */
    @Value("${retry.initial.interval:3}")
    private int retryInitialInterval;

    /**
     * 重试倍数
     */
    @Value("${retry.multiplier:2}")
    private int retryMultiplier;

    /**
     * 最大重试间隔
     */
    @Value("${retry.max.interval:30000}")
    private int retryMaxInterval;

    @Bean
    public RetryTemplate retryTemplate() {

        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(retryMaxAttempts);
        retryTemplate.setRetryPolicy(retryPolicy);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(retryInitialInterval);
        backOffPolicy.setMultiplier(retryMultiplier);
        backOffPolicy.setMaxInterval(retryMaxInterval);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }
}
