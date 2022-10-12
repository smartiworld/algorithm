package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 328. Odd Even Linked List
 * Medium
 * 6274
 * 397
 * Given the head of a singly linked list,
 * group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * Example 2:
 *
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 * Constraints:
 *
 * The number of nodes in the linked list is in the range [0, 104].
 * -10^6 <= Node.val <= 10^6
 * https://leetcode.com/problems/odd-even-linked-list/
 * @date 2022/10/12 21:16
 */
public class OddEvenLinkedList {
    
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddH = new ListNode();
        ListNode even = head;
        ListNode odd = oddH;
        ListNode cur = head;
        boolean isOdd = true;
        while (cur != null) {
            ListNode next = cur.next;
            if (isOdd) {
                odd.next = next;
                odd = odd.next;
            } else if (next != null) {
                even.next = next;
                even = even.next;
            }
            isOdd = !isOdd;
            cur = next;
        }
        even.next = oddH.next;
        return head;
    }
}
