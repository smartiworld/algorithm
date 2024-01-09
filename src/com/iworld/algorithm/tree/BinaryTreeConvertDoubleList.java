package com.iworld.algorithm.tree;

import com.iworld.algorithm.list.CommonDoubleList;
import com.iworld.algorithm.util.CommonBinaryTreeUtils;
import com.iworld.algorithm.util.CommonDoubleListUtils;

import java.util.List;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.4.2 二叉树转换为双向连边
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
 *
 * 中序遍历 头尾相连
 * @date 2024/1/8 19:29
 */
public class BinaryTreeConvertDoubleList {
    
    
    public static CommonDoubleList<Integer> convertDoubleList(CommonBinaryTree<Integer> root) {
        CommonDoubleList<Integer>[] process = process(root);
        return process[0];
    }
    
    /**
     * 返回链表的头和尾 0位置存放头 1位置存放尾
     * 叶子节点头尾都是自己
     * 当前节点连左子树的尾节点
     * 当前节点连右子树的头节点
     * 此时已经将左子树 当前节点 和右子树已经连接起来
     * 新头节点为 左树的头节点
     * 新尾节点为 右树的尾节点
     * 返回新的头尾
     * @param head
     * @return
     */
    private static CommonDoubleList<Integer>[] process(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return null;
        }
        CommonDoubleList[] ans = new CommonDoubleList[2];
        CommonDoubleList<Integer> cur = new CommonDoubleList<>(head.value);
        CommonDoubleList<Integer>[] left = process(head.left);
        CommonDoubleList<Integer> newTail = cur;
        CommonDoubleList<Integer> newHead = cur;
        if (left != null) {
            left[1].next = cur;
            cur.pre = left[1];
            newHead = left[0];
        }
        CommonDoubleList<Integer>[] right = process(head.right);
        if (right != null) {
            cur.next = right[0];
            right[0].pre = cur;
            newTail = right[1];
        }
        ans[0] = newHead;
        ans[1] = newTail;
        return ans;
    }
    
    public static CommonDoubleList<Integer> convertDoubleListDeep(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return null;
        }
        Stack<CommonBinaryTree<Integer>> stack = new Stack<>();
        CommonDoubleList<Integer> head = new CommonDoubleList<>();
        CommonDoubleList<Integer> cur = head;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                CommonBinaryTree<Integer> pop = stack.pop();
                CommonDoubleList<Integer> next = new CommonDoubleList<>(pop.value);
                cur.next = next;
                next.pre = cur;
                cur = next;
                root = pop.right;
            }
        }
        CommonDoubleList<Integer> ans = head.next;
        ans.pre = null;
        return ans;
    }
    
    public static void main(String[] args) {
        int level = 2;
        int max = 10;
        CommonBinaryTree<Integer> integerBinaryTree = CommonBinaryTreeUtils.getIntegerBinaryTree(level, max);
        List<Integer> integers = CommonBinaryTreeUtils.inOrder(integerBinaryTree);
        System.out.println(integers);
        CommonDoubleList<Integer> integerCommonDoubleList = convertDoubleList(integerBinaryTree);
        List<Object> objects = CommonDoubleListUtils.serializerDoubleList(integerCommonDoubleList);
        System.out.println(objects.get(0));
        System.out.println(objects.get(1));
        System.out.println();
        CommonDoubleList<Integer> integerCommonDoubleListDeep = convertDoubleListDeep(integerBinaryTree);
        List<Object> objects1 = CommonDoubleListUtils.serializerDoubleList(integerCommonDoubleListDeep);
        System.out.println(objects1.get(0));
        System.out.println(objects1.get(1));
    
    }

}
