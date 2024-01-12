package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.5.3 完全二叉树节点个数
 * 求完全二叉树节点的个数
 * 要求时间复杂度低于O(N)
 * 1.分别计算出左右子树深度
 * 1.1 如果左右子树高度相同 说明当前节点的左子树是满数 可以用公式直接计算当前节点1+(2^左树高度)-1
 * 1.2 如果左子树高度大于右子树高度 说明当前节点的左子树是满数 可以用公式直接计算当前节点1+(2^左树高度)-1
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * @see CountCompleteTreeNodes
 * @date 2024/1/11 19:54
 */
public class BinaryTreeTotalNode {
    
    public static int totalBinaryTreeNode(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return 0;
        }
        return totalNode(root);
    }
    
    public static int totalNode(CommonBinaryTree<Integer> head) {
        if (head.left == null) {
            return 1;
        }
        if (head.right == null) {
            return 2;
        }
        int leftDeep = childDeep(head.left);
        int rightDeep = childDeep(head.right);
        if (leftDeep == rightDeep) {
            return (1 << leftDeep) + totalNode(head.right);
        }
        return (1 << rightDeep) + totalNode(head.left);
    }
    
    
    private static int childDeep(CommonBinaryTree<Integer> head) {
        int deep = 0;
        while (head != null) {
            deep++;
            head = head.left;
        }
        return deep;
    }
    
    public static void main(String[] args) {
    
    }
}
