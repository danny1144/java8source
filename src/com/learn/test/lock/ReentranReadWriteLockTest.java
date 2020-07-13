package com.learn.test.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁测试
 * 现象：方法1 读锁几乎同时都能拿到锁，方法2 需要等待第一个线程释放锁第二个线程才能拿到锁
 * @author: zhongxp
 * @Date: 7/13/2020 2:25 PM
 */
public class ReentranReadWriteLockTest {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    /**
     * 读读共享锁
     */
    public void read() {
        try {
            readWriteLock.readLock().lock();
            System.out.println("获得读锁" + Thread.currentThread().getName() +
                    " " + System.currentTimeMillis()
            );
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 写写互斥锁
     */
    public void write() {
        try {
            readWriteLock.writeLock().lock();
            System.out.println("获得写锁" + Thread.currentThread().getName() +
                    " " + System.currentTimeMillis()
            );
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {

        ReentranReadWriteLockTest reentranReadWriteLockTest = new ReentranReadWriteLockTest();

      /*  Runnable r1 = () -> {
            reentranReadWriteLockTest.read();
        };
        Runnable r2 = () -> {
            reentranReadWriteLockTest.read();
        };
        new Thread(r1).start();
        new Thread(r2).start();
*/

        Runnable r3 = () -> {
            reentranReadWriteLockTest.write();
        };

        Runnable r4 = () -> {
            reentranReadWriteLockTest.write();
        };

        new Thread(r3).start();
        new Thread(r4).start();
    }
}
