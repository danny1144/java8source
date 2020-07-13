package com.learn.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 死锁测试
 * jps 获取java执行线程id
 * jstack pid 查询线程详情
 * @author: zhongxp
 * @Date: 7/13/2020 1:19 PM
 */
public class DeadLock {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Runnable t1 = () -> {
            try {
                lock1.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lock();
            } finally {
                lock1.unlock();
            }
        };
        Runnable t2 = () -> {
            try {
                lock2.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lock();
            } finally {
                lock2.unlock();
            }
        };


        new Thread(t1).start();
        new Thread(t2).start();


    }

}


