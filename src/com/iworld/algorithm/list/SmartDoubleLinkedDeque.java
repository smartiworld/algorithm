package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/8/13 9:53
 */
public class SmartDoubleLinkedDeque<E> {
    
    private Node<E> head;
    private Node<E> tail;
    private int count;
    private int capacity;
    
    public SmartDoubleLinkedDeque() {
        this(Integer.MAX_VALUE);
    }
    
    public SmartDoubleLinkedDeque(int capacity) {
        this.capacity = capacity;
    }
    
    private boolean linkFirst(E e) {
        if (count >= capacity) {
            return false;
        }
        Node<E> node = new Node<>(e);
        Node<E> f = head;
        node.next = f;
        head = node;
        if (tail == null) {
            tail = node;
        } else {
            f.pre = node;
        }
        ++ count;
        return true;
    }
    
    private boolean linkLast(E e) {
        if (count >= capacity) {
            return false;
        }
        Node<E> node = new Node(e);
        Node<E> last = tail;
        node.pre = last;
        tail = node;
        if (head == null) {
            head = node;
        } else {
            last.next = node;
        }
        ++ count;
        return true;
    }
    
    private E unLinkFirst() {
        if (head == null) {
            return null;
        }
        Node<E> f = head;
        E e = f.value;
        Node<E> n = f.next;
        f.next = f;
        f.value = null;
        head = n;
        if (n == null) {
            tail = null;
        } else {
            n.pre = null;
        }
        -- count;
        return e;
    }
    
    private E unLinkLast() {
        if (tail == null) {
            return null;
        }
        Node<E> last = tail;
        E e = last.value;
        Node<E> pre = last.pre;
        last.pre = last;
        last.value = null;
        tail = pre;
        if (pre == null) {
            head = null;
        } else {
            pre.next = null;
        }
        -- count;
        return e;
    }
    
    public int size() {
        return count;
    }
    
    public void pushStack(E e) {
        addLast(e);
    }
    public E popStack() {
        return popLast();
    }
    
    public void addQueue(E e) {
        addLast(e);
    }
    
    public E popQueue() {
        return popFirst();
    }
    static class Node<E>{
        E value;
        Node<E> pre;
        Node<E> next;
        
        Node(E e) {
            value = e;
        }
        
        Node(E e, Node pre, Node next) {
            this.pre = pre;
            this.next = next;
        }
    }
    public boolean addFirst(E e) {
        return linkFirst(e);
    }
    public boolean addLast(E e) {
        return linkLast(e);
    }
    
    public E popFirst() {
        return unLinkFirst();
    }
    public E popLast() {
        return unLinkLast();
    }
    public boolean isEmpty() {
        return count == 0;
    }
    public static void main(String[] args) {
        SmartDoubleLinkedDeque<Integer> deque = new SmartDoubleLinkedDeque<>();
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.addFirst(6);
//        deque.addFirst(7);
//        deque.addFirst(8);
//        deque.addFirst(9);
//        deque.addFirst(10);
//        deque.addFirst(11);
//        deque.addFirst(12);
//        deque.addFirst(13);
//        while (!deque.isEmpty()) {
//            //System.out.println(deque.popFirst());
//            System.out.println(deque.popLast());
//        }
//        deque.addLast(1);
//        deque.addLast(2);
//        deque.addLast(3);
//        deque.addLast(4);
//        deque.addLast(5);
//        deque.addLast(6);
//        deque.addLast(7);
//        deque.addLast(8);
//        deque.addLast(9);
//        deque.addLast(10);
//        deque.addLast(11);
//        deque.addLast(12);
//        deque.addLast(13);
//        deque.addLast(14);
//        deque.addLast(15);
//        while (!deque.isEmpty()) {
//            System.out.println(deque.popFirst());
//            //System.out.println(deque.popLast());
//        }
        deque.addQueue(1);
        deque.addQueue(2);
        deque.addQueue(3);
        deque.addQueue(4);
        deque.addQueue(5);
        deque.addQueue(6);
        deque.addQueue(6);
        deque.addQueue(7);
        deque.addQueue(8);
        deque.addQueue(9);
        deque.addQueue(10);
        deque.addQueue(11);
        deque.addQueue(12);
        deque.addQueue(13);
        while (!deque.isEmpty()) {
            System.out.println(deque.popQueue());
        }
    }
}
