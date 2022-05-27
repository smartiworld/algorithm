package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 19.删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list
 * 可以一遍扫描吗
 * @date 2022/5/27 0:10
 */
public class DeleteTargetNode {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length ++;
            cur = cur.next;
        }
        if (n == length) {
            return head.next;
        }
        int i = 0;
        int index = length - n;
        cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            if (i == index) {
                pre.next = next;
                cur.next = null;
                break;
            }
            i ++;
            pre = cur;
            cur = next;
        }
        return head;
    }
    
    /**
     * 快慢指针
     * 删除倒数第n个节点 需要找到n节点的前一节点
     * 快指针先走n+1步 然后慢指针开始走 快慢指针相差n+1节点
     * 当快指针走到最后的时候 慢指针走到倒数n+1个节点位置
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        int i = 0;
        while (i < n+1) {
            fast = fast.next;
            i++;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        slow.next.next = null;
        return dummy.next;
    }
    
    /**
     * 利用方法栈 从尾部开始计数
     * 数量等于n的时候表示匹配
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        return removeNode(head,n)==n ? head.next : head;
    }
    
    public int removeNode(ListNode node, int n) {
        if(node.next == null)  {
            return 1;
        }
        int m = removeNode(node.next, n);
        // 当前来到m位置节点前一个节点
        if(m == n) {
            node.next = node.next.next;
        }
        return m+1;
    }
    
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        head.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        DeleteTargetNode deleteTargetNode = new DeleteTargetNode();
        ListNode listNode = deleteTargetNode.removeNthFromEnd3(head, 1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}

