package com.learn.test.lock.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 本地缓存
 * @author: zhongxp
 * @Date: 7/22/2020 1:12 PM
 */
public class SingletonMap {

    ReadWriteLock readAndWriteLock = new ReentrantReadWriteLock();

    //一个本地的缓存Map
    private Map<String, Object> localCacheStore = new LRUCache<String, Object>(200);
    //一个私有的对象，非懒汉模式
    private static volatile SingletonMap singletonMap = null;

    //私有构造方法，外部不可以new一个对象
    private SingletonMap() {
    }

    //静态方法，外部获得实例对象
    public static SingletonMap getInstance() {
        if (singletonMap == null) {
            synchronized (SingletonMap.class) {
                if (singletonMap == null) {
                    singletonMap = new SingletonMap();
                }
            }
        }
        return singletonMap;
    }

    //获得缓存中的数据
    public Object getValueByKey(String key) {
        readAndWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "read lock is ready.." + (key+">>>"+localCacheStore.get(key)));
            return localCacheStore.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readAndWriteLock.readLock().unlock();
        }
        return null;
    }

    //清除
    public void clear() {
        localCacheStore.clear();
    }

    //向缓存中添加数据
    public void putValue(String key, Object value) {
        readAndWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "write lock is ready.." + (key + ">>" + value));
            localCacheStore.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readAndWriteLock.writeLock().unlock();
        }

    }


}
