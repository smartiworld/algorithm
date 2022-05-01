package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 是否为满二叉树
 * @date 2022/4/27 10:08
 */
public class IsFullBinaryTree {

    public static boolean isFull(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return true;
        }
        TreeInfo treeInfo = process(head);
        // 满二叉树为2的高度次方-1 == 树的节点数量
        return (Math.pow(2, treeInfo.height) - 1) == treeInfo.count;
    }
    
    private static TreeInfo process(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo leftTreeInfo = process(head.left);
        TreeInfo rightTreeInfo = process(head.right);
        int height = Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1;
        int count = leftTreeInfo.count + 1 + rightTreeInfo.count;
        return new TreeInfo(height, count);
    }
    
    public static boolean isFull2(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return true;
        }
        TreeInfo2 treeInfo = process2(head);
        return treeInfo.isFBT;
    }
    
    private static TreeInfo2 process2(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return new TreeInfo2(0, true);
        }
        TreeInfo2 leftTreeInfo = process2(head.left);
        TreeInfo2 rightTreeInfo = process2(head.right);
        int height = Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1;
        boolean isFBT = false;
        // 每一个左树右树高度相等
        if (leftTreeInfo.isFBT && rightTreeInfo.isFBT && leftTreeInfo.height == rightTreeInfo.height) {
            isFBT = true;
        }
        return new TreeInfo2(height, isFBT);
    }
    
    public static void main(String[] args) {
        CommonBinaryTree<Integer> root = new CommonBinaryTree<Integer>(1);
        CommonBinaryTree<Integer> node2 = new CommonBinaryTree<Integer>(2);
        CommonBinaryTree<Integer> node3 = new CommonBinaryTree<Integer>(3);
        root.left = node2;
        root.right = node3;
        CommonBinaryTree<Integer> node4 = new CommonBinaryTree<Integer>(4);
        CommonBinaryTree<Integer> node5 = new CommonBinaryTree<Integer>(5);
        node2.left = node4;
        node2.right = node5;
        CommonBinaryTree<Integer> node6 = new CommonBinaryTree<Integer>(6);
        CommonBinaryTree<Integer> node7 = new CommonBinaryTree<Integer>(7);
//        node3.left = node6;
//        node3.right = node7;
//        CommonBinaryTree<Integer> node9 = new CommonBinaryTree<Integer>(9);
//        CommonBinaryTree<Integer> node10 = new CommonBinaryTree<Integer>(10);
//        node4.right = node9;
//        node5.right = node10;
        System.out.println(isFull(root));
        System.out.println(isFull2(root));
    }
    
    static class TreeInfo{
        // 树的高度
        int height;
        // 节点数量
        int count;
        
        TreeInfo(int height, int count) {
            this.height = height;
            this.count = count;
        }
    
        @Override
        public String toString() {
            return "TreeInfo{" +
                    "height=" + height +
                    ", count=" + count +
                    '}';
        }
    }
    
    static class TreeInfo2{
        // 树的高度
        int height;
        // 是否为满二叉树
        boolean isFBT;
        
        TreeInfo2(int height, boolean isFBT) {
            this.height = height;
            this.isFBT = isFBT;
        }
        
        @Override
        public String toString() {
            return "TreeInfo{" +
                    "height=" + height +
                    ", isFBT=" + isFBT +
                    '}';
        }
    }
}
