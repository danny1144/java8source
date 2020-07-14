package com.learn.test.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * countdownlatch 线程精确结束
 *
 * @author root
 */
public class SumTest1 {
    private static int m = 0;

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ReentrantLock lock = new ReentrantLock();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                try {
                    lock.lock();
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }
                } finally {
                    lock.unlock();
                }
                countDownLatch.countDown();
            });

        }
        for (Thread thread : threads) {
            thread.start();
        }
        countDownLatch.await();
        System.out.println(m);

    }
}
