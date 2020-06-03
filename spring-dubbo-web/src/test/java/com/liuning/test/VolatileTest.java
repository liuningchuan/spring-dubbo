package com.liuning.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile变量自增测试
 */
public class VolatileTest {

    private static volatile int race = 0;

    private static final int THREADS_COUNT = 20;

    /**
     * 最开始的版本在IDEA的run模式下会死循环，使用CountDownLatch解决了这个问题
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

    /**
     * AtomicInteger.getAndIncrement是典型的CAS算法
     * getAndAddInt方法解析：拿到内存位置的最新值v，使用CAS尝试修将内存位置的值修改为目标值v+delta，
     *   如果修改失败，则获取该内存位置的新值v，然后继续尝试，直至修改成功。
     */
    public static AtomicInteger number = new AtomicInteger(0);

    private static  void increase() {
        //race++不是原子操作
        race++;
        number.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
                countDownLatch.countDown();
            });
            threads[i].start();
        }
        countDownLatch.await();
        System.out.println(race);
        System.out.println(number);
    }
}
