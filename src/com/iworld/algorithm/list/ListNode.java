package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 链表节点
 * @date 2022/5/20 16:08
 */
public class ListNode {
    
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
