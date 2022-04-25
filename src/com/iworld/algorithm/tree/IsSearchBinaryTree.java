package com.iworld.algorithm.tree;

import java.util.ArrayList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 二叉搜索树 右数所有节点大于当前父节点  左数所有节点小于当前父节点
 * 拆分成小的树处理 如果每个子树都是二叉搜索树 整棵树就为二叉搜索树
 * 如果有一颗子树不为 二叉搜索树 此树则不是二叉搜索树
 * @date 2022/3/10 17:49
 */
public class IsSearchBinaryTree {
    
    /**
     * 判断当前节点数是否是二叉搜索树
     *
     * @param head
     * @return
     */
    public static boolean isSearchBinaryTree(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return true;
        }
        return getTreeInfo(head).isBST;
    }
    
    /**
     *
     * @param head
     * @return
     */
    public static TreeInfo getTreeInfo(CommonBinaryTree<Integer> head) {
        if (head == null) {
            // 当前节点为null 返回当前节点是二叉搜索树 min为int最大值  max为int最小值 此时能保证父节点在此子树为二叉搜索树
            return new TreeInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        TreeInfo leftInfo = getTreeInfo(head.left);
        TreeInfo rightInfo = getTreeInfo(head.right);
        boolean isBST = false;
        if (leftInfo.isBST && rightInfo.isBST && head.value > leftInfo.max && head.value < rightInfo.min) {
            isBST = true;
        }
        // 此时认为当前树就是二叉搜索树 最大值则为右树最大值 最小值左树最小值
        int min = leftInfo.min == Integer.MAX_VALUE ? head.value : Math.min(leftInfo.min, head.value);
        int max = rightInfo.max == Integer.MIN_VALUE ? head.value : Math.max(rightInfo.max, head.value);
        return new TreeInfo(isBST, min, max);
    }
    
    /**
     * 判断当前节点数是否是二叉搜索树
     * @param head
     * @return
     */
    public static boolean isSearchBinaryTree2(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }
    
    public static TreeInfo process(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return null;
        }
        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);
        
        boolean isBST = false;
        if (leftInfo != null && rightInfo != null) {
            if (leftInfo.isBST && rightInfo.isBST && head.value > leftInfo.max && head.value < rightInfo.min) {
                isBST = true;
            }
        } else if (leftInfo == null && rightInfo == null) {
            isBST = true;
        } else if ((leftInfo != null && leftInfo.isBST && head.value > leftInfo.max)) {
            isBST = true;
        } else if (rightInfo != null && rightInfo.isBST && head.value < rightInfo.min) {
            isBST = true;
        }
        int min = leftInfo == null ? head.value : Math.min(leftInfo.min, head.value);
        int max = rightInfo == null ? head.value : Math.max(rightInfo.max, head.value);
        return new TreeInfo(isBST, min, max);
    }
    
    public static TreeInfo process2(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return null;
        }
        TreeInfo leftInfo = process2(head.left);
        TreeInfo rightInfo = process2(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.min(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.min(max, rightInfo.max);
        }
        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.max > head.value)) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST || rightInfo.min < head.value) {
            isBST = false;
        }
        return new TreeInfo(isBST, min, max);
    }
    
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            CommonBinaryTree<Integer> head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isSearchBinaryTree2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
        //System.out.println(isSearchBinaryTree(head));
    }
    
    static class TreeInfo {
        // 是否时二叉搜索树
        private boolean isBST;
        // 子树最小值
        private int min;
        // 子树最大值
        private int max;
    
        TreeInfo (boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    
        @Override
        public String toString() {
            return "TreeInfo{" +
                    "isBST=" + isBST +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
    
    // for test
    public static CommonBinaryTree generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }
    
    // for test
    public static CommonBinaryTree generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        CommonBinaryTree head = new CommonBinaryTree((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }
    
    /**
     * 暴力方式
     * @param head
     * @return
     */
    public static boolean isBST1(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return true;
        }
        ArrayList<CommonBinaryTree<Integer>> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }
    
    public static void in(CommonBinaryTree<Integer> head, ArrayList<CommonBinaryTree<Integer>> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }
    
    
    
}
