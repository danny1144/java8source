package com.learn.test.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/14/2020 3:48 PM
 */
public class JoinThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread pre = Thread.currentThread();
        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new ActiveThread(pre));
            thread.setName(i + "");
            thread.start();
            pre = thread;
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main end");
    }
}

class ActiveThread implements Runnable {
    private Thread pre;

    ActiveThread(Thread pre) {
        this.pre = pre;
    }

    @Override
    public void run() {
        try {
            pre.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "Terminal");
    }
}