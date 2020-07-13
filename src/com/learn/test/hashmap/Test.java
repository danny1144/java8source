package com.learn.test.hashmap;

import java.util.HashMap;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/13/2020 9:36 AM
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("dd2", "1");
        map.put("dd1", "2");
        map.put("dd2", "3");
        map.put("dd3", "4");
        System.out.println("ab".hashCode());

    }
}
