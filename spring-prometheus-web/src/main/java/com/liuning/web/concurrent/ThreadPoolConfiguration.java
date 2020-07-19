package com.liuning.web.concurrent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池配置
 *
 * @author liuning
 * @date 2020-01-05 21:11
 */
@Configuration
public class ThreadPoolConfiguration {

    @Value("${threadpool.core-size:20}")
    private int corePoolSize;
    @Value("${threadpool.max-size:20}")
    private int maximumPoolSize;
    @Value("${threadpool.keep-alive-time:0}")
    private int keepAliveTime;

    public ThreadPoolConfiguration() {
    }

    @Bean("threadPoolExecutor")
    public GenericThreadPoolExecutor netSignThreadExecutor() {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        GenericThreadPoolExecutor threadPoolExecutor =
                new GenericThreadPoolExecutor(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(),
                        (Runnable r) -> {
                            Thread thread = new Thread(r);
                            thread.setName("ThreadExecutor-" + threadNumber.getAndIncrement());
                            if (thread.isDaemon()) {
                                thread.setDaemon(false);
                            }
                            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                                thread.setPriority(Thread.NORM_PRIORITY);
                            }
                            return thread;
                        });
        //设置是否允许核心线程超时
        threadPoolExecutor.allowCoreThreadTimeOut(false);
        //设置线程池任务拒绝策略
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
