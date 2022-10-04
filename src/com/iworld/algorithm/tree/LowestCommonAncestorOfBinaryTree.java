package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 236. Lowest Common Ancestor of a Binary Tree
 * Medium
 * 12514
 * 307
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 * @date 2022/10/3 14:06
 */
public class LowestCommonAncestorOfBinaryTree {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q);
    }
    
    private TreeNode process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = process(root.left, p, q);
        TreeNode right = process(root.right, p, q);
        // 左树没有 返回右树  左树有右树没有返回左树 左树有右树有 返回左右父节点
        return left == null ? right : right == null ? left : root;
    }
    
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return process2(root, p, q).ans;
    }
    
    private TreeInfo process2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new TreeInfo(null, false, false);
        }
        TreeInfo leftTreeInfo = process2(root.left, p, q);
        TreeInfo rightTreeInfo = process2(root.right, p, q);
        boolean findP = leftTreeInfo.findP || rightTreeInfo.findP || root == p;
        boolean findQ = leftTreeInfo.findQ || rightTreeInfo.findQ || root == q;
        TreeNode ans = null;
        if (leftTreeInfo.ans != null) {
            ans = leftTreeInfo.ans;
        }
        if (rightTreeInfo.ans != null) {
            ans = rightTreeInfo.ans;
        }
        if (ans == null && findP && findQ) {
            ans = root;
        }
        return new TreeInfo(ans, findP, findQ);
    }
    
    
    public static class TreeInfo {
        public TreeNode ans;
        public boolean findP;
        public boolean findQ;
        
        public TreeInfo(TreeNode ans, boolean findP, boolean findQ) {
            this.ans = ans;
            this.findP = findP;
            this.findQ = findQ;
        }
    }
}
