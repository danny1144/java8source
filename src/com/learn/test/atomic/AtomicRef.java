package com.learn.test.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/17/2020 3:38 PM
 */
public class AtomicRef {

    public static AtomicReference<User> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User();
        user.setAge(13);
        user.setName("danny");
        atomicReference.set(user);
        User updateUser = new User();
        updateUser.setAge(23);
        updateUser.setName("zxp");
        atomicReference.compareAndSet(user, updateUser);
        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getAge());
    }
    static class User {
        private String name;
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
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

