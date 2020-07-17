package com.learn.test.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 信号量：用来控制同时访问特定资源的线程数量，他可以协调各个线程，保障合理的使用公共资源
 * 使用场景：流量控制，特别是公共资源有限的情况下
 * @author: zhongxp
 * @Date: 7/17/2020 5:14 PM
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    //并发数控制在10个
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取一个许可证
                        s.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("save data");
                    //释放许可资源
                    s.release();
                }
            });
        }

        executorService.shutdown();
    }

}
