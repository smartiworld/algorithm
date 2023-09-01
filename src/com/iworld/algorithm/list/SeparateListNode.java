package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 分割链表
 * @date 2021/9/23 15:13
 */
public class SeparateListNode {
    
    /**
     * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
     *
     * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
     *
     * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
     *
     * 返回一个由上述 k 部分组成的数组。
     *
     * 示例1：
     * 输入：head = [1,2,3], k = 5
     * 输出：[[1],[2],[3],[],[]]
     * 解释：
     * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
     * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
     *
     * 示例2：
     * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
     * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
     * 解释：
     * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
     * https://leetcode-cn.com/problems/split-linked-list-in-parts/
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] listNodes = new ListNode[k];
        if (head == null || k == 0) {
            return listNodes;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            ListNode next = cur.next;
            cur = next;
            length ++;
        }
        int j = Math.min(k, length);
        int everyCount = length / j;
        int count = 0;
        cur = head;
        ListNode h = cur;
        ListNode pre;
        int useEveryCount;
        for (int i = 0; i < listNodes.length ; i++) {
            if (length % j > 0) {
                useEveryCount = everyCount + 1;
                length --;
            } else {
                useEveryCount = everyCount;
            }
            while (cur != null) {
                count ++;
                pre = cur;
                cur = cur.next;
                if (count >= useEveryCount) {
                    pre.next = null;
                    count = 0;
                    listNodes[i] = h;
                    h = cur;
                    break;
                }
            }
        }
        return listNodes;
    }
    
    public static void main(String[] args) {
        SeparateListNode separateListNode = new SeparateListNode();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        ListNode[] listNodes = separateListNode.splitListToParts(listNode1, 9);
        for (int i = 0; i < listNodes.length; i++) {
            ListNode listNode = listNodes[i];
            while (listNode != null) {
                System.out.println(listNode.val);
                listNode = listNode.next;
            }
            System.out.println("================");
        }
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
        
        @Override
        public String toString() {
            return "val=" + val;
        }
    }
}
