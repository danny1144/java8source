package com.learn.test.design;

import java.util.LinkedList;

/**
 * @Description: 设计一个有getmin的栈，时间复杂度O(1)空间复杂度O(N)
 * @author: zhongxp
 * @Date: 8/13/2020 12:14 PM
 */
public class MyStack {

    private LinkedList<Integer> stackData;
    private LinkedList<Integer> stackMin;
    public MyStack() {
        stackData = new LinkedList<>();
        stackMin = new LinkedList<>();
    }

    public void push(Integer newElement) {
        if (stackMin.isEmpty()) {
            stackMin.addFirst(newElement);
        } else if (newElement <= this.getMin()) {
            stackMin.addFirst(newElement);
        }
        stackData.addFirst(newElement);
    }
    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("your stack is empty");
        }
        int value = this.stackData.removeFirst();
        if (value == this.getMin()) {
            this.stackMin.removeFirst();
        }
        return value;
    }

    public Integer getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return this.stackMin.peek();
    }

    public static void main(String[] args) {
        MyStack    stack=new MyStack();
        stack.push(6);
        stack.push(4);
        stack.push(2);
        stack.push(56);
        stack.push(4);
        stack.push(51);
        System.out.println(stack.getMin());
    }

}
