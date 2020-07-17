package com.learn.test.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 字面意思：可循环利用的屏障。作用是让一组线程达到一个屏障时被阻塞，
 * 直到最后一个线程达到屏障时，屏障才会开门，所有被屏障拦截的线程才会继续执行
 * CyclicBarrier(int parties)
 * @author: zhongxp
 * @Date: 7/17/2020 4:22 PM
 */
public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }, "t1");
        thread.start();
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
