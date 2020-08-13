package com.learn.test.design;

import java.util.LinkedList;

/**
 * @Description: 设计一个两个栈组成的队列
 * @author: zhongxp
 * @Date: 8/13/2020 12:41 PM
 */
public class MyQueue<T> {

    private LinkedList<T> stack1;
    private LinkedList<T> stack2;

    public MyQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void add(T newElements) {
        stack1.addFirst(newElements);
    }

    public T poll() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("queue is empty");
        } else if (stack2.isEmpty()) {
            while (stack1.isEmpty()) {
                stack2.addFirst(stack1.removeFirst());
            }
        }
        return stack2.removeFirst();
    }
    public T peek() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("queue is empty");
        } else if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.addFirst(stack1.removeFirst());
            }
        }
        return stack2.peekFirst();
    }

    public static void main(String[] args) {
        MyQueue<Integer> integerMyQueue = new MyQueue<>();
        integerMyQueue.add(1);
        integerMyQueue.add(2);
        integerMyQueue.add(3);
        integerMyQueue.add(4);

        System.out.println(integerMyQueue.peek());
        System.out.println(integerMyQueue.poll());
        System.out.println(integerMyQueue.poll());
    }
}
