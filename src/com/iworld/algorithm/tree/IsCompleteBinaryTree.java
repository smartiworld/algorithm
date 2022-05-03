package com.iworld.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gq.cai
 * @version 1.0
 * @description  给定一颗二叉树的头节点 返回这颗树是不是完全二叉树
 * 完全二叉树 趋于变满的二叉树 从左到右依次变满的二叉树
 * 满二叉树 所有节点都会有左右子树 层数为k满二叉树总结点数为：2的k次方减1
 * @date 2022/4/25 17:35
 */
public class IsCompleteBinaryTree {
    
    public static void main(String[] args) {
        CommonBinaryTree<Integer> root = new CommonBinaryTree<Integer>(1);
        CommonBinaryTree<Integer> node2 = new CommonBinaryTree<Integer>(2);
        CommonBinaryTree<Integer> node3 = new CommonBinaryTree<Integer>(3);
        root.left = node2;
        root.right = node3;
        CommonBinaryTree<Integer> node4 = new CommonBinaryTree<Integer>(4);
        CommonBinaryTree<Integer> node5 = new CommonBinaryTree<Integer>(5);
        //node2.left = node4;
        node2.right = node5;
        CommonBinaryTree<Integer> node6 = new CommonBinaryTree<Integer>(6);
        CommonBinaryTree<Integer> node7 = new CommonBinaryTree<Integer>(7);
        node3.left = node6;
        node3.right = node7;
        System.out.println(isComplete2(root));
        System.out.println(isComplete3(root));
    }
    
    public static boolean isComplete2(CommonBinaryTree<Integer> root) {
            if (root == null) {
            return true;
        }
        return process(root).isCBT;
    }
    
    private static TreeInfo2 process(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return new TreeInfo2(true, true, 0);
        }
        TreeInfo2 leftTreeInfo = process(head.left);
        TreeInfo2 rightTreeInfo = process(head.right);
        int height = Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1;
        boolean isFBT = false;
        boolean isCBT = false;
        // 判断当前数是否是满二叉树 是满二叉树也为完全二叉树  左满 右满 左右高度相等 满
        if (leftTreeInfo.isFBT && rightTreeInfo.isFBT && leftTreeInfo.height == rightTreeInfo.height) {
            isFBT = true;
            isCBT = true;
        }
        // 判断当前子树是否为完全二叉树
        if (!isFBT) {
            // 左完全二叉树或者是满二叉树 右满二叉树 左右高度相差1
            if ((leftTreeInfo.isCBT || leftTreeInfo.isFBT) &&
                    rightTreeInfo.isFBT && leftTreeInfo.height == rightTreeInfo.height + 1) {
                isCBT = true;
            }
            // 左树满 右树完全 高度相等
            if (leftTreeInfo.isFBT && rightTreeInfo.isCBT && leftTreeInfo.height == rightTreeInfo.height) {
                isCBT = true;
            }
        }
        return new TreeInfo2(isFBT, isCBT, height);
    }
    
    public static boolean isComplete3(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return true;
        }
        Queue<CommonBinaryTree<Integer>> queue = new LinkedList<>();
        queue.add(root);
        boolean leafNode = false;
        while (!queue.isEmpty()) {
            CommonBinaryTree<Integer> poll = queue.poll();
            // 1.有右无左
            // 2.当最后一个节点左右不全时 下一个节点还有左孩子或者右孩子的时候
            if ((poll.right != null && poll.left == null) ||
                    leafNode && (poll.left != null || poll.right != null)) {
                return false;
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            } else {
                leafNode = true;
            }
        }
        return true;
    }
    
    static class TreeInfo {
        boolean isFBT;
        boolean isCBT;
        
    }
    
    /**
     * 左树是满                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        右树是满 左右高度相等 此树是满的
     */
    static class TreeInfo2 {
        // 是否是满二叉树
        boolean isFBT;
        // 是否是完全二叉树
        boolean isCBT;
        // 高度
        int height;
        
        TreeInfo2(boolean isFBT, boolean isCBT, int height) {
            this.isFBT = isFBT;
            this.isCBT = isCBT;
            this.height = height;
        }
        
    }
}
