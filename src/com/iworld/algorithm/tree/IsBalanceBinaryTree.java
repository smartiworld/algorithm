package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 平衡二叉树
 * 每个子树的左右高度差不超过1
 * 每个子树是平衡的
 * @date 2022/4/25 17:12
 */
public class IsBalanceBinaryTree {
    
    
    public static boolean isBalance(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return true;
        }
        return process(root).isBBT;
    }
    
    public static TreeInfo process(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return new TreeInfo(true, 0);
        }
        TreeInfo leftTreeInfo = process(head.left);
        TreeInfo rightTreeInfo = process(head.right);
        boolean isBBT = false;
        if (leftTreeInfo.isBBT && rightTreeInfo.isBBT &&
                Math.abs(leftTreeInfo.height - rightTreeInfo.height) <= 1) {
            isBBT = true;
        }
        int height = Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1;
        return new TreeInfo(isBBT, height);
    }
    
    static class TreeInfo {
        // 是否为平衡二叉树
        private boolean isBBT;
        // 子树最小值
        private int height;
        
        TreeInfo(boolean isBBT, int height) {
            this.isBBT = isBBT;
            this.height = height;
        }
        
    }
}
