package com.learn.test.lock;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/13/2020 4:22 PM
 */
public class ThreadGroupTest {

    public static void main(String[] args) {

        ThreadGroup zxp = new ThreadGroup("zxp");

        Thread thread1 = new Thread(zxp, new ThreaA());
        Thread thread2 = new Thread(zxp, new ThreaB());

        thread1.start();
        thread2.start();

        System.out.println("thread active " + zxp.activeCount());
        System.out.println("thread group " + zxp.getName());
    }
}


class ThreaA extends Thread {


    @Override
    public void run() {
        this.setName("A");
        while (!this.isInterrupted()) {
            System.out.println("thread Name" + this.getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class ThreaB extends Thread {


    @Override
    public void run() {
        this.setName("B");
        while (!this.isInterrupted()) {
            System.out.println("thread Name" + this.getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}