package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 83. Remove Duplicates from Sorted List   Easy
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 * Example 1:
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 * Example 2:
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * @date 2022/6/20 16:37
 */
public class RemoveDuplicatesSortedList {
    
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (cur.val != next.val) {
                pre.next = next;
                pre = next;
            }
            cur = next;
        }
        return head;
    }
    
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val != pre.val) {
                pre.next = cur;
                pre = cur;
            } else {
                pre.next = null;
            }
            cur = cur.next;
        }
        return head;
    }
}
