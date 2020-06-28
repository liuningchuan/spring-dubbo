package com.liuning.web.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @author: liuning
 * @description: 并行处理类
 * @create: 2020-06-27 21:37
 * @version: 1.0
 */
public class ParallelProcessor<T> {

    private static final Logger log = LoggerFactory.getLogger(ParallelProcessor.class);
    private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
    private Map<String, Consumer<T>> checkers;
    private T context;

    public ParallelProcessor(Map<String, Consumer<T>> checkers, T context) {
        this.checkers = checkers;
        this.context = context;
    }

    public ParallelProcessor<T> addChecker(String desc, Consumer<T> checker) {
        checkers.put(desc, checker);
        return this;
    }

    public void executeAll() {
        try {
            long start = System.currentTimeMillis();
            CountDownLatch countDownLatch = new CountDownLatch(checkers.size());

            checkers.keySet().forEach(desc -> newFixedThreadPool.execute(() -> {
                    try {
                        log.info("开始进行校验：{}", desc);
                        checkers.get(desc).accept(context);
                        log.info("完成执行校验：{}", desc);
                    } finally {
                        countDownLatch.countDown();
                    }
                }));
            countDownLatch.await();
            long cost = System.currentTimeMillis() - start;
            log.info("cost is :" + cost);
        } catch (InterruptedException e) {
            log.error("线程中断",e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("并行校验发生异常，", e);
            throw e;
        }
    }
}
