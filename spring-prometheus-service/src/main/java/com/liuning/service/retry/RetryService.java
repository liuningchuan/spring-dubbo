package com.liuning.service.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;

/**
 * 重试机制
 *
 * @author liuning
 * @since 2020-10-04 21:19
 */
@Service
public class RetryService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RetryTemplate retryTemplate;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public void retryable() throws Exception {
        logger.info("执行开始" + LocalTime.now());

        throw new Exception("系统异常");
    }

    public void retryTemplate() {

        int count = retryTemplate.execute(retryContext -> {
                    logger.info("开始执行，重试次数：{}次", retryContext.getRetryCount());
                    throw new NullPointerException();
                },
                retryContext -> {
                    logger.info("重试完成");
                    logger.info(String.valueOf(retryContext.getRetryCount()));
                    return retryContext.getRetryCount();
                });
        logger.info("重试次数：{}", count);
    }
}
