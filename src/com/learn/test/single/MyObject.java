package com.learn.test.single;

/**
 * @Description: Dcl双检查锁机制实现懒汉模式单例模式
 * @author: zhongxp
 * @Date: 7/13/2020 3:38 PM
 */
public class MyObject {
    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println(Person.getInstance().hashCode());
        };
        Runnable r2 = () -> {
            System.out.println(Person.getInstance().hashCode());
        };
        Runnable r3 = () -> {
            System.out.println(Person.getInstance().hashCode());
        };
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
}

class Person {
    private static volatile Person person;

    private Person() {
    }
    public static Person getInstance() {
        if (person != null) {
        } else {
            synchronized (Person.class) {
                if (person == null) {
                    person = new Person();
                }
            }
        }
        return person;
    }
}