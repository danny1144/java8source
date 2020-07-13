package com.learn.test.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description:  schedule(TimerTask task,long delay) 延迟指定秒后执行一次任务
 *
 * @author: zhongxp
 * @Date: 7/13/2020 3:01 PM
 */


public class MyTask {
    public static void main(String[] args) {
        System.out.println("当前的时间为" + new Date());
        Calendar calendar = Calendar.getInstance();
       // calendar.add(Calendar.SECOND, 10);
        Date runDate = calendar.getTime();
        //    MyFutureTask task = new MyFutureTask();
        //改为守护线程
        //Timer timer = new Timer(true);
//        Timer timer = new Timer();
//        // 每隔4秒再次执行任务
//        timer.schedule(task, runDate, 4);
        int i = 0;
        while (true) {
            i++;
            Timer timer = new Timer();
            TaskA task = new TaskA(i);
            timer.schedule(task, runDate);
            timer.cancel();
        }

    }
}

class MyFutureTask extends TimerTask {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "任务执行了。时间为" + new Date());
    }
}

class TaskA extends TimerTask {
    private int i;

    public TaskA(Integer i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("第" + i + "次执行任务没有被取消");
    }
}
