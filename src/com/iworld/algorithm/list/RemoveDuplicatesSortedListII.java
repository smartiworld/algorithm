package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 82. Remove Duplicates from Sorted List II
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * @date 2022/7/1 16:31
 */
public class RemoveDuplicatesSortedListII {
    
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = new ListNode();
        // 默认让pre的下一个节点指向当前节点
        // 当指向不同时 表示cur走了多步 有相同值节点
        ans.next = head;
        ListNode pre = ans;
        ListNode cur = head;
        while (cur != null) {
            // 走到相同节点最后一个位置
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // cur没有发生相同值跳跃
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return ans.next;
    }
    
    public ListNode deleteDuplicates2(ListNode head) {
        if (process(head)) {
            return head.next;
        }
        return head;
    }
    
    private boolean process(ListNode node) {
        if (node.next == null) {
            return false;
        }
        boolean process = process(node.next);
        if (process) {
            node.next = null;
            return false;
        }
        if (node.val == node.next.val) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        h1.next = h2;
        ListNode h3 = new ListNode(3);
        h2.next = h3;
        ListNode h4 = new ListNode(3);
        h3.next = h4;
        ListNode h5 = new ListNode(4);
        h4.next = h5;
        ListNode h6 = new ListNode(4);
        h5.next = h6;
        ListNode h7 = new ListNode(5);
        h6.next = h7;
        RemoveDuplicatesSortedListII removeDuplicatesSortedListII = new RemoveDuplicatesSortedListII();
        //System.out.println(removeDuplicatesSortedListII.deleteDuplicates(h1));
        System.out.println(removeDuplicatesSortedListII.deleteDuplicates2(h1));
    }
}
