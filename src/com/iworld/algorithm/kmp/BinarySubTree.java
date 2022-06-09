package com.iworld.algorithm.kmp;

import com.iworld.algorithm.tree.CommonBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description KMP
 * 给定两个二叉树 tree1和tree2 判断tree2是否为tree1的子树
 * 序列化二叉树为一个数组，将一个数组看作一个串做kmp 如果匹配 则为子树 否则不为
 * @date 2022/6/8 13:42
 */
public class BinarySubTree {
    
    public static boolean isSubTree(CommonBinaryTree<String> parent, CommonBinaryTree<String> subTree) {
        // 如果子树为空树 一定是parent子树
        if (subTree == null) {
            return true;
        }
        // 子树不为空 parent为空 subTree一定不是parent的子树
        if (parent == null) {
            return false;
        }
        List<String> parentSerializer = preSerializerTree(parent);
        List<String> subSerializer = preSerializerTree(subTree);
        int x = 0;
        int y = 0;
        int[] sameStrLength = getSameStrLength(subSerializer);
        while (x < parentSerializer.size() && y < subSerializer.size()) {
            if (parentSerializer.get(x).equals(subSerializer.get(y))) {
                x ++;
                y ++;
            } else if (y > 0) {
                y = sameStrLength[y];
            } else {
                x ++;
            }
        }
        return y == subSerializer.size();
    }
    
    /**
     * 二叉树先序遍历
     * @param parent
     * @return
     */
    private static List<String> preSerializerTree(CommonBinaryTree<String> parent) {
        List<String> list = new ArrayList<>();
        Stack<CommonBinaryTree<String>> stack = new Stack<>();
        stack.push(parent);
        while (!stack.isEmpty()) {
            CommonBinaryTree<String> pop = stack.pop();
            if (pop == null) {
                list.add("null");
                continue;
            } else {
                list.add(pop.value);
            }
            stack.push(pop.right);
            stack.push(pop.left);
        }
        return list;
    }
    
    private static int[] getSameStrLength(List<String> list) {
        if (list.size() == 1) {
            return new int[]{-1};
        }
        int[] res = new int[list.size()];
        res[0] = -1;
        res[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < list.size()) {
            if (list.get(i - 1).equals(list.get(cn))) {
                res[i++] = ++cn;
            } else if (cn > 0) {
                cn = res[cn];
            } else {
                i ++;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        CommonBinaryTree<String> parent = new CommonBinaryTree<>("1");
        CommonBinaryTree<String> t2 = new CommonBinaryTree<>("3");
        CommonBinaryTree<String> t3 = new CommonBinaryTree<>("7");
        parent.left = t2;
        parent.right = t3;
        CommonBinaryTree<String> t4 = new CommonBinaryTree<>("10");
        CommonBinaryTree<String> t5 = new CommonBinaryTree<>("128");
        t3.left = t4;
        t3.right = t5;
        CommonBinaryTree<String> sub1 = new CommonBinaryTree<>("7");
        CommonBinaryTree<String> sub2 = new CommonBinaryTree<>("10");
        CommonBinaryTree<String> sub3 = new CommonBinaryTree<>("128");
        //sub1.left = sub2;
        //sub1.right = sub3;
        System.out.println(isSubTree(parent, sub1));
    }
}
