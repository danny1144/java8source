package com.learn.test.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/17/2020 2:43 PM
 */
public class UnsafeTest {
    public static void main(String[] args) {

        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);

            Unsafe unsafe = (Unsafe) theUnsafe.get(null);


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
