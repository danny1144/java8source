package com.learn.test.hashmap;

import java.util.HashMap;
import java.util.UUID;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/13/2020 9:36 AM
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        /*map.put("dd2", "1");
        map.put("dd1", "2");
        map.put("dd2", "3");
        map.put("dd3", "4");
        System.out.println("ab".hashCode());*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                            System.out.println(Thread.currentThread().getName());
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        thread.start();
        thread.join();
    }
}
