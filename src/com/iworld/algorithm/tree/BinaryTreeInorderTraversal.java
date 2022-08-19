package com.iworld.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 94. Binary Tree Inorder Traversal   Easy
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * Example 1:
 *
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * @date 2022/8/19 11:06
 */
public class BinaryTreeInorderTraversal {
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    ans.add(cur.val);
                    cur = cur.right;
                }
            } else {
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }
    
    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                ans.add(pop.val);
                cur = pop.right;
            }
        }
        return ans;
    }
    
    public List<Integer> inorderTraversalIter(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }
    
    private void process(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        process(root.left, ans);
        ans.add(root.val);
        process(root.right, ans);
    }
    
    public static void main(String[] args) {
    
    }
    
}
