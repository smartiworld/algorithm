package com.iworld.algorithm.tree.morris;

import com.iworld.algorithm.tree.CommonBinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 莫里斯 二叉树 morris遍历 额外空间复杂度O(1)
 * @date 2022/8/19 12:32
 */
public class BinaryTreeMorris {
    
    /**
     * 1.来到当前节点的左孩子 如果没有左孩子 当前节点来到当前节点的右孩子
     * 2.当前节点有左孩子
     *  1.来到当前左孩子最右节点 如果当前左孩子最右节点为null表示第一次来到当前节点 然后将当前节点最右节点的右指针指向当前节点，当前节点来到当前节点左孩子
     *  2.来到当前左孩子最右节点 如果当前左孩子最右节点为当前节点，表示第二次来到当前节点 此时需要去处理当前节点的右节点，
     *  当前节点来到当前节点的右孩子 当前节点左孩子最右节点指向null
     * @param parent
     */
    public void morris(CommonBinaryTree<Integer> parent) {
        CommonBinaryTree<Integer> cur = parent;
        while (cur != null) {
            // 当前节点左孩子 也为最右节点初始节点
            CommonBinaryTree<Integer> mostRight = cur.left;
            if (mostRight != null) {
                // 如果当前左节点最右节点不为null或者不为当前节点 一直找到最右
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 最右节点右孩子为null
                if (mostRight.right == null) {
                    // 最右节点右孩子指向当前节点 第一次来到当前节点
                    mostRight.right = cur;
                    // 当前节点来到当前节点左孩子
                    cur = cur.left;
                } else {
                    // 最右节点右孩子已经指向当前节点
                    // 表示第二次来到当前节点 需要处理当前节点右孩子
                    cur = cur.right;
                    // 将当前节点左孩子最右节点指向null
                    mostRight.right = null;
                }
            } else {
                // 当前节点没有左孩子
                cur = cur.right;
            }
        }
    }
    
    public List<Integer> morrisPre(CommonBinaryTree<Integer> parent) {
        List<Integer> ans = new ArrayList<>();
        CommonBinaryTree<Integer> cur = parent;
        while (cur != null) {
            // 当前节点左孩子 也为最右节点初始节点
            CommonBinaryTree<Integer> mostRight = cur.left;
            if (mostRight != null) {
                // 如果当前左节点最右节点不为null或者不为当前节点 一直找到最右
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 最右节点右孩子为null
                if (mostRight.right == null) {
                    // 最右节点右孩子指向当前节点 第一次来到当前节点
                    mostRight.right = cur;
                    ans.add(cur.value);
                    // 当前节点来到当前节点左孩子
                    cur = cur.left;
                } else {
                    // 最右节点右孩子已经指向当前节点
                    // 表示第二次来到当前节点 需要处理当前节点右孩子
                    cur = cur.right;
                    // 将当前节点左孩子最右节点指向null
                    mostRight.right = null;
                }
            } else {
                ans.add(cur.value);
                // 当前节点没有左孩子
                cur = cur.right;
            }
        }
        return ans;
    }
    
    public List<Integer> morrisIn(CommonBinaryTree<Integer> parent) {
        List<Integer> ans = new ArrayList<>();
        CommonBinaryTree<Integer> cur = parent;
        while (cur != null) {
            // 当前节点左孩子 也为最右节点初始节点
            CommonBinaryTree<Integer> mostRight = cur.left;
            if (mostRight != null) {
                // 如果当前左节点最右节点不为null或者不为当前节点 一直找到最右
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 最右节点右孩子为null
                if (mostRight.right == null) {
                    // 最右节点右孩子指向当前节点 第一次来到当前节点
                    mostRight.right = cur;
                    // 当前节点来到当前节点左孩子
                    cur = cur.left;
                } else {
                    ans.add(cur.value);
                    // 最右节点右孩子已经指向当前节点
                    // 表示第二次来到当前节点 需要处理当前节点右孩子
                    cur = cur.right;
                    // 将当前节点左孩子最右节点指向null
                    mostRight.right = null;
                }
            } else {
                ans.add(cur.value);
                // 当前节点没有左孩子
                cur = cur.right;
            }
        }
        return ans;
    }
    
    public List<Integer> morrisPost(CommonBinaryTree<Integer> parent) {
        List<Integer> ans = new ArrayList<>();
        CommonBinaryTree<Integer> cur = parent;
        while (cur != null) {
            // 当前节点左孩子 也为最右节点初始节点
            CommonBinaryTree<Integer> mostRight = cur.left;
            if (mostRight != null) {
                // 如果当前左节点最右节点不为null或者不为当前节点 一直找到最右
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 最右节点右孩子为null
                if (mostRight.right == null) {
                    // 最右节点右孩子指向当前节点 第一次来到当前节点
                    mostRight.right = cur;
                    // 当前节点来到当前节点左孩子
                    cur = cur.left;
                } else {
                    // 将当前节点左孩子最右节点指向null
                    mostRight.right = null;
                    // 第二次来到当前节点的时候 处理左孩子右整条边
                    addEdge(cur.left, ans);
                    // 最右节点右孩子已经指向当前节点
                    // 表示第二次来到当前节点 需要处理当前节点右孩子
                    cur = cur.right;
                }
            } else {
                // 当前节点没有左孩子
                cur = cur.right;
            }
        }
        addEdge(parent, ans);
        return ans;
    }
    
    private void addEdge(CommonBinaryTree<Integer> cur, List<Integer> ans) {
        CommonBinaryTree<Integer> tail = reversRightEdge(cur);
        reversRightEdge(tail, ans);
    }
    
    private CommonBinaryTree<Integer> reversRightEdge(CommonBinaryTree<Integer> parent) {
        CommonBinaryTree<Integer> cur = parent;
        CommonBinaryTree<Integer> pre = null;
        while (cur != null) {
            CommonBinaryTree<Integer> right = cur.right;
            cur.right = pre;
            pre = cur;
            cur = right;
        }
        return pre;
    }
    
    private void reversRightEdge(CommonBinaryTree<Integer> parent, List<Integer> ans) {
        CommonBinaryTree<Integer> cur = parent;
        CommonBinaryTree<Integer> pre = null;
        while (cur != null) {
            ans.add(cur.value);
            CommonBinaryTree<Integer> right = cur.right;
            cur.right = pre;
            pre = cur;
            cur = right;
        }
    }
    
    public void morris2(CommonBinaryTree<Integer> parent) {
        if (parent == null) {
            return;
        }
        CommonBinaryTree<Integer> cur = parent;
        CommonBinaryTree<Integer> mostRight = null;
        while (cur != null) {
            // cur有没有左树
            mostRight = cur.left;
            if (mostRight != null) { // 有左树的情况下
                // 找到cur左树上，真实的最右
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从while中出来，mostRight一定是cur左树上的最右节点
                // mostRight
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right != null -> mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
    
    public static void main(String[] args) {
        CommonBinaryTree<Integer> head = new CommonBinaryTree<>(1);
        CommonBinaryTree<Integer> tree2 = new CommonBinaryTree<>(2);
        CommonBinaryTree<Integer> tree3 = new CommonBinaryTree<>(3);
        head.left = tree2;
        head.right = tree3;
        CommonBinaryTree<Integer> tree4 = new CommonBinaryTree<>(4);
        CommonBinaryTree<Integer> tree5 = new CommonBinaryTree<>(5);
        tree2.left = tree4;
        tree2.right = tree5;
        CommonBinaryTree<Integer> tree6 = new CommonBinaryTree<>(6);
        CommonBinaryTree<Integer> tree7 = new CommonBinaryTree<>(7);
        tree3.left = tree6;
        tree3.right = tree7;
        CommonBinaryTree<Integer> tree8 = new CommonBinaryTree<>(8);
        CommonBinaryTree<Integer> tree9 = new CommonBinaryTree<>(9);
        tree4.left = tree8;
        tree4.right = tree9;
        CommonBinaryTree<Integer> tree10 = new CommonBinaryTree<>(10);
        CommonBinaryTree<Integer> tree11 = new CommonBinaryTree<>(11);
        tree5.left = tree10;
        tree5.right = tree11;
        CommonBinaryTree<Integer> tree12 = new CommonBinaryTree<>(12);
        tree6.left = tree12;
        CommonBinaryTree<Integer> tree13 = new CommonBinaryTree<>(13);
        tree7.right = tree13;
        BinaryTreeMorris binaryTreeMorris = new BinaryTreeMorris();
        System.out.println(binaryTreeMorris.morrisPre(head));
        System.out.println(binaryTreeMorris.morrisIn(head));
        System.out.println(binaryTreeMorris.morrisPost(head));
    }
}
