package com.iworld.algorithm.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 计算二叉树最大宽度
 * @date 2022/3/4 16:21
 */
public class BinaryTreeMaxWidth {
    
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
        //CommonBinaryTree<Integer> tree11 = new CommonBinaryTree<>(11);
        tree5.left = tree10;
        //tree5.right = tree11;
        //CommonBinaryTree<Integer> tree12 = new CommonBinaryTree<>(12);
        //tree6.left = tree12;
        //CommonBinaryTree<Integer> tree13 = new CommonBinaryTree<>(13);
        //tree7.right = tree13;
        int maxTreeWidth = getMaxTreeWidthUseMap(head);
        int maxTreeWidth2 = getMaxTreeWidth(head);
        System.out.println(maxTreeWidth + "---------" + maxTreeWidth2);
    }
    
    /**
     * 二叉树宽度 使用map记录当前节点层 如果当前层数不和前层数相同 表示已经开启下一行，
     * 此时计算上层最大宽度，重置当前层宽度，将上一层数修改当前层数
     * @param head
     * @return
     */
    public static int getMaxTreeWidthUseMap(CommonBinaryTree<Integer> head) {
        // 最大宽度
        int maxWidth = 0;
        if (head != null) {
            Queue<CommonBinaryTree<Integer>> queue = new LinkedList<>();
            queue.offer(head);
            // 记录当前节点层数
            Map<CommonBinaryTree<Integer>, Integer> map = new HashMap();
            map.put(head, 1);
            // 当前层的宽度
            int curWidth = 0;
            // 上一层是多少层了
            int preLeve = 1;
            while (!queue.isEmpty()) {
                CommonBinaryTree<Integer> poll = queue.poll();
                int curLeve = map.get(poll);
                if (poll.left != null) {
                    map.put(poll.left, curLeve + 1);
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    map.put(poll.right, curLeve + 1);
                    queue.offer(poll.right);
                }
                // 当前没有切换层 当前宽度++
                if (preLeve == curLeve) {
                    curWidth ++;
                } else {
                    // 切换层表示上一层已执行完毕 计算最大宽度
                    maxWidth = Math.max(maxWidth, curWidth);
                    // 上一层来到当前层
                    preLeve = curLeve;
                    // 初始化当前层宽度
                    curWidth = 1;
                }
            }
            // 切换层数的时候进行结算 上面循环执行完毕后 最后一层还没有结算
            maxWidth = Math.max(maxWidth, curWidth);
        }
        return maxWidth;
    }
    
    /**
     * 获取数的最大宽度
     * @param head
     * @return
     */
    public static int getMaxTreeWidth(CommonBinaryTree<Integer> head) {
        int maxWidth = 0;
        if (head != null) {
            Queue<CommonBinaryTree<Integer>> queue = new LinkedList<>();
            queue.offer(head);
            int curWidth = 0;
            // 当前层数节点
            CommonBinaryTree<Integer> curTree = head;
            // 下层最右节点 子节点向右只要有节点就给当前值
            CommonBinaryTree<Integer> nextEndTree = null;
            while (!queue.isEmpty()) {
                CommonBinaryTree<Integer> poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                    nextEndTree = poll.left;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    nextEndTree = poll.right;
                }
                curWidth ++;
                // 当前节点走到当前节点最后一个节点位置，将下层最后节点赋值到当前节点，重置当前层宽度，计算当前层最大宽度
                // 每走完一层结算一层
                if (curTree == poll) {
                    maxWidth = Math.max(maxWidth, curWidth);
                    // 当前层走完后将下层最右节点赋值当前节点
                    curTree = nextEndTree;
                    curWidth = 0;
                }
            }
        }
        return maxWidth;
    }
}
