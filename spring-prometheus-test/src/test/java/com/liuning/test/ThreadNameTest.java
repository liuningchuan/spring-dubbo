package com.liuning.test;

import java.util.UUID;

/**
 * 线程名字测试
 */
public class ThreadNameTest {

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
            });
            threads[i].start();
        }

        System.out.println("over");
    }
}

