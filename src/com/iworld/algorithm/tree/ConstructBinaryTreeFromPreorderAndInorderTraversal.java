package com.iworld.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 105. Construct Binary Tree from Preorder and Inorder Traversal   Medium
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a
 * binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @date 2022/8/22 12:25
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int m = inorder.length;
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        int n = preorder.length;
        return process(preorder, 0, n - 1, inorder, 0, m - 1, inOrderIndexMap);
    }
    
    private TreeNode process(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2, Map<Integer, Integer> inOrderIndexMap) {
        if (s1 > e1) {
            return null;
        }
        TreeNode head = new TreeNode(preorder[s1]);
        if (s1 == e1) {
            return head;
        }
        int headInOrderIndex = inOrderIndexMap.get(preorder[s1]);
        int count = headInOrderIndex - s2;
        head.left = process(preorder, s1 + 1, count + s1, inorder, s2, headInOrderIndex - 1, inOrderIndexMap);
        head.right = process(preorder, headInOrderIndex - s2 + s1 + 1, e1, inorder, headInOrderIndex + 1, e2, inOrderIndexMap);
        return head;
    }
    
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7}, inorder = {9,3,15,20,7};
        ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(preorder, inorder);
    }
}
