package com.learn.test.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: zhongxp
 * @Date: 7/16/2020 3:12 PM
 */
public class TwoSum {


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] test = {2, 7, 11, 15};
        System.out.println(new TwoSum().twoSum(test, 9));
    }

}
