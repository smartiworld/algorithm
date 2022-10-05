package com.iworld.algorithm.calc;

import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 772.Basic Calculator III
 * Hard
 * 494
 * 207
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators ,
 * open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Examples 1:
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Examples 2:
 * Input: s = " 6-4 / 2 "
 * Output:  4
 *
 * Examples 3:
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 * Examples 3:
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 * Examples 4:
 * Input: s = "(2+6* 3+5- (3*14/7+2)*5)+3"
 * Output: -12
 * @date 2022/10/5 15:17
 */
public class BasicCalculatorIII {
    
    public int calculate(String s) {
        return process(s)[0];
    }
    
    private int[] process(String s) {
        LinkedList<String> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int preNum = 0;
        int i = 0;
        for (;i < chars.length; i++) {
            char cur = chars[i];
            if (cur != ' ') {
                if (cur == ')') {
                    break;
                }
                if (cur == '(') {
                    int[] process = process(s.substring(i + 1));
                    preNum = process[0];
                    i = i + process[1] + 1;
                } else {
                    if (cur >= '0' && cur <= '9') {
                        preNum = preNum * 10 + (cur - '0');
                    } else {
                        dealStack(stack, preNum, String.valueOf(cur));
                        preNum = 0;
                    }
                }
            }
        }
        dealStack(stack, preNum, null);
        int calc = calcFromStack(stack);
        return new int[]{calc, i};
    }
    
    private void dealStack(LinkedList<String> stack, int num, String cur) {
        if (stack.isEmpty() || "+".equals(stack.peekLast()) || "-".equals(stack.peekLast())) {
            stack.addLast(String.valueOf(num));
        } else {
            String pop = stack.pollLast();
            if ("*".equals(pop)) {
                stack.addLast(String.valueOf(Integer.parseInt(stack.pollLast()) * num));
            } else if ("/".equals(pop)) {
                stack.addLast(String.valueOf(Integer.parseInt(stack.pollLast()) / num));
            }
        }
        if (cur != null) {
            stack.addLast(cur);
        }
    }
    
    private int calcFromStack(LinkedList<String> stack) {
        int ans = Integer.valueOf(stack.pollFirst());
        while (!stack.isEmpty()) {
            String symbol = stack.pollFirst();
            if ("+".equals(symbol)) {
                ans += Integer.valueOf(stack.pollFirst());
            } else {
                ans -= Integer.valueOf(stack.pollFirst());
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        //String s = "(2+6*3+5-(3*14/7+2)*5)+3";
        String s = "2*(5+5*2)/3+(6/2+8)";
        BasicCalculatorIII basicCalculatorIII = new BasicCalculatorIII();
        System.out.println(basicCalculatorIII.calculate(s));
    }
}
