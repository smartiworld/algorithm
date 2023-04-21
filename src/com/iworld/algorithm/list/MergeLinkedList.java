package com.iworld.algorithm.list;

/**
 * 多个有序链表合并成一个链表
 *
 * @author iworld
 * @Date 2020-10-17 20:36
 */
public class MergeLinkedList {
    
    //[[1,4,5],[1,3,4],[2,6]]
    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[3];
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(5);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        nodes[0] = listNode11;
        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        nodes[1] = listNode21;
        ListNode listNode31 = new ListNode(2);
        ListNode listNode32 = new ListNode(6);
        listNode31.next = listNode32;
        nodes[2] = listNode31;
        MergeLinkedList mergeLinkedList = new MergeLinkedList();
        ListNode merge = mergeLinkedList.mergeForLoop(nodes);
        while (merge != null) {
            System.out.println(merge.val);
            merge = merge.next;
        }
    }
    
    public ListNode merge(ListNode[] nodes){
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        return merge(nodes, 0, nodes.length - 1);
    }
    
    /**
     * 非递归合并
     * @param nodes
     * @return
     */
    public ListNode mergeForLoop(ListNode[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        int len = nodes.length;
        int k = 0;
        while (k != 1) {
            k = 0;
            for (int i = 0; i < len; i += 2) {
                if (i == len -1) {
                    nodes[k++] = nodes[len - 1];
                } else {
                    nodes[k++] = mergeTwo(nodes[i], nodes[i + 1]);
                }
            }
            len = k;
        }
        return nodes[0];
    }
    /**
     * 每两个合并 递归
     * @param nodes
     * @param s 排序数组开始位置
     * @param e 排序数组结束位置
     * @return
     */
    public ListNode merge(ListNode[] nodes, int s, int e){
        if(s == e){
            return nodes[s];
        }
        int mid = s + ((e - s) >> 1);
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
    
    /**
     * 合并两个链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = null;
        ListNode cur = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if(cur == null) {
                    res = l1;
                } else {
                    cur.next = l1;
                }
                cur = l1;
                l1 = l1.next;
            } else {
                if(cur == null) {
                    res = l2;
                } else {
                    cur.next = l2;
                }
                cur = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return res;
    }
    
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode cur = new ListNode();
        ListNode res = cur;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return res.next;
    }
    
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }
    
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        int len = lists.length;
        int k = 10;
        while(k != 0){
            k = 0;
            for(int i = 0; i < len; i = i + 2){
                if( i < len-1){
                    lists[k] = mergeTwoLists(lists[i],lists[i + 1]);
                }else{
                    lists[k] = lists[len - 1];
                }
                k++;
            }
            len = k;
            if(len == 1){
                break;
            }
        }
        return lists[0];
        
    }

    static class ListNode{

        public int val;

        public ListNode next;
    
        public ListNode() {
        }
        public ListNode(int value) {
            val = value;
        }
    }
}
