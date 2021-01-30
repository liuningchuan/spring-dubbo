package com.liuning.test.retry;

import com.liuning.service.retry.RetryService;
import com.liuning.web.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Retryable测试
 *
 * @author liuning
 * @since 2020-10-04 21:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RetryTest {

    @Resource
    private RetryService retryService;

    @Test
    public void retry() throws Exception {
        retryService.retryable();
    }

    @Test
    public void retryTemplate() {
        retryService.retryTemplate();
    }
}
