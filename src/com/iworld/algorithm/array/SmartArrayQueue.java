package com.iworld.algorithm.array;

import com.iworld.algorithm.list.SmartLinkedQueue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/8/10 17:50
 */
public class SmartArrayQueue<E> {

    Object[] items;
    private int capacity;
    private int putIndex;
    private int takeIndex;
    private int count;
    
    public SmartArrayQueue(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
    }
    
    public void enqueue(E e) {
        if (capacity == count) {
            throw new RuntimeException("队列已满");
        }
        items[putIndex] = e;
        count ++;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
    }
    
    public E dequeue() {
        if (count == 0) {
            throw new RuntimeException("队列已空");
        }
        E e = (E) items[takeIndex];
        count --;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        return e;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public static void main(String[] args) {
        SmartArrayQueue<Integer> queue = new SmartArrayQueue<>(10);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        while (!queue.isEmpty()) {
            Integer dequeue = queue.dequeue();
            System.out.println(dequeue);
        }
    }
}
