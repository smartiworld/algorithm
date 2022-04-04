package com.iworld.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 二叉树反转 左右孩子反转
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @date 2022/3/15 19:42
 */
public class BinaryTreeRevert {
    
    /**
     * 递归的方式 先交换左右子树 然后继续递归处理左树 完成后递归处理右树
     * @param root
     * @return
     */
    public CommonBinaryTree<String> invertTree(CommonBinaryTree<String> root) {
        if (root == null) {
            return null;
        }
        CommonBinaryTree<String> left = root.left;
        // 交换左右子树
        root.left = root.right;
        root.right = left;
        // 递归处理左树
        invertTree(root.left);
        // 递归处理右树
        invertTree(root.right);
        return root;
    }
    
    /**
     * 深度优先遍历的方式 先交换左右子树 如果左右子树不为空 继续加入栈
     * @param root
     * @return
     */
    public CommonBinaryTree<String> invertTreeWithDFS(CommonBinaryTree<String> root) {
        if (root == null) {
            return null;
        }
        Stack<CommonBinaryTree<String>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            CommonBinaryTree<String> pop = stack.pop();
            CommonBinaryTree<String> left = pop.left;
            pop.left = pop.right;
            pop.right = left;
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return root;
    }
    
    /**
     * 广度优先遍历的方式 先交换左右子树 如果左右子树不为空 继续加入队列
     * @param root
     * @return
     */
    public CommonBinaryTree<String> invertTreeWithBFS(CommonBinaryTree<String> root) {
        if (root == null) {
            return null;
        }
        Queue<CommonBinaryTree<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            CommonBinaryTree<String> poll = queue.poll();
            CommonBinaryTree<String> left = poll.left;
            // 交换左右子树
            poll.left = poll.right;
            poll.right = left;
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return root;
    }
    
    public static void main(String[] args) {
        CommonBinaryTree<String> root = new CommonBinaryTree<>("1");
        CommonBinaryTree<String> tree2 = new CommonBinaryTree<>("2");
        CommonBinaryTree<String> tree3 = new CommonBinaryTree<>("3");
        root.left = tree2;
        root.right = tree3;
        CommonBinaryTree<String> tree4 = new CommonBinaryTree<>("4");
        tree2.left = tree4;
        CommonBinaryTree<String> tree5 = new CommonBinaryTree<>("5");
        CommonBinaryTree<String> tree6 = new CommonBinaryTree<>("6");
        tree3.left = tree5;
        tree3.right = tree6;
        CommonBinaryTree<String> tree7 = new CommonBinaryTree<>("7");
        tree5.left = tree7;
        CommonBinaryTree<String> tree8 = new CommonBinaryTree<>("8");
        tree6.right = tree8;
        BinaryTreeRevert binaryTreeRevert = new BinaryTreeRevert();
        //CommonBinaryTree<String> tree11 = binaryTreeRevert.invertTree(root);
        //System.out.println(tree11);
        CommonBinaryTree<String> tree22 = binaryTreeRevert.invertTreeWithBFS(root);
        System.out.println(tree22);
        CommonBinaryTree<String> tree33 = binaryTreeRevert.invertTreeWithDFS(root);
        System.out.println(tree33);
    }
}
