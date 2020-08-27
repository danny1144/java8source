package com.learn.test.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 两个线程交替执行
 * @author: zhongxp
 * @Date: 8/27/2020 1:40 PM
 */
public class ThreadSwitchTest {
    private volatile static int single = 0;

    public static void main(String[] args) throws InterruptedException {

        String lock = new String();
        Runnable t1 = () -> {
            while (single < 10) {
                if (single % 2 == 0) {
                    System.out.println(single);
                    synchronized (lock) {
                        single++;
                    }
                }
            }
        };
        Runnable t2 = () -> {
            while (single < 10) {
                if (single % 2 == 1) {
                    System.out.println(single);
                    synchronized (lock) {
                        single=single+1;
                    }
                }
            }
        };

        new Thread(t1).start();
        new Thread(t2).start();
    }
}
