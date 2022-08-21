package com.iworld.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 103. Binary Tree Zigzag Level Order Traversal   Medium
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * @date 2022/8/21 15:00
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> help = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty() || !help.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            if (!stack.isEmpty()) {
                while (!stack.isEmpty()) {
                    TreeNode pop = stack.pop();
                    result.add(pop.val);
                    if (pop.left != null) {
                        help.push(pop.left);
                    }
                    if (pop.right != null) {
                        help.push(pop.right);
                    }
                }
            } else {
                while (!help.isEmpty()) {
                    TreeNode pop = help.pop();
                    result.add(pop.val);
                    if (pop.right != null) {
                        stack.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack.push(pop.left);
                    }
                }
            }
            ans.add(result);
        }
        return ans;
    }
    
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        // 添加顺序也即为添加后要输出的顺序是否是从左边开始 默认false为第二层添加输出顺序
        boolean isLeft = false;
        while (!stack.isEmpty()) {
            int size = stack.size();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = isLeft ? stack.pollLast() : stack.pollFirst();
                result.add((poll.val));
                // 如果是从左输出 从头加 先加右再加左 下次弹就从头部弹
                if (isLeft) {
                    if (poll.right != null) {
                        stack.addFirst(poll.right);
                    }
                    if (poll.left != null) {
                        stack.addFirst(poll.left);
                    }
                } else {
                    // 如果是从右输出 从尾加 先加左再加右 下次弹就从尾部弹
                    if (poll.left != null) {
                        stack.offerLast(poll.left);
                    }
                    if (poll.right != null) {
                        stack.offerLast(poll.right);
                    }
                }
            }
            isLeft = !isLeft;
            ans.add(result);
        }
        return ans;
    }
}
