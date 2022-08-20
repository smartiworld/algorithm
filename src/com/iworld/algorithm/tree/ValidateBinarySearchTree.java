package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 98. Validate Binary Search Tree   Medium
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -2 31 <= Node.val <= 2 (31 - 1)
 * https://leetcode.com/problems/validate-binary-search-tree/
 * @date 2022/8/20 13:30
 */
public class ValidateBinarySearchTree {
    
    public boolean isValidBST(TreeNode root) {
        TreeInfo process = process(root);
        return process == null || process.isBST;
    }
    
    public TreeInfo process(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeInfo leftInfo = process(node.left);
        TreeInfo rightInfo = process(node.right);
        boolean isBST = true;
        int min = node.val;
        int max = node.val;
        if (leftInfo != null) {
            isBST = leftInfo.isBST && node.val > leftInfo.max;
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            isBST = isBST && rightInfo.isBST && node.val < rightInfo.min;
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        return new TreeInfo(max, min, isBST);
    }
    
    public static class TreeInfo {
        public int max;
        public int min;
        public boolean isBST;
        
        public TreeInfo (int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }
}
