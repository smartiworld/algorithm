package com.iworld.algorithm.recursion;

import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description  反转栈
 * 1-2-3  ====   3-2-1
 * @date 2022/4/19 10:14
 */
public class ReverseStack {
    
    /**
     * 反转栈 不申请额外空间
     * 1.首先获取栈底元素 压入方法栈
     * 2.栈中其他元素不变
     * 3.重复上述步骤  直到栈空 此时栈中元素都按照反方向压入方法栈
     * 4.弹方法栈  重z新压入栈
     * @param stack
     */
    public static void reverseStackNoSpace(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return ;
        }
        // 栈的最后位置 此时栈只是少了最后的尾部栈中其他值位置不变
        // 此时 栈底元素先弹出 将最后元素压入方法栈
        Integer process = process(stack);
        // 重复以上步骤  每次将最后位置压到方法栈  直到栈空后 结束  结束时即为栈顶值
        // 此时的结构为 栈底元素先弹出  此方法将栈底值 放到此方法栈顶
        reverseStackNoSpace(stack);
        //压入栈
        stack.push(process);
    }
    
    /**
     * 返回栈底元素  栈中其他元素位置不变
     * @param stack
     * @return
     */
    private static Integer process(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            Integer process = process(stack);
            stack.push(pop);
            return process;
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println(null == null);
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        reverseStackNoSpace(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
