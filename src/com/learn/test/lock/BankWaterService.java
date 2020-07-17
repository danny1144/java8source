package com.learn.test.lock;

import java.util.concurrent.*;

/**
 * @Description: 使用CyclicBarrier模拟银行流水汇总
 * @author: zhongxp
 * @Date: 7/17/2020 4:37 PM
 */
public class BankWaterService implements Runnable {

    CyclicBarrier c = new CyclicBarrier(4, this);

    private Executor executor = Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String, Integer> bankWaterSheetCount = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    bankWaterSheetCount.put(Thread.currentThread().getName(), 1);
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = bankWaterSheetCount.values().stream().mapToInt(Integer::intValue).sum();
        bankWaterSheetCount.put("result", result);
        System.out.println(result);

    }


    public static void main(String[] args) {
        new BankWaterService().count();
    }
}


/**
 * isBroken 用来判断阻塞的线程是否被中断
 */
class CyclicBarrierTest1 {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    c.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        t.interrupt();
        try {
            c.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (BrokenBarrierException e) {

            e.printStackTrace();
        }
        System.out.println(c.isBroken());
    }

}