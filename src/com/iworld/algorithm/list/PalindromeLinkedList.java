package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 234. Palindrome Linked List
 * Easy
 * 11887
 * 663
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 *
 * Example 1:
 *
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 *
 * Input: head = [1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 * https://leetcode.com/problems/palindrome-linked-list/
 * @date 2022/10/3 13:44
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null;
        ListNode cur = slow.next;
        slow.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ListNode b = head;
        while (pre != null) {
            if (pre.val != b.val) {
                return false;
            }
            pre = pre.next;
            b = b.next;
        }
        return true;
    }
}
