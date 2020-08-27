package com.learn.test.lock;

import com.learn.test.lock.cache.SingletonMap;
import sun.misc.LRUCache;

/**
 * @Description: 设计一个高效缓存，只允许一个用户写，运行多个用户读。
 * @author: zhongxp
 * @Date: 8/18/2020 3:58 PM
 */
public class TestCache {


    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
             final int finalI = i;
            new Thread(() -> {
                SingletonMap.getInstance().putValue("i" + finalI, finalI);
            }).start();
        }

        for (int i = 0; i < 10; i++) {
             final int finalI = i;
            new Thread(() -> {
                SingletonMap.getInstance().getValueByKey("i" + finalI);
            }).start();
        }
    }

}
