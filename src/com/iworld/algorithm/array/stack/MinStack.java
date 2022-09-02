package com.iworld.algorithm.array.stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 155. Min Stack
 * Medium
 * 9398
 * 668
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *  Constraints:
 *
 * -2^31 <= val <= 2^31 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 * https://leetcode.com/problems/min-stack/
 * @date 2022/9/2 16:23
 */
public class MinStack {
    
    private int size;
    
    private int DEFAULT_SIZE = 10;
    
    private int[] tables;
    private int[] mins;
    
    public MinStack() {
        tables = new int[DEFAULT_SIZE];
        mins = new int[DEFAULT_SIZE];
    }
    
    public void push(int val) {
        if (size >= tables.length) {
            resize();
        }
        tables[size] = val;
        int min = val;
        if (size > 0) {
            min = Math.min(val, mins[size - 1]);
        }
        mins[size++] = min;
    }
    
    private void resize() {
        int n = tables.length;
        int newSize = n << 1;
        int[] newTables = new int[newSize];
        int[] newMins = new int[newSize];
        for (int i = 0; i < n; i++) {
            newTables[i] = tables[i];
            newMins[i] = mins[i];
        }
        tables = newTables;
        mins = newMins;
    }
    
    public void pop() {
        size--;
    }
    
    public int top() {
        if (size == 0) {
            return -1;
        }
        int peekIndex = size - 1;
        return tables[peekIndex];
    }
    
    public int getMin() {
        if (size == 0) {
            return -1;
        }
        int minIndex = size - 1;
        return mins[minIndex];
    }
    
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        for (int i = 0; i < 100; i++) {
            minStack.push(i);
        }
    }
    
}
