package com.learn.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: getHoldCount()的作用：查询当前线程保存此锁定的数目，也就是调用lock的次数
 * getQueueLength(condition)作用：返回正等待获取此锁定的线程估计数
 * getWaitQueueLength(condition)作用：返回此锁定相关的给定条件的线程估计数
 * hasQueueThread(Thread thread) 作用：查询指定线程是否是正在等待获取此锁定
 * hasQueueThreads() 作用：查询是否有线程正在等待获取此锁定
 * hasWaiters()作用：查询是否有线程正在等待与此锁定有关的condition条件
 * @author: zhongxp
 * @Date: 7/13/2020 1:14 PM
 */
public class LockMethodTest {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void WaitMethod() {

        try {
            lock.lock();
            try {
                System.out.println("正在执行");
                condition.await();
                System.out.println("执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {
            lock.lock();
            System.out.println("有没有线程正在等待 newCondition: " + lock.hasWaiters(condition)
                    + "线程数是：" + lock.getWaitQueueLength(condition)
            );

            condition.signal();
            System.out.println("唤醒结束");
            System.out.println("等待唤醒数目" + lock.getWaitQueueLength(condition));
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final LockMethodTest lockMethodTest = new LockMethodTest();
        Runnable runnable = () -> {
            lockMethodTest.WaitMethod();
        };
        Thread[] t1 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            t1[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            t1[i].start();
        }
        Thread.sleep(10000);
        lockMethodTest.notifyMethod();
    }
}
