package com.iworld.algorithm.zijie;

import com.iworld.algorithm.list.ListNode;

/**
 * @author gq.cai
 * @version 1.0
 * @description 25. Reverse Nodes in k-Group
 * Hard
 * 10.9K
 * 570
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * @date 2023/4/19 13:09
 */
public class ReverseNodesInKGroup {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode start = head;
        int group = 0;
        boolean isChooseHead = false;
        ListNode preTail = null;
        while (cur != null) {
            ListNode next = cur.next;
            group++;
            if (group % k == 0) {
                if (!isChooseHead) {
                    head = cur;
                    isChooseHead = true;
                }
                reverseListNode(start, cur);
                if (preTail == null) {
                    preTail = start;
                } else {
                    preTail.next = cur;
                    preTail = start;
                }
                start = next;
            }
            cur = next;
        }
        return head;
    }
    
    /**
     * 反转当前链表
     * @param start  反转前链表头 反转后链表尾部
     * @param end    反转前链表尾部 反转后链表头部
     * @return
     */
    private void reverseListNode(ListNode start, ListNode end) {
        ListNode cur = start;
        ListNode pre = end.next;
        ListNode stop = end.next;
        while (cur != stop) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //return pre;
    }
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);;
        l4.next = l5;
        ListNode l6 = new ListNode(6);;
        l5.next = l6;
        ReverseNodesInKGroup  reverseNodesInKGroup = new ReverseNodesInKGroup();
        int k = 2;
        ListNode listNode = reverseNodesInKGroup.reverseKGroup(l1, k);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
