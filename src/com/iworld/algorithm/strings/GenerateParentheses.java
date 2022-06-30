package com.iworld.algorithm.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 22.Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *         [()()(), ()(()), (())(), (()()), ((())),((()()]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 * @date 2022/6/29 15:35
 */
public class GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
        char[] stack = new char[n];
        List<String> result = new ArrayList<>();
        process(n << 1, stack, 0, new StringBuilder(), result, 0);
        return result;
    }
    
    private void process(int dn, char[] stack, int index, StringBuilder sb, List<String> result, int i) {
        if (i == dn) {
            result.add(sb.toString());
            return ;
        }
        if (index == 0) {
            sb.append("(");
            stack[index] = '(';
            process(dn, stack, index + 1, sb, result, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(")");
            process(dn, stack, index - 1, sb, result, i + 1);
            sb.deleteCharAt(sb.length() - 1);
            if (index + i < dn) {
                sb.append("(");
                stack[index] = '(';
                process(dn, stack, index + 1, sb, result, i + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, new StringBuilder(), 0, 0, n);
        return list;
    }
    
    public void backtrack(List<String> list, StringBuilder sb, int open, int close, int max){
        
        if(sb.length() == max*2){
            list.add(sb.toString());
            return;
        }
        if(open < max) {
            backtrack(list, sb.append("("), open + 1, close, max);
        }
        if(close < open) {
            backtrack(list, sb.append(")"), open, close + 1, max);
        }
    }
    
    public List<String> generateParenthesis3(int n) {
        char[] stack = new char[n];
        List<String> result = new ArrayList<>();
        process3(n << 1, stack, 0, new StringBuilder(), result, 0);
        return result;
    }
    
    private void process3(int dn, char[] stack, int index, StringBuilder sb, List<String> result, int i) {
        if (i == dn) {
            result.add(sb.toString());
            return ;
        }
        if (index == 0 || index + i < dn) {
            sb.append("(");
            stack[index] = '(';
            process3(dn, stack, index + 1, sb, result, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (index > 0) {
            sb.append(")");
            process3(dn, stack, index - 1, sb, result, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        int n = 3;
        System.out.println(generateParentheses.generateParenthesis(n));
        System.out.println(generateParentheses.generateParenthesis3(n));
    }
}
