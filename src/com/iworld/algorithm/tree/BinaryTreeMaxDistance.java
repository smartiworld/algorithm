package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 计算二叉树最大距离
 * 给定一颗二叉树，任何两个节点都存在距离 返回整颗二叉树的最大距离
 * 两个节点间距离即为两个两个节点所包含的节点
 * 1.和当前树头节点有关  就是左树高度加右树高度加1
 * 2.和当前树头节点无关  就是左数最大距离或者右树最大距离
 * @date 2022/4/28 22:40
 */
public class BinaryTreeMaxDistance {
    
    public static int maxDistance(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return 0;
        }
        return process(root).distance;
    }
    
    public static TreeInfo process (CommonBinaryTree<Integer> head) {
        if (head == null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo leftTreeInfo = process(head.left);
        TreeInfo rightTreeInfo = process(head.right);
        int height = Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1;
        // 计算出和头节点有关的距离 leftTreeInfo.height + rightTreeInfo.height + 1
        // 和头节点无关的距离 Math.max(leftTreeInfo.distance, rightTreeInfo.distance)
        int distance = Math.max(leftTreeInfo.height + rightTreeInfo.height + 1,
                Math.max(leftTreeInfo.distance, rightTreeInfo.distance));
        return new TreeInfo(distance, height);
    }
    
    static class TreeInfo {
        // 距离 两个节点间包含的节点
        int distance;
        // 树的高度
        int height;
        
        TreeInfo (int distance, int height) {
            this.distance = distance;
            this.height = height;
        }
    }
}
