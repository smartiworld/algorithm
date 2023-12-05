package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.1.7 二叉树最大路径和
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
 *
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 *
 * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 *
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 * @date 2023/12/4 14:07
 */
public class BinaryTreeMaximumPathSumAllKinds {
    
    public int maximumPathSum;
    
    /**
     * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
     * @param root
     * @return
     */
    public static int maximumPathSumIncludeRoot(TreeNode root) {
        return maximumPathSumIncludeHeadProcess(root).maximumPathSum;
    }
    
    public static TreeNodeInfo maximumPathSumIncludeHeadProcess(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNodeInfo leftTreeNodeInfo = maximumPathSumIncludeHeadProcess(head.left);
        TreeNodeInfo rightTreeNodeInfo = maximumPathSumIncludeHeadProcess(head.right);
        int maximumPathSum = head.val;
        int curPathSum = head.val;
        int childMaximumPathSum = 0;
        if (leftTreeNodeInfo != null) {
            childMaximumPathSum = leftTreeNodeInfo.fromHeadMaximumPathSum;
            maximumPathSum = leftTreeNodeInfo.maximumPathSum;
        }
        if (rightTreeNodeInfo != null) {
            childMaximumPathSum = Math.max(childMaximumPathSum, rightTreeNodeInfo.fromHeadMaximumPathSum);
            maximumPathSum = Math.max(maximumPathSum, rightTreeNodeInfo.maximumPathSum);
        }
        curPathSum += childMaximumPathSum;
        maximumPathSum = Math.max(maximumPathSum, curPathSum);
        return new TreeNodeInfo(maximumPathSum, curPathSum);
    }
    
    private static int max = Integer.MIN_VALUE;
    
    public static int maximumPathSumIncludeRoot1(TreeNode root) {
        maximumPathSumIncludeRoot1Process(root, 0);
        return max;
    }
    
    private static void maximumPathSumIncludeRoot1Process(TreeNode head, int pre) {
        if (head.left == null && head.right == null) {
            max = Math.max(max, head.val + pre);
            return ;
        }
        if (head.left != null) {
            maximumPathSumIncludeRoot1Process(head.left, pre + head.val);
        }
        if (head.right != null) {
            maximumPathSumIncludeRoot1Process(head.right, pre + head.val);
        }
    }
    
    public static int maximumPathSumIncludeRoot2(TreeNode root) {
        maximumPathSumIncludeRoot1Process(root, 0);
        return max;
    }
    
    private static int maximumPathSumIncludeRoot2Process(TreeNode head) {
        if (head.left == null && head.right == null) {
            return head.val;
        }
        int next = Integer.MIN_VALUE;
        if (head.left != null) {
            next = maximumPathSumIncludeRoot2Process(head.left);
        }
        if (head.right != null) {
            next = Math.max(next, maximumPathSumIncludeRoot2Process(head.right));
        }
        return next + head.val;
    }
    
    public static class TreeNodeInfo {
        // 最大累加和
        public int maximumPathSum;
        // 包含当前头最大路径和
        public int fromHeadMaximumPathSum;
        
        public TreeNodeInfo(int maximumPathSum, int fromHeadMaximumPathSum) {
            this.maximumPathSum = maximumPathSum;
            this.fromHeadMaximumPathSum = fromHeadMaximumPathSum;
        }
    }
    
    /**
     * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
     * @param root
     * @return
     */
    public static int maximumPathSumToChildren(TreeNode root) {
        return maximumPathSumToChildrenProcess(root).maximumPathSum;
    }
    
    public static TreeNodeInfo maximumPathSumToChildrenProcess(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNodeInfo leftTreeNodeInfo = maximumPathSumIncludeHeadProcess(head.left);
        TreeNodeInfo rightTreeNodeInfo = maximumPathSumIncludeHeadProcess(head.right);
        // 左子树最大路径和
        int leftMaximumPathSum = Integer.MIN_VALUE;
        // 右子树最大路径和
        int rightMaximumPathSum = Integer.MIN_VALUE;
        // 左树包含当前节点最大路径和 包含当前节点
        int leftAndHeadMaximumPathSum = head.val;
        // 右树包含当前节点最大路径和 包含当前节点
        int rightAndHeadMaximumPathSum = head.val;
        if (leftTreeNodeInfo != null) {
            leftAndHeadMaximumPathSum += leftTreeNodeInfo.fromHeadMaximumPathSum;
            // 单独左子树最大值
            leftMaximumPathSum = leftTreeNodeInfo.maximumPathSum;
        }
        if (rightTreeNodeInfo != null) {
            rightAndHeadMaximumPathSum += rightTreeNodeInfo.fromHeadMaximumPathSum;
            // 单独右书最大值
            rightMaximumPathSum = rightTreeNodeInfo.maximumPathSum;
        }
        int fromHeadMaximumPathSum = Math.max(head.val, Math.max(leftMaximumPathSum, rightMaximumPathSum));
        // leftMaximumPathSum 最测最优 一定大于fromHeadMaximumPathSum
        int maximumPathSum = Math.max(head.val, Math.max(Math.max(leftAndHeadMaximumPathSum, rightAndHeadMaximumPathSum), Math.max(leftMaximumPathSum, rightMaximumPathSum)));
        return new TreeNodeInfo(maximumPathSum, fromHeadMaximumPathSum);
    }
    
    /**
     * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
     * @param root
     * @return
     */
    public static int maximumPathSum(TreeNode root) {
        return maximumPathSumProcess(root).maximumPathSum;
    }
    
    public static TreeNodeInfo maximumPathSumProcess(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNodeInfo leftTreeNodeInfo = maximumPathSumIncludeHeadProcess(head.left);
        TreeNodeInfo rightTreeNodeInfo = maximumPathSumIncludeHeadProcess(head.right);
        
        // 左边最大路径和
        int leftPathSum = Integer.MIN_VALUE;
        int rightPathSum = Integer.MIN_VALUE;
        int leftAndHeadPathSum = head.val;
        int rightAndHeadPathSum = head.val;
        int leftHeadAndRightPathSum = head.val;
        if (leftTreeNodeInfo != null) {
            leftPathSum = leftTreeNodeInfo.maximumPathSum;
            leftAndHeadPathSum += leftTreeNodeInfo.fromHeadMaximumPathSum;
            leftHeadAndRightPathSum += leftTreeNodeInfo.fromHeadMaximumPathSum;
        }
        if (rightTreeNodeInfo != null) {
            rightPathSum = rightTreeNodeInfo.maximumPathSum;
            rightAndHeadPathSum += rightTreeNodeInfo.fromHeadMaximumPathSum;
            leftHeadAndRightPathSum += rightTreeNodeInfo.fromHeadMaximumPathSum;
        }
        // 包含当前头节点最大路径和 只能从当前节点向下
        int fromHeadMaximumPathSum = Math.max(Math.max(leftAndHeadPathSum, rightAndHeadPathSum), head.val);
        // 以当前head为头 最大路径和
        // 包含当前节点 1.当前节点 2.当前节点和包括左累加和 3.当前节点和包含右头累加和 4。包含左右头累加和
        // 不包含当前节点 5.左最大和 6.右最大和
        int maximumPathSum = Math.max(head.val, Math.max(Math.max(leftHeadAndRightPathSum, Math.max(leftAndHeadPathSum, rightAndHeadPathSum)), Math.max(leftPathSum, rightPathSum)));;
        return new TreeNodeInfo(maximumPathSum, fromHeadMaximumPathSum);
    }
    
    /**
     * 4）路径可以从任何节点出发，必须到叶子节点，返回最大路径和
     * @param root
     * @return
     */
    public static int maximumPathSumNeedToLeaf(TreeNode root) {
        maximumPathSumNeedToLeafProcess(root);
        return max;
    }
    
    public static int maximumPathSumNeedToLeafProcess(TreeNode head) {
        if (head.left == null && head.right == null) {
            max = Math.max(max, head.val);
            return head.val;
        }
        int next = Integer.MIN_VALUE;
        if (head.left != null) {
            next = maximumPathSumNeedToLeafProcess(head.left);
        }
        if (head.right != null) {
            next = Math.max(next, maximumPathSumNeedToLeafProcess(head.right));
        }
        int ans = next + head.val;
        max = Math.max(ans, max);
        return ans;
    }
    
}
