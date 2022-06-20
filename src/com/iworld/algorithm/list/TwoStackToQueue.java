package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/8/16 17:52
 */
public class TwoStackToQueue<E> {
    
    private SmartDoubleLinkedDeque<E> stack = new SmartDoubleLinkedDeque<>();
    
    private SmartDoubleLinkedDeque<E> help = new SmartDoubleLinkedDeque<>();
    
    public void put(E e) {
        stack.pushStack(e);
    }
    
    public E poll() {
        if (!help.isEmpty()) {
            return help.popStack();
        }
        while (!stack.isEmpty()) {
            help.pushStack(stack.popStack());
        }
        return help.popStack();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty() && help.isEmpty();
    }
    
    public static void main(String[] args) {
        TwoStackToQueue<Integer> twoStackToQueue = new TwoStackToQueue<>();
        twoStackToQueue.put(1);
        twoStackToQueue.put(2);
        twoStackToQueue.put(3);
        System.out.println(twoStackToQueue.poll());
        twoStackToQueue.put(4);
        twoStackToQueue.put(5);
        System.out.println(twoStackToQueue.poll());
        twoStackToQueue.put(6);
        twoStackToQueue.put(7);
        twoStackToQueue.poll();
        while (!twoStackToQueue.isEmpty()) {
            System.out.println(twoStackToQueue.poll());
        }
    }
}
