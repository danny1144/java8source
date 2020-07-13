package com.learn.test.lock;

class SysDeadLock {
    //对象监视器
    static String lock1 = new String("a");
    static String lock2 = new String("a");

    public static void main(String[] args) {

        Runnable t1 = () -> {
            synchronized (lock1) {
                System.out.println("service1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("service2");

                }
                System.out.println("service  1 end");
            }

        };
        Runnable t2 = () -> {
            synchronized (lock2) {
                System.out.println("service2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("service1");
                }
                System.out.println("service  2 end");
            }
        };
        new Thread(t1).start();
        new Thread(t2).start();
    }


}