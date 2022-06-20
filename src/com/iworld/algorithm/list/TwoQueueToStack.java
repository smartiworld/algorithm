package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/8/12 19:50
 */
public class TwoQueueToStack<E> {
    
    private SmartDoubleLinkedDeque<E> queue = new SmartDoubleLinkedDeque<>();
    
    private SmartDoubleLinkedDeque<E> help = new SmartDoubleLinkedDeque<>();
    
    public void push(E e) {
        queue.addQueue(e);
    }
    
    public E pop() {
        E e = null;
        if (help.isEmpty()) {
            while (!queue.isEmpty()) {
                e = queue.popQueue();
                if (!queue.isEmpty()) {
                    help.addQueue(e);
                }
            }
        }
        SmartDoubleLinkedDeque<E> tmp = queue;
        queue = help;
        help = tmp;
        return e;
    }
    
    public static void main(String[] args) {
        TwoQueueToStack<Integer> twoQueueStack = new TwoQueueToStack<>();
        System.out.println(twoQueueStack.pop());
        twoQueueStack.push(1);
        twoQueueStack.push(2);
        twoQueueStack.push(3);
        System.out.println(twoQueueStack.pop());
        twoQueueStack.push(4);
        twoQueueStack.push(5);
        System.out.println(twoQueueStack.pop());
        twoQueueStack.push(6);
        while (true) {
            Integer pop = twoQueueStack.pop();
            System.out.println(pop);
            if (pop == null) {
                break;
            }
        }
    }
}
