package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 206. Reverse Linked List
 * Easy
 * 14198
 * 245
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 *
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 * @date 2022/8/30 12:58
 */
public class ReverseLinkedList {
    
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return process(head)[1];
    }
    
    /**
     * 返回结果listnode数组0位置为前节点 1位置为新链表头节点
     * 将后面节点next指针指向前节点
     * 走到链表最后一个节点 开始执行将后面节点next指针指当前节点
     * 当前节点下一节点指向空
     * @param head
     * @return
     */
    private ListNode[] process(ListNode head) {
        if (head.next == null) {
            return new ListNode[]{head, head};
        }
        ListNode[] nexts = process(head.next);
        nexts[0].next = head;
        head.next = null;
        return new ListNode[]{head, nexts[1]};
    }
}
