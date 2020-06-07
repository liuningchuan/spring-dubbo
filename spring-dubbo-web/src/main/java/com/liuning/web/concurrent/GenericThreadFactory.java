package com.liuning.web.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工场，定义线程池中创建的线程，如线程名称、优先级、守护进程状态等
 *
 * @author liuning
 * @since 2019-12-30 23:18
 */
public class GenericThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public GenericThreadFactory() {
        namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix + threadNumber.getAndIncrement());
        /**
         * 设置非守护线程
         */
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        /**
         * 设置线程优先级
         */
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
