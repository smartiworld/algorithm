package com.iworld.algorithm.array.stack;

import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 150. Evaluate Reverse Polish Notation
 * Medium
 * 3844
 * 686
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid.
 * That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * Constraints:
 *
 * 1 <= tokens.length <= 10^4
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * @date 2022/9/1 16:12
 */
public class EvaluateReversePolishNotation {
    
    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        if (n == 1) {
            return Integer.parseInt(tokens[0]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if ("*".equals(tokens[i])) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(tokens[i])) {
                Integer divisor = stack.pop();
                Integer dividend = stack.pop();
                stack.push(dividend / divisor);
            } else if ("+".equals(tokens[i])) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(tokens[i])) {
                Integer sub1 = stack.pop();
                Integer sub2 = stack.pop();
                stack.push(sub2 - sub1);
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }
    
    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();
        System.out.println(evaluateReversePolishNotation.evalRPN(tokens));
    }
}
