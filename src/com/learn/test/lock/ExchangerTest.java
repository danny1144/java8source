package com.learn.test.lock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 交换者：用于线程协作的工具类，提供一个同步点，在这个同步点，两个线程可以交换彼此的数据
 * 使用场景：遗传算法 、校对工作
 * 如果两个线程有一个没有执行exchange方法则方法会一致等待
 * @author: zhongxp
 * @Date: 7/17/2020 5:26 PM
 */
public class ExchangerTest {

    private static final Exchanger<String> exg = new Exchanger<>();

    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        pool.execute(() -> {
            String A = "A银行流水";
            try {
                exg.exchange(A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            String B = "B银行流水";
            try {
                String A = exg.exchange("B");
                System.out.println("A和B银行流水是否一致： " + A.equals(B) + "\tA录入的是： " + A + "\tB录入的是： " + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
    }
}
