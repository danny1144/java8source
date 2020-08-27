package com.learn.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 8/19/2020 11:03 AM
 */
public class CallableThreadTest implements Callable<Integer> {

    public static void main(String[] args) {
        CallableThreadTest callableThreadTest = new CallableThreadTest();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(callableThreadTest);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "循环的变量》》" + i);
            if (i == 20) {
                new Thread(integerFutureTask, "有返回值的线程").start();
            }
        }

        try {
            System.out.println(integerFutureTask.get() + "《《《子线程的返回值 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            String name = Thread.currentThread().getName();
            System.out.println("Current Thread Name>>>" + name + i);
        }
        return i;
    }
}
