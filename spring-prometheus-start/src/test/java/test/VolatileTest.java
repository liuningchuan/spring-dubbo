package test;

import com.liuning.common.algorithms.SnowflakeId;

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
     * CountDownLatch典型用法：
     * 1、某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为new CountDownLatch(n)，
     *  每当一个任务线程执行完毕，就将计数器减1 countdownLatch.countDown()，当计数器的值变为0时，
     *  在CountDownLatch上await()的线程就会被唤醒。
     *  一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
     *
     * 2、实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。
     *  类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，
     *  将其计算器初始化为1，多个线程在开始执行任务前首先countdownlatch.await()，当主线程调用countDown()时，
     *  计数器变为0，多个线程同时被唤醒。
     */
    private static final CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

    /**
     * AtomicInteger.getAndIncrement是典型的CAS算法
     * getAndAddInt方法解析：拿到内存位置的最新值v，使用CAS尝试修将内存位置的值修改为目标值v+delta，
     * 如果修改失败，则获取该内存位置的新值v，然后继续尝试，直至修改成功。
     */
    public static AtomicInteger number = new AtomicInteger(0);

    private static void increase() {
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
        //阻塞主线程，等待子线程执行完成再执行主线程
        countDownLatch.await();
        System.out.println("race is : " + race);
        System.out.println("number is : " + number);

        System.out.println(new SnowflakeId().nextId());
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
    }
}
