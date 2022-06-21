package com.iworld.algorithm.list;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单链表队列
 * @date 2021/8/10 15:40
 */
public class SmartLinkedQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    
    public SmartLinkedQueue() {
    
    }
    
    public void enqueue(E e) {
        Node<E> node = new Node(e);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }
    
    public E dequeue() {
       if (head == null) {
           return null;
       }
       E e = head.value;
       Node<E> h = head;
       head = head.next;
       h.value = null;
       h.next = null;
       return e;
    }
    
    public E peek() {
        if (head == null) {
            return null;
        }
        E e = head.value;
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
    
    public static void main(String[] args) {
        SmartLinkedQueue<Integer> queue = new SmartLinkedQueue<>();
        System.out.println(queue.dequeue());
        queue.enqueue(1);
        System.out.println(queue.dequeue());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
