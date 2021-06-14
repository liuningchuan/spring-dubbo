package com.liuning.start.config.retry;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * spring-retry properties
 *
 * @author liuning
 * @since 2020-11-29 20:52
 */
@Component
@ConfigurationProperties(prefix = "spring.retry")
@PropertySource(value = {"classpath:spring-retry.properties"}, ignoreResourceNotFound = false, encoding = "UTF-8")
public class RetryProperties {

    /**
     * 最大重试次数
     */
    private int maxAttempts;

    /**
     * 重试间隔
     */
    private int initialInterval;

    /**
     * 重试倍数
     */
    private int multiplier;

    /**
     * 最大重试间隔
     */
    private int maxInterval;

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public int getInitialInterval() {
        return initialInterval;
    }

    public void setInitialInterval(int initialInterval) {
        this.initialInterval = initialInterval;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMaxInterval() {
        return maxInterval;
    }

    public void setMaxInterval(int maxInterval) {
        this.maxInterval = maxInterval;
    }
}
