package com.learn.test.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description: 原子更新字段类 修改字段是基础类型而且是内存可见的公共属性
 * @author: zhongxp
 * @Date: 7/17/2020 3:45 PM
 */
public class AtomicFiled {
    private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");


    public static void main(String[] args) {
        User user = new User();
        user.setAge(18);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));


    }

    static class User {
        private String name;
        public volatile int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
