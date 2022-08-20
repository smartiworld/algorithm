package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 通用二叉树对象
 * @date 2022/8/19 11:17
 */
public class TreeNode {
    
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    
    public TreeNode(int val) { this.val = val; }
    
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
