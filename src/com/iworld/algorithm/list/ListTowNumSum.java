package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 链表两个数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例1：
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * @date 2022/5/20 15:49
 */
public class ListTowNumSum {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode resultCurrent = result;
        int nextNum = 0;
        while (l1 != null && l2 != null) {
            resultCurrent.next = new ListNode();
            int curVal = l1.val + l2.val + nextNum;
            if (curVal / 10 > 0) {
                nextNum = curVal / 10;
                resultCurrent.next.val = curVal % 10;
            } else {
                resultCurrent.next.val = curVal;
                nextNum = 0;
            }
            l1 = l1.next;
            l2 = l2.next;
            resultCurrent = resultCurrent.next;
        }
        while (l1 != null) {
            resultCurrent.next = new ListNode();
            int curVal = l1.val + nextNum;
            if (curVal / 10 > 0) {
                nextNum = curVal / 10;
                resultCurrent.next.val = curVal % 10;
            } else {
                resultCurrent.next.val = curVal;
                nextNum = 0;
            }
            l1 = l1.next;
            resultCurrent = resultCurrent.next;
        }
        while (l2 != null) {
            resultCurrent.next = new ListNode();
            int curVal = l2.val + nextNum;
            if (curVal / 10 > 0) {
                nextNum = curVal / 10;
                resultCurrent.next.val = curVal % 10;
            } else {
                resultCurrent.next.val = curVal;
                nextNum = 0;
            }
            l2 = l2.next;
            resultCurrent = resultCurrent.next;
        }
        if (nextNum > 0) {
            resultCurrent.next = new ListNode(nextNum);
        }
        return result.next;
    }
    
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode resultCurrent = result;
        // 下一个进位的数字
        int nextNum = 0;
        while (l1 != null || l2 != null) {
            resultCurrent.next = new ListNode();
            int curVal = nextNum;
            if (l1 != null) {
                curVal += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                curVal += l2.val;
                l2 = l2.next;
            }
            // 如果当前两个数相加大于10表示需要进位
            if (curVal / 10 > 0) {
                nextNum = curVal / 10;
                resultCurrent.next.val = curVal % 10;
            } else {
                resultCurrent.next.val = curVal;
                nextNum = 0;
            }
            resultCurrent = resultCurrent.next;
        }
        if (nextNum > 0) {
            resultCurrent.next = new ListNode(nextNum);
        }
        return result.next;
    }
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        ListTowNumSum listTowNumSum = new ListTowNumSum();
        ListNode listNode = listTowNumSum.addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
        System.out.println("----");
        ListNode listNode2 = listTowNumSum.addTwoNumbers2(l1, l2);
        while (listNode2 != null) {
            System.out.print(listNode2.val + ",");
            listNode2 = listNode2.next;
        }
    }
}
