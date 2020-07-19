package com.liuning.test;

import com.liuning.web.concurrent.singleton.ThreadExecutor;
import org.junit.Test;

/**
 * @author: liuning
 * @description: 线程池测试
 * @create: 2020-07-19 22:21
 * @version: 1.0
 */
public class ThreadPoolTest {

    @Test
    public void threadPoolTest() {
        ThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println(Thread.currentThread().getName());
    }
}
