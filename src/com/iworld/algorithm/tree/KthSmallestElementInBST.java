package com.iworld.algorithm.tree;

import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 230. Kth Smallest Element in a BST
 * Medium
 * 8427
 * 148
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations)
 * and you need to find the kth smallest frequently, how would you optimize?
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * @date 2022/9/30 21:34
 */
public class KthSmallestElementInBST {
    
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        int ans = -1;
        TreeNode cur = root;
        int index = 1;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (index++ == k) {
                    return node.val;
                }
                cur = node.right;
            }
        }
        return ans;
    }
    
    /**
     * morris
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        TreeNode cur = root;
        int index = 1;
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (index++ == k) {
                return cur.val;
            }
            cur = cur.right;
        }
        return -1;
    }
}
