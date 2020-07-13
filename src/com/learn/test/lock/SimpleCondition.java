package com.learn.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/13/2020 10:50 AM
 */
public class SimpleCondition {


    public static void main(String[] args) throws InterruptedException {

        ThreadA threadA = new ThreadA();
        threadA.setName("A");
        ThreadB threadB = new ThreadB();
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
        new SimpleCondition().singalAll();
    }

    public void singalAll() {
        try {
            LockUtil.lock.lock();
            System.out.println("singal All");
            LockUtil.condition.signalAll();
        } finally {
            LockUtil.lock.unlock();
        }
    }


}

class ThreadA extends Thread {


    public void awaitA() {
        try {
            LockUtil.lock.lock();
            System.out.println("service A await time" + System.currentTimeMillis());
            try {
                LockUtil.condition.await();
                System.out.println("service A end await time" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            LockUtil.lock.unlock();
        }

    }

    @Override
    public void run() {
        this.awaitA();
    }

    public void signalAll() {
        try {
            LockUtil.lock.lock();
            System.out.println("signalAll");
            LockUtil.condition.signalAll();
        } finally {
            LockUtil.lock.unlock();
        }

    }
}

class ThreadB extends Thread {


    public void awaitB() {
        try {
            LockUtil.lock.lock();
            System.out.println("service B   await time" + System.currentTimeMillis());
            try {
                LockUtil.condition.await();
                System.out.println("service B end await time" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            LockUtil.lock.unlock();
        }

    }

    @Override
    public void run() {
        this.awaitB();
    }
}

class LockUtil {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
}