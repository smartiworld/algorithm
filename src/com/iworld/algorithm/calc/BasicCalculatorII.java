package com.iworld.algorithm.calc;

import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 227. Basic Calculator II
 * Medium
 * 4849
 * 624
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2^31, 2^31 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 10^5
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 * https://leetcode.com/problems/basic-calculator-ii/
 * @date 2022/9/15 19:28
 */
public class BasicCalculatorII {
    
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        LinkedList<String> stack = new LinkedList<>();
        int putNum = 0;
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (cur != ' ') {
                if (cur >= '0' && cur <= '9') {
                    putNum = putNum * 10 + (chars[i] - '0');
                } else {
                    dealStack(stack, putNum, String.valueOf(cur));
                    putNum = 0;
                }
            }
        }
        dealStack(stack, putNum, null);
        return calc(stack);
    }
    
    private void dealStack(LinkedList<String> stack, int putNum, String cur) {
        if (stack.isEmpty() || "+".equals(stack.peek()) || "-".equals(stack.peek())) {
            stack.push(String.valueOf(putNum));
        } else {
            String pop = stack.pop();
            if ("*".equals(pop)) {
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) * putNum));
            } else if ("/".equals(pop)) {
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) / putNum));
            }
        }
        stack.push(cur);
    }
    
    private int calc(LinkedList<String> list) {
        Integer ans = Integer.parseInt(list.pollLast());
        while (list.size() > 1) {
            String symbol = list.pollLast();
            Integer next = Integer.parseInt(list.pollLast());
            if ("+".equals(symbol)) {
                ans += next;
            } else {
                ans -= next;
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
//        String s = "1-1+1";
//        BasicCalculatorII basicCalculatorII = new BasicCalculatorII();
//        System.out.println(basicCalculatorII.calculate(s));
        Integer a = Integer.valueOf(20000);
        Integer b = Integer.valueOf(20000);
        System.out.println(a.equals(b));
    }
}
