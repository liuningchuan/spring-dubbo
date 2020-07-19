package com.liuning.web.concurrent.singleton;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Here is an example of how ThreadExecutor is used for a simple Class:
 *
 * <pre>
 * Runnable runnable = new Runnable();
 * ThreadExecutor.execute(runnable);
 * </pre>
 *
 * @author: liuning
 * @description: 线程池
 * @since: 2020-07-19 20:12
 * @version: 1.0
 */
public class ThreadExecutor {

    @Value("${thread.pool.core-pool-size:20}")
    private static int THREAD_CORE_POOL_SIZE;

    @Value("${thread.pool.maximum-pool-size:50}")
    private static int THREAD_MAXIMUM_POOL_SIZE;

    @Value("${thread.pool.keep-alive-time:10L}")
    private static final long THREAD_KEEP_ALIVE_TIME = 10L;

    @Value("${thread.pool.capacity:200}")
    private static int THREAD_CAPACITY;

    private ThreadExecutor() {
    }

    private static class ThreadPoolHolder {
        /**
         * 创建线程池
         */
        private static final ExecutorService executor =
                new ThreadPoolExecutor(THREAD_CORE_POOL_SIZE,
                        THREAD_MAXIMUM_POOL_SIZE,
                        THREAD_KEEP_ALIVE_TIME,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(THREAD_CAPACITY),
                        new DefaultThreadFactory());
    }

    /**
     * 实现自定义线程名
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("pool-" + threadNumber.getAndIncrement());
            /*
             * 设置非守护线程
             */
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            /*
             * 设置线程优先级
             */
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            // 自定义线程名
            return thread;
        }
    }

    /**
     * 提交线程到线程池运行
     */
    public static void execute(Runnable r) {
        ThreadPoolHolder.executor.execute(r);
    }

    /**
     * 程序退出时，执行此方法，关闭线程池中的线程，避免程序挂起
     * 立即结束线程，不管线程是否阻塞或者在执行中，有中断正在执行任务的风险
     */
    public static void shutdown() {
        ThreadPoolHolder.executor.shutdownNow();
    }
}
