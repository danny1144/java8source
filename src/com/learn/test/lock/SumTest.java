package com.learn.test.lock;

public class SumTest {
    private static int m = 0;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                synchronized (SumTest.class) {
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }
                }
            });
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(m);
    }

}
