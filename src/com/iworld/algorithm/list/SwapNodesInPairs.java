package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 24. Swap Nodes in Pairs
 * Medium
 * 8350
 * 337
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * @date 2022/10/22 17:08
 */
public class SwapNodesInPairs {
    
    /**
     * 递归改迭代 内存优些
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            ListNode nextNext = next.next;
            if (nextNext == null || nextNext.next == null) {
                cur.next = nextNext;
            }
            if (nextNext != null && nextNext.next != null) {
                cur.next = nextNext.next;
            }
            next.next = cur;
            cur = nextNext;
        }
        return newHead;
    }
    
    public ListNode swapPairs2(ListNode head) {
        return process(head);
    }
    
    
    private ListNode process(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode next = node.next;
        ListNode nextNext = next.next;
        next.next = node;
        node.next = process(nextNext);
        return next;
    }
    
    public static void main(String[] args) {
        SwapNodesInPairs swapNodesInPairs = new SwapNodesInPairs();
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        h1.next = h2;
        ListNode h3 = new ListNode(3);
        h2.next = h3;
        ListNode h4 = new ListNode(4);
        h3.next = h4;
        ListNode h5 = new ListNode(5);
        h4.next = h5;
        ListNode head = swapNodesInPairs.swapPairs(h1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
    
}
