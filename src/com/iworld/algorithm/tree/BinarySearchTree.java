package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 搜索二叉树
 * @date 2022/4/30 11:34
 */
public class BinarySearchTree {
    
    /**
     * 给定一个树的根节点返回这颗树的最大二叉搜索树的头节点
     * @param root
     * @return
     */
    public static CommonBinaryTree<Integer> getBinarySearchTree(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return null;
        }
        return process(root).head;
    }
    
    /**
     * 当前节点为二叉搜索树
     * 1.最大二叉搜索树头节点为当前节点
     * 2.size为左树size+右树size+1
     * 3.max为当前节点子树最大值
     * 4.min为当前节点子树最小值
     * 5.isBST为true
     * 当前节点不是二叉搜索树
     * 1.最大二叉搜索树头节点为左右子树最大节点的头节点
     * 2.size为左右树最大值
     * 3.max为当前节点子树最大值
     * 4.min为当前节点子树最小值
     * 5.isBST为false
     * @param head
     * @return
     */
    private static TreeInfo process(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return null;
        }
        TreeInfo leftTreeInfo = process(head.left);
        TreeInfo rightTreeInfo = process(head.left);
        // 树节点数量
        int size = 1;
        int max = head.value;
        int min = head.value;
        boolean isBST = true;
        if (leftTreeInfo != null) {
            size += leftTreeInfo.size;
            min = Math.min(min, leftTreeInfo.min);
            max = Math.min(max, leftTreeInfo.max);
            // 左树不是二叉搜索树 或者 左树上有大于当前值的节点
            if (!leftTreeInfo.isBST || leftTreeInfo.max > head.value) {
                isBST = false;
            }
        }
        if (rightTreeInfo != null) {
            size += rightTreeInfo.size;
            min = Math.min(min, rightTreeInfo.min);
            max = Math.min(max, rightTreeInfo.max);
            // 右树不是二叉搜索树 或者 右树上有大于当前值的节点
            if (!rightTreeInfo.isBST || rightTreeInfo.min < head.value) {
                isBST = false;
            }
        }
        // 如果当前树为二叉搜索树 最大节点则为当前节点
        CommonBinaryTree<Integer> bstHead = head;
        if (!isBST) {
            // 当前树已经不是二叉搜索树的时候
            bstHead = leftTreeInfo == null ? rightTreeInfo.head :
                    (rightTreeInfo == null ? leftTreeInfo.head :
                            (leftTreeInfo.size < rightTreeInfo.size ? rightTreeInfo.head : leftTreeInfo.head));
            size = Math.max(leftTreeInfo.size, rightTreeInfo.size);
        }
        return new TreeInfo(bstHead, isBST, min, max, size);
    }
    
    /**
     * 当前节点为二叉搜索树
     * 1.最大二叉搜索树头节点为当前节点
     * 2.size为左树size+右树size+1
     * 3.max为当前节点子树最大值
     * 4.min为当前节点子树最小值
     * 5.isBST为true
     * 当前节点不是二叉搜索树
     * 1.最大二叉搜索树头节点为左右子树最大节点的头节点
     * 2.size为左右树最大值
     * 3.max为当前节点子树最大值
     * 4.min为当前节点子树最小值
     * 5.isBST为false
     * @param head
     * @return
     */
    private static TreeInfo process2(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return null;
        }
        TreeInfo leftTreeInfo = process2(head.left);
        TreeInfo rightTreeInfo = process2(head.left);
        // 树节点数量
        int size = 0;
        int max = head.value;
        int min = head.value;
        boolean isBST = false;
        // 如果当前树为二叉搜索树 最大节点则为当前节点
        CommonBinaryTree<Integer> bstHead = null;
        if (leftTreeInfo != null) {
            size = leftTreeInfo.size;
            min = Math.min(min, leftTreeInfo.min);
            max = Math.min(max, leftTreeInfo.max);
            bstHead = leftTreeInfo.head;
        }
        if (rightTreeInfo != null) {
            bstHead = size > rightTreeInfo.size ? bstHead : rightTreeInfo.head;
            size = Math.max(size, rightTreeInfo.size);
            min = Math.min(min, rightTreeInfo.min);
            max = Math.min(max, rightTreeInfo.max);
        }
        // 所树为空或者左树是二叉搜索树并且左树最大值小于当前值 并且
        // 右树为空或者右树为二叉搜索树并且右树的最小值大于当前值
        if ((leftTreeInfo == null || (leftTreeInfo.isBST && leftTreeInfo.max < head.value))
                && (rightTreeInfo == null || (rightTreeInfo.isBST && rightTreeInfo.min > head.value))) {
            isBST = true;
            size = (leftTreeInfo == null ? 0 : leftTreeInfo.size)
                    + (rightTreeInfo == null ? 0 : rightTreeInfo.size) + 1;
        }
        return new TreeInfo(bstHead, isBST, min, max, size);
    }
    
    
    /**
     * 给定一个树的根节点返回这颗树的最大二叉搜索树的节点个数
     * @param root
     * @return
     */
    public static int getMaxBinarySearchTreeSize(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return 0;
        }
        return process(root).size;
    }
    
    static class TreeInfo {
        // 二叉搜索树的头节点
        private CommonBinaryTree<Integer> head;
        // 是否时二叉搜索树
        private boolean isBST;
        // 子树最小值
        private int min;
        // 子树最大值
        private int max;
        // 二叉搜索树节点个数
        private int size;
        
        TreeInfo (CommonBinaryTree<Integer> head, boolean isBST, int min, int max, int size) {
            this.head = head;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = size;
        }
    
        @Override
        public String toString() {
            return "TreeInfo{" +
                    "head=" + head.value +
                    ", isBST=" + isBST +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
}
