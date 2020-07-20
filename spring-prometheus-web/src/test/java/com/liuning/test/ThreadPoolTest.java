package com.liuning.test;

import com.liuning.web.concurrent.singleton.ThreadExecutor;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: liuning
 * @description: 线程池测试
 * @create: 2020-07-19 22:21
 * @version: 1.0
 */
public class ThreadPoolTest {

    @Test
    public void threadPoolTest_1() {
        ThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void threadPoolTest_2() {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i=0; i < 100; i++) {
            cachedThreadPool.submit(() -> System.out.println(Thread.currentThread().getName()));
        }

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i=0; i < 100; i++) {
            fixedThreadPool.submit(() -> System.out.println(Thread.currentThread().getName()));
        }

        ExecutorService singleaThreadPool = Executors.newSingleThreadExecutor();
        for (int i=0; i < 100; i++) {
            singleaThreadPool.submit(() -> System.out.println(Thread.currentThread().getName()));
        }

    }
}
