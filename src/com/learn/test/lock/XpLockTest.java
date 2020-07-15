package com.learn.test.lock;

/**
 * @Description: 测试自定义锁
 * @author: zhongxp
 * @Date: 7/15/2020 4:32 PM
 */
public class XpLockTest {


    public static void main(String[] args) {
        final XpLock lock = new XpLock();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtills.sleep(1L);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtills.sleep(1L);
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }
        for (int i = 9; i >= 0; i--) {
            threads[i].setDaemon(true);
            threads[i].setName("Thread" + i);
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            SleepUtills.sleep(1L);
            System.out.println();
        }


    }

}
