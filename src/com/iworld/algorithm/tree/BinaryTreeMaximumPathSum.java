package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 124. Binary Tree Maximum Path Sum   Hard
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -1000 <= Node.val <= 1000
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * @date 2022/8/22 18:03
 */
public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }
    
    /**
     * 1.包含当前节点
     *   1.1自己一个节点
     *   1.2自己和左树
     *   1.3自己和右树
     *   1.4自己和左右树
     * 2.不包含当前节点 单
     *   2.1左树
     *   2.2右树
     * @param node
     * @return
     */
    private TreeInfo process(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeInfo leftTreeInfo = process(node.left);
        TreeInfo rightTreeInfo = process(node.right);
        // 1.2自己和左树
        int leftAndCur = Integer.MIN_VALUE;
        // 1.3自己和右树
        int rightAndCur = Integer.MIN_VALUE;
        // 2.1左树
        int onlyLeft = Integer.MIN_VALUE;
        // 2.2右树
        int onlyRight = Integer.MIN_VALUE;
        // 1.4自己和左右树
        int all = node.val;
        if (leftTreeInfo != null) {
            onlyLeft = leftTreeInfo.maxPathSum;
            leftAndCur = node.val + leftTreeInfo.fromHeadMaxPathSum;
            all += leftTreeInfo.fromHeadMaxPathSum;
        }
        if (rightTreeInfo != null) {
            onlyRight = rightTreeInfo.maxPathSum;
            rightAndCur = node.val + rightTreeInfo.fromHeadMaxPathSum;
            all += rightTreeInfo.fromHeadMaxPathSum;
        }
        int fromHeadMaxPathSum = Math.max(Math.max(leftAndCur, rightAndCur), node.val);
        int maxPathSum = Math.max(Math.max(Math.max(Math.max(onlyLeft, onlyRight), Math.max(leftAndCur, rightAndCur)), all), node.val);
        return new TreeInfo(maxPathSum, fromHeadMaxPathSum);
    }
    
    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process2(root).maxPathSum;
    }
    
    private TreeInfo process2(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeInfo leftTreeInfo = process(node.left);
        TreeInfo rightTreeInfo = process(node.right);
        // 1.2自己和左树  1.3自己和右树
        int childAndCur = Integer.MIN_VALUE;
        // 2.1左树 2.2右树
        int onlyChild = Integer.MIN_VALUE;
        // 1.4自己和左右树
        int all = node.val;
        if (leftTreeInfo != null) {
            onlyChild = leftTreeInfo.maxPathSum;
            childAndCur = node.val + leftTreeInfo.fromHeadMaxPathSum;
            all += leftTreeInfo.fromHeadMaxPathSum;
        }
        if (rightTreeInfo != null) {
            onlyChild = Math.max(onlyChild, rightTreeInfo.maxPathSum);
            childAndCur = Math.max(node.val + rightTreeInfo.fromHeadMaxPathSum, childAndCur);
            all += rightTreeInfo.fromHeadMaxPathSum;
        }
        int fromHeadMaxPathSum = Math.max(childAndCur, node.val);
        int maxPathSum = Math.max(Math.max(Math.max(onlyChild, childAndCur), all), node.val);
        return new TreeInfo(maxPathSum, fromHeadMaxPathSum);
    }
    
    public static class TreeInfo {
        // 当前树上最大路径
        public int maxPathSum;
        // 以当前头节点开始最大和
        public int fromHeadMaxPathSum;
        public TreeInfo(int maxPathSum, int fromHeadMaxPathSum) {
            this.maxPathSum = maxPathSum;
            this.fromHeadMaxPathSum = fromHeadMaxPathSum;
        }
    }
    
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(-2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();
        System.out.println(binaryTreeMaximumPathSum.maxPathSum(t1));
        System.out.println(binaryTreeMaximumPathSum.maxPathSum2(t1));
    }
}
