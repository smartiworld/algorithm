package com.iworld.algorithm;

import java.util.List;

/**
 * 多个有序链表合并成一个链表
 *
 * @Autor iworld
 * @Date 2020-10-17 20:36
 */
public class MergeLinkedList {

    public void merge(ListNode[] nodes){
        for(ListNode node : nodes){

        }
    }

    /**
     * 每两个合并 递归
     * @param nodes
     * @param s
     * @param e
     * @return
     */
    public ListNode merge(ListNode[] nodes, int s, int e){
        if(s == e){
            return nodes[0];
        }
        int mid = (e + s)/2;
        ListNode l1 = merge(nodes, s, mid);
        ListNode l2 = merge(nodes, mid + 1, e);
        return mergeTwo(l1, l2);
    }

    /**
     * 将两个链表合并成一个链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwo(ListNode l1, ListNode l2){
        if (l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.val < l2.val){
            l1.next = mergeTwo(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwo(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int k = lists.length;
        while (k > 1) {
            int idx = 0;
            for (int i = 0; i < k; i += 2) {
                if (i == k - 1) {
                    lists[idx++] = lists[i];
                } else {
                    lists[idx++] = mergeTwo(lists[i], lists[i + 1]);
                }
            }
            k = idx;
        }
        return lists[0];
    }

    class ListNode{

        public int val;

        public ListNode next;
    }
}
