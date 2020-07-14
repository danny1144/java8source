package com.learn.test.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 安全的终止线程. 使用标志位去终止线程，而不是中断方法interrupt
 * @author: zhongxp
 * @Date: 7/14/2020 2:13 PM
 */
public class SafeStopThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.setName("Thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
        Thread1 thread2 = new Thread1();
        thread2.setName("Thread2");
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.cancel();
    }
}


class Thread1 extends Thread {
    private long i;
    private volatile boolean on = true;

    @Override
    public void run() {
        while (on && !Thread.currentThread().isInterrupted()) {
            i++;
        }
        System.out.println("Count : " + i);

    }

    public void cancel() {
        this.on = false;
    }
}
