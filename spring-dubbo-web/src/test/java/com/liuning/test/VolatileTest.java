package com.liuning.test;

import java.util.concurrent.CountDownLatch;

/**
 * volatile变量自增测试
 */
public class VolatileTest {

    private static volatile int race = 0;

    private static final int THREADS_COUNT = 20;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

    private static void increase() {
        //race++不是原子操作
        race++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    increase();
                }
                countDownLatch.countDown();
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        countDownLatch.await();
        System.out.println(race);
    }
}
