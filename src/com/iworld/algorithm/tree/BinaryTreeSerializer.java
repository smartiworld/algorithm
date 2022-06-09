package com.iworld.algorithm.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 二叉树的序列化和反序列化
 * 中序不可以反序列化
 * @date 2022/3/12 21:25
 */
public class BinaryTreeSerializer {
    
    /**
     * 二叉树先序序列化
     * @return
     */
    public String preSerial(CommonBinaryTree<String> root) {
        if (root == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        preIter(root, builder);
        return builder.toString();
    }
    
    private void preIter(CommonBinaryTree<String> tree, StringBuilder builder) {
        if (tree == null) {
            builder.append("null").append(",");
            return ;
        }
        builder.append(tree.value).append(",");
        preIter(tree.left, builder);
        preIter(tree.right, builder);
    }
    
    /**
     * 反序列化
     * @param data
     * @return
     */
    public CommonBinaryTree<String> preDeSerialUseStack(String data) {
        if (data == null) {
            return null;
        }
        String[] split = data.split(",");
        CommonBinaryTree<String> root = generateBinaryTree(split[0]);
        // 当前树的路径
        Stack<CommonBinaryTree<String>> stack = new Stack<>();
        stack.push(root);
        CommonBinaryTree<String> parent = null;
        boolean isLeft = true;
        // 先序 先父在左 会把有左子树全部构建回来等左子树节点为空的时候构建右子树
        for (int i = 1; i < split.length; i++) {
            String s = split[i];
            if (s == null || "null".equals(s)) {
                // 不是左边树的时候 弹出
                if (!stack.isEmpty()) {
                    parent = stack.pop();
                }
                isLeft = false;
                continue;
            }
            if (isLeft) {
                parent = stack.peek();
                parent.left = generateBinaryTree(s);
                stack.push(parent.left);
            } else {
                isLeft = true;
                parent.right = generateBinaryTree(s);
                stack.push(parent.right);
            }
        }
        return root;
    }
    
    /**
     * 二叉树先序序列化 反序列化preSerial
     * @param data
     * @return
     */
    public CommonBinaryTree<String> preDeSerial(String data) {
        if (data == null) {
            return null;
        }
        String[] split = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(split));
        return preBuild(queue);
    }
    
    private CommonBinaryTree<String> preBuild(Queue<String> queue) {
        String poll = queue.poll();
        if (poll == null || "null".equals(poll)) {
            return null;
        }
        CommonBinaryTree<String> tree = new CommonBinaryTree<>(poll);
        tree.left = preBuild(queue);
        tree.right = preBuild(queue);
        return tree;
    }
    
    /**
     * 宽度优先 序列化
     * @param root
     * @return
     */
    public Queue<String> levelSerial(CommonBinaryTree<String> root) {
        if (root == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(root.value);
        Queue<CommonBinaryTree<String>> treeQueue = new LinkedList<>();
        treeQueue.offer(root);
        while (!treeQueue.isEmpty()) {
            CommonBinaryTree<String> poll = treeQueue.poll();
            if (poll.left != null) {
                treeQueue.offer(poll.left);
                queue.offer(poll.left.value);
            } else {
                queue.offer(null);
            }
            if (poll.right != null) {
                treeQueue.offer(poll.right);
                queue.offer(poll.right.value);
            } else {
                queue.offer(null);
            }
        }
        return queue;
    }
    
    public CommonBinaryTree<String> levelSerialBuild(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Queue<CommonBinaryTree<String>> treeQueue = new LinkedList<>();
        CommonBinaryTree<String> root = new CommonBinaryTree<>(queue.poll());
        treeQueue.offer(root);
        while (!treeQueue.isEmpty()) {
            String pollLeft = queue.poll();
            CommonBinaryTree<String> pollTree = treeQueue.poll();
            if (pollLeft != null) {
                pollTree.left = generateBinaryTree(pollLeft);
                treeQueue.offer(pollTree.left);
            }
            String pollRight = queue.poll();
            if (pollRight != null) {
                pollTree.right = generateBinaryTree(pollRight);
                treeQueue.offer(pollTree.right);
            }
        }
        return root;
    }
    
    public CommonBinaryTree<String> generateBinaryTree(String val) {
        CommonBinaryTree<String> tree = null;
        if (val != null) {
            tree = new CommonBinaryTree<>(val);
        }
        return tree;
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
        BinaryTreeSerializer binaryTreeSerializer = new BinaryTreeSerializer();
        Queue<String> queue = binaryTreeSerializer.levelSerial(root);
        System.out.println(Arrays.toString(queue.toArray(new String[0])));
        CommonBinaryTree<String> tree = binaryTreeSerializer.levelSerialBuild(queue);
        String s = binaryTreeSerializer.preSerial(root);
        System.out.println(s);
        CommonBinaryTree<String> tree11 = binaryTreeSerializer.preDeSerial(s);
        System.out.println(tree11);
        CommonBinaryTree<String> tree22 = binaryTreeSerializer.preDeSerialUseStack(s);
        System.out.println(tree22);
    }
}
