package com.learn.test.forkjoin;

import java.util.concurrent.*;

/**
 * @Description: RecursiveTask 有返回结果的任务 RecursiveAction 用于没有返回结果的任务 ForkJoinPool
 * @author: zhongxp
 * @Date: 7/17/2020 3:12 PM
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rithTask = new CountTask(middle+1, end);
            leftTask.fork();
            rithTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rithTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
