package com.learn.test.design;

import java.util.LinkedList;

/**
 * @Description: 给定一个二叉树，检查它是否是镜像对称的。
 * @author: zhongxp
 * @Date: 8/13/2020 5:37 PM
 */
public class BinaryTreeTest {

    private boolean isSymmetric(Tree tree) {

        if (tree == null || (tree.left == null || tree.right == null)) {
            return true;
        }
        LinkedList<Tree> queue = new LinkedList<>();
        queue.addFirst(tree.left);
        queue.addFirst(tree.right);
        while (queue.size() > 0) {
            Tree left = queue.removeLast();
            Tree right = queue.removeLast();
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.value != right.value) {
                return false;
            }
            queue.addFirst(left.left);
            queue.addFirst(right.right);
            queue.addFirst(left.right);
            queue.addFirst(right.left);
        }
        return true;
    }
}


class Tree {
    int value;
    Tree left;
    Tree right;

    public Tree(int value) {
        this.value = value;
    }
}
