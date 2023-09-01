package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/10/21 14:13
 */
public class DoubleLinkedListOperate {
    //[1,2,3,4,5,6,null,null,null,7,8,null,null,11,12]
    //[1,2,3,7,8,11,12,4,5,6]
    public static void main(String[] args) {
        DoubleListNode d1 = new DoubleListNode(1);
        DoubleListNode d2 = new DoubleListNode(2);
        DoubleListNode d3 = new DoubleListNode(3);
        DoubleListNode d4 = new DoubleListNode(4);
        DoubleListNode d5 = new DoubleListNode(5);
        DoubleListNode d6 = new DoubleListNode(6);
        DoubleListNode d7 = new DoubleListNode(7);
        DoubleListNode d8 = new DoubleListNode(8);
        //DoubleListNode d9 = new DoubleListNode(9);
        //DoubleListNode d10 = new DoubleListNode(10);
        DoubleListNode d11 = new DoubleListNode(11);
        DoubleListNode d12 = new DoubleListNode(12);
        d1.next = d2;
        d2.prev = d1;
        d2.next = d3;
        d3.prev = d2;
        d3.next = d4;
        d4.prev = d3;
        d4.next = d5;
        d5.prev = d4;
        d5.next = d6;
        d6.prev = d5;
        d3.child = d7;
        d7.next = d8;
        d8.prev = d7;
//        d8.next = d9;
//        d9.prev = d8;
//        d9.next = d10;
//        d10.prev = d9;
        d8.child = d11;
        d11.next = d12;
        d12.prev = d11;
//        DoubleListNode d1 = new DoubleListNode(1);
//        DoubleListNode d2 = new DoubleListNode(2);
//        DoubleListNode d3 = new DoubleListNode(3);
//        d1.child = d2;
//        d2.child = d3;
        DoubleLinkedListOperate doubleLinkedListOperate = new DoubleLinkedListOperate();
        DoubleListNode flatten = doubleLinkedListOperate.flatten(d1);
        while (flatten != null) {
            System.out.print(flatten.val + ",");
            if (flatten.next == null) {
                break;
            }
            flatten = flatten.next;
        }
        System.out.println();
        System.out.println("========================");
        while (flatten != null) {
            System.out.print(flatten.val + ",");
            flatten = flatten.prev;
        }
    }
    /**
     * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
     * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
     *
     * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
     * 例1：
     * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * 1---2---3---4---5---6--NULL
     *          |
     *          7---8---9---10--NULL
     *              |
     *              11--12--NULL
     *
     * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
     * 例2：
     * 输入：head = [1,2,null,3]
     * 输出：[1,3,2]
     * 解释：
     *
     * 输入的多级列表如下图所示：
     *
     *   1---2---NULL
     *   |
     *   3---NULL
     *
     * https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
     * @param head
     * @return
     */
    public DoubleListNode flatten(DoubleListNode head) {
        if (head == null) {
            return null;
        }
        findChild2(head);
        return head;
    }
    
    public DoubleListNode findChild(DoubleListNode head) {
        DoubleListNode cur = head;
        while (cur.next != null || cur.child != null) {
            DoubleListNode child = cur.child;
            DoubleListNode next = cur.next;
            if (child != null) {
                // 返回子链表最后节点
                DoubleListNode flatten = findChild(child);
                child.prev = cur;
                cur.next = child;
                cur.child = null;
                if (next != null) {
                    // 当前节点的下一个节点不为空需要放在当前链表子链表的最后
                    next.prev = flatten;
                    flatten.next = next;
                } else {
                    next = child;
                }
            }
            cur = next;
        }
        return cur;
    }
    
    public DoubleListNode findChild2(DoubleListNode head) {
        DoubleListNode cur = head;
        DoubleListNode pre = null;
        while (cur != null) {
            pre = cur;
            DoubleListNode child = cur.child;
            DoubleListNode next = cur.next;
            if (child != null) {
                // 返回子链表最后节点
                DoubleListNode flatten = findChild2(child);
                child.prev = cur;
                cur.next = child;
                cur.child = null;
                if (next != null) {
                    // 当前节点的下一个节点不为空需要放在当前链表子链表的最后
                    next.prev = flatten;
                    flatten.next = next;
                }
                // 下一个链表需放当前子链表后面 记录当前节点位置为子链表最后一位置
                pre = flatten;
            }
            cur = next;
        }
        return pre;
    }
    
    private DoubleListNode helper(DoubleListNode head) {
        DoubleListNode node = head;
        DoubleListNode last = null;
        while (node != null) {
            last = node;
            DoubleListNode next = node.next;
            if (node.child != null) {
                DoubleListNode start = node.child;
                DoubleListNode end = helper(node.child);
                end.next = next;
                if (next != null) {
                    next.prev = end;
                }
                node.next = start;
                start.prev = node;
                node.child = null;
                last = end;
            }
            node = next;
        }
        return last;
    }
    
    private static class DoubleListNode {
        int val;
        DoubleListNode prev;
        DoubleListNode next;
        DoubleListNode child;
        DoubleListNode(int x) {
            val = x;
        }
        DoubleListNode(int x, DoubleListNode next) {
            val = x;
            this.next = next;
        }
    
        @Override
        public String toString() {
            return "DoubleListNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
