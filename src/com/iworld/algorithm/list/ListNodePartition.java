package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 86.分割链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 示例 1：
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
 * 链接：https://leetcode.cn/problems/partition-list
 * @date 2022/5/27 14:19
 */
public class ListNodePartition {
    
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode bigHead = null;
        ListNode bigTail = null;
        ListNode listNode = head;
        while (listNode != null) {
            ListNode next = listNode.next;
            listNode.next = null;
            if (listNode.val < x) {
                if (smallHead == null) {
                    smallHead = listNode;
                } else {
                    smallTail.next = listNode;
                }
                smallTail = listNode;
            } else {
                if (bigHead == null) {
                    bigHead = listNode;
                } else {
                    bigTail.next = listNode;
                }
                bigTail = listNode;
            }
            listNode = next;
        }
        if (smallTail != null) {
            smallTail.next = bigHead;
            return smallHead;
        } else {
            return bigHead;
        }
    }
    
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(4);
        head.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(2);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        ListNode l6 = new ListNode(2);
        l5.next = l6;
        ListNodePartition listNodePartition = new ListNodePartition();
        ListNode listNode = listNodePartition.partition(head, 3);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    
}
