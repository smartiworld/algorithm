package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 约瑟夫环
 * @date 2023/3/3 13:33
 */
public class JosephusProblem {
    
    public static void main(String[] args) {
    
    }
    
    public static ListNode josephusKill(ListNode head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        ListNode cur = head.next;
        int size = 1;
        // tmp -> list size
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        // tmp -> service node position
        int live = getLive(size, m);
        while (--live != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }
    
    // 现在一共有i个节点，数到m就杀死节点，最终会活下来的节点，请返回它在有i个节点时候的编号
    // 旧
    // getLive(N, m)
    public static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        // getLive(i - 1, m)   长度为i-1时候，活下来的编号
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }
}
