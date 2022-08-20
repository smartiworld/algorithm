package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 101. Symmetric Tree   Easy
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * Example 1:
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 *
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Could you solve it both recursively and iteratively?
 * https://leetcode.com/problems/symmetric-tree/
 * @date 2022/8/20 14:49
 */
public class SymmetricTree {
    
    public boolean isSymmetric(TreeNode root) {
        return process(root, root);
    }
    
    private boolean process(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 != null && t2 != null) {
            return t1.val == t2.val && process(t1.left, t2.right) && process(t1.right, t2.left);
        }
        return false;
    }
}
