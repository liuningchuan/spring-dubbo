package com.liuning.test;

/**
 * volatile变量自增测试
 */
public class VolatileTest {

    private static volatile int race = 0;

    private static void increase() {
        //race++不是院子操作
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() < 0) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
