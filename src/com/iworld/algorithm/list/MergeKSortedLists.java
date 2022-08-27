package com.iworld.algorithm.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 23. Merge k Sorted Lists   Hard
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10^4.
 * @date 2022/8/27 14:11
 */
public class MergeKSortedLists {
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length ==0) {
            return null;
        }
        int n = lists.length;
        if (n == 1) {
            return lists[0];
        }
        ListNode ans = new ListNode();
        ListNode cur = ans;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {
                heap.offer(lists[i]);
            }
        }
        while (!heap.isEmpty()) {
            ListNode poll = heap.poll();
            cur.next = poll;
            if (poll.next != null) {
                heap.offer(poll.next);
            }
            cur = cur.next;
        }
        return ans.next;
    }
    
    static class ListNodeComparator implements Comparator<ListNode> {
        
        @Override
        public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val;
        }
    }
    
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length ==0) {
            return null;
        }
        int n = lists.length;
        while (n > 1) {
            int index = 0;
            for (int i = 0; i < n; i = i + 2) {
                if (i == n - 1) {
                    lists[index++] = lists[i];
                } else {
                    lists[index++] = mergeTwoList(lists[i], lists[i + 1]);
                }
            }
            n = index;
        }
        return lists[0];
    }
    
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ListNode cur = ans;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return ans.next;
    }
    
}
