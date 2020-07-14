package com.learn.test.lock;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/14/2020 2:37 PM
 */
public class Comunication {

    public static void main(String[] args) {

        synchronized (Comunication.class) {
            m();
        }
    }

    synchronized static void m() {

    }
}
