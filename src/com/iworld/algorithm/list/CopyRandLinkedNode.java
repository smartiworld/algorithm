package com.iworld.algorithm.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 拷贝链表 每个链表节点有一个随机指向一个节点的引用，拷贝当前链表
 * @date 2021/8/12 15:24
 */
public class CopyRandLinkedNode<E> {
    
    /**
     *
     * @param head
     * @return
     */
    public Node copyWithMap(Node head) {
        // copy 数组映射 key原链表节点 value
        Map<Node, Node> copyNodeMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.value);
            copyNodeMap.put(cur, copyNode);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            copyNodeMap.get(cur).next = copyNodeMap.get(cur.next);
            copyNodeMap.get(cur).random = copyNodeMap.get(cur.random);
            cur = cur.next;
        }
        return copyNodeMap.get(head);
    }
    
    /**
     *
     * @param head
     * @return
     */
    public Node copyNoSpace(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        //复制原链表节点 加入到原链表中1-2-3  1-c1-2-c2-3-c3
        while (cur != null) {
            Node next = cur.next;
            //复制节点
            Node node = new Node(cur.value);
            //将当前复制节点加入到当前链表中 放原节点后
            cur.next = node;
            node.next = next;
            cur = next;
        }
        cur = head;
        // 设置复制节点random节点 此时不能断开链表  如果断开 找rand节点得复制节点可能找不到
        while (cur != null) {
            Node copyNode = cur.next;
            copyNode.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node copyHead = cur.next;
        Node copyCur = null;
        // 拆分链表 将原链表和复制链表拆开
        while (cur != null) {
            
            copyCur = cur.next;
            //原链表下一个节点
            Node next = cur.next.next;
            copyCur.next = next != null ? next.next : null;
            cur.next = next;
            cur = next;
        }
        return copyHead;
    }
    
    static class Node<E>{
        E value;
        Node<E> next;
        Node<E> random;
        Node(E e) {
            value = e;
        }
    }
}
