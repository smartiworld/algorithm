package com.iworld.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
        return process(preorder, 0, n - 1, 0, m - 1, inOrderIndexMap);
    }
    
    /**
     * 先序遍历头在中序遍历中 头左边为二叉树的左子树 头右边数字为二叉树的右树
     * 1.拿出先序遍历的头  在中序遍历中找出头在中序遍历的位置
     * @param preorder
     * @param s1        先序构造开始位置
     * @param e1        先序构造结束位置
     * @param s2        中序构造开始位置
     * @param e2        中序构造结束位置
     * @param inOrderIndexMap  中序各位置元素下标
     * @return
     */
    private TreeNode process(int[] preorder, int s1, int e1, int s2, int e2, Map<Integer, Integer> inOrderIndexMap) {
        if (s1 > e1) {
            return null;
        }
        // 根据先序遍历的开始位置先构造出一颗子树的头
        TreeNode head = new TreeNode(preorder[s1]);
        if (s1 == e1) {
            // 此时先序和后续同节点
            return head;
        }
        // 找出当前头在中序遍历中的位置
        // 中序遍历s2~headInOrderIndex-1 即为当前头的左树
        // 先序遍历s1+1~s1+count(中序遍历左树元素个数)为当前头的左树
        // 在中序遍历headInOrderIndex + 1~e2 中当前树的右树
        // 先序遍历中s1+count(中序遍历左树元素个数)+1~e1 为当前树的右树
        int headInOrderIndex = inOrderIndexMap.get(preorder[s1]);
        // 左树节点数量
        int count = headInOrderIndex - s2;
        // 先序数组中左树结束位置count + s1 中序数组左树终止位置headInOrderIndex - 1
        head.left = process(preorder, s1 + 1, count + s1, s2, headInOrderIndex - 1, inOrderIndexMap);
        // 先序数组左树剩余位置为右树节点
        head.right = process(preorder, count + s1 + 1, e1, headInOrderIndex + 1, e2, inOrderIndexMap);
        return head;
    }
    
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7}, inorder = {9,3,15,20,7};
        ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        TreeNode treeNode = constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(preorder, inorder);
        List<Integer> preOrders = preOrder(treeNode);
        System.out.println(preOrders);
        List<Integer> inOrders = inOrder(treeNode);
        System.out.println(inOrders);
        List<Integer> postOrders = postOrder(treeNode);
        System.out.println(postOrders);
    }
    
    private static List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            result.add(treeNode.val);
            TreeNode right = treeNode.right;
            if (right != null) {
                stack.push(right);
            }
            TreeNode left = treeNode.left;
            if (left != null) {
                stack.push(left);
            }
        }
        return result;
    }
    
    private static List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                cur = pop.right;
            }
        }
        return result;
    }
    
    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prePop = null;
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null && prePop != peek.left && prePop != peek.right) {
                stack.push(peek.left);
            } else if (peek.right != null && prePop != peek.right) {
                stack.push(peek.right);
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                prePop = pop;
            }
        }
        return result;
    }
}
