package com.iworld.algorithm.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 297. Serialize and Deserialize Binary Tree
 * Hard
 * 7846
 * 288
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * @date 2022/10/11 14:41
 */
public class SerializeAndDeserializeBinaryTree {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll != null) {
                sb.append(poll.val);
                queue.add(poll.left);
                queue.add(poll.right);
            } else {
                sb.append("null");
            }
            if (!queue.isEmpty()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() < 1) {
            return null;
        }
        String[] split = data.split(",");
        LinkedList<String> queue = new LinkedList<>();
        for (String s : split) {
            queue.add(s);
        }
        TreeNode root = new TreeNode(Integer.parseInt(queue.poll()));
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.push(root);
        while (!queue.isEmpty()) {
            TreeNode poll = nodeQueue.poll();
            if (poll == null) {
                continue;
            }
            String leftVal = queue.pop();
            TreeNode left = null;
            if (!leftVal.equals("null")) {
                left = new TreeNode(Integer.parseInt(leftVal));
                poll.left = left;
            }
            nodeQueue.add(left);
            String rightVal = queue.pop();
            TreeNode right = null;
            if (!rightVal.equals("null")) {
                right = new TreeNode(Integer.parseInt(rightVal));
                poll.right = right;
            }
            nodeQueue.add(right);
            
        }
        return root;
    }
    
    // Encodes a tree to a single string.
    public String serializePre(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                sb.append(pop.val);
                stack.push(pop.right);
                stack.push(pop.left);
            } else {
                sb.append("null");
            }
            if (!stack.isEmpty()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserializePre(String data) {
        if (data == null || data.length() < 1) {
            return null;
        }
        String[] split = data.split(",");
        LinkedList<String> queue = new LinkedList<>();
        for (String s : split) {
            queue.add(s);
        }
        TreeNode root = new TreeNode(Integer.parseInt(queue.poll()));
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!queue.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop == null) {
                continue;
            }
            String leftVal = queue.pop();
            TreeNode left = null;
            if (!leftVal.equals("null")) {
                left = new TreeNode(Integer.parseInt(leftVal));
                pop.left = left;
            }
            String rightVal = queue.pop();
            TreeNode right = null;
            if (!rightVal.equals("null")) {
                right = new TreeNode(Integer.parseInt(rightVal));
                pop.right = right;
            }
            stack.push(right);
            stack.push(left);
        }
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        root.left = t2;
        root.right = t3;
        t3.left = t4;
        t3.right = t5;
        SerializeAndDeserializeBinaryTree serializeAndDeserializeBinaryTree = new SerializeAndDeserializeBinaryTree();
        System.out.println(serializeAndDeserializeBinaryTree.serialize(root));
    }
}
