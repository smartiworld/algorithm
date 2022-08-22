package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 104. Maximum Depth of Binary Tree   Easy
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from
 * the root node down to the farthest leaf node.
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * @date 2022/8/21 18:06
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return process(root);
    }
    
    private int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = process(node.left);
        int rightDepth = process(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
