package com.iworld.algorithm.list;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单链表栈
 * @date 2021/8/10 15:40
 */
public class SmartLinkedStack<E> {

    private Node<E> head;
    private Node<E> tail;
    
    public SmartLinkedStack() {
    
    }
    
    public void push(E e) {
        Node<E> node = new Node<>(e);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }
    
    public E pop() {
        E e = tail.value;
        Node<E> cur = head;
        Node<E> pre = null;
        while (cur != null) {
            if (cur.next == null) {
                pre.next = null;
                tail = pre;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return e;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    static class Node<E>{
        E value;
        Node<E> next;
        Node(E e) {
            value = e;
        }
    }
    
}
