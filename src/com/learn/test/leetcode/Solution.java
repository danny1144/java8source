package com.learn.test.leetcode;

class Solution {
    public int reverse(int x) {
        if( x>Math.pow(2,31) || x< Math.pow(-2,31)){
            return 0;
        }
        String a = String.valueOf(x);
        StringBuilder str = new StringBuilder(a);
        if (a.startsWith("-")) {
            str = str.reverse().deleteCharAt(str.indexOf("-"));
            str = str.insert(0, "-");
        } else {
            str = str.reverse();
        }

        return Integer.parseInt(str.toString());
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(2));
    }
}