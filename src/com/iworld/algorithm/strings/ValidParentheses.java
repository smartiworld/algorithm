package com.iworld.algorithm.strings;

import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 20. Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 * https://leetcode.com/problems/valid-parentheses/
 * @date 2022/6/29 10:26
 */
public class ValidParentheses {
    
    /**
     * 不需左右相同括号挨着
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        int[] map = new int[86];
        map[0] = 1;
        map[51] = 53;
        map[83] = 85;
        // 缓存左边界出现次数 下标为有边界ascii
        int[] cache = new int[86];
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            // 读到左边界
            if (map[s.charAt(i) - '('] > 0) {
                cache[map[s.charAt(i) - '(']] ++;
                count ++;
            } else {
                cache[s.charAt(i) - '('] --;
                if (cache[s.charAt(i) - '('] < 0) {
                    return false;
                }
                count --;
            }
            
        }
        return count == 0;
    }
    
    /**
     * 左右括号挨着
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int[] map = new int[86];
        map[0] = 1;
        map[51] = 53;
        map[83] = 85;
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 40] > 0) {
                stack.add(s.charAt(i));
            } else {
                if (stack.empty() || map[stack.pop() - 40] != (s.charAt(i) - 40)) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid && stack.isEmpty();
    }
    
    /**
     * 严格每个种类对应
     * 比较优
     * 使用栈结构 遇到右边界入栈  value为右边界
     * 遇到右边界 检查栈中是否存在元素 如果不存在则失败 如果存在检查最后入栈是否为对应左边界 如果是继续匹配 否则失败
     * @param s
     * @return
     */
    public boolean isValid3(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        int n = s.length();
        // value存放左边界对应右边界
        int[] stack = new int[n];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack[index++] = ')';
            } else if (s.charAt(i) == '[') {
                stack[index++] = ']';
            } else if (s.charAt(i) == '{') {
                stack[index++] = '}';
            } else {
                //当前字符为右边界 如果之前没有左边界在栈中 失败
                // 如果最后入栈字符和当前不对应 则失败
                if (index == 0 || stack[--index] != s.charAt(i)) {
                    return false;
                }
            }
            
        }
        return index == 0;
    }
    
    /**
     * 不区分顺序
     * @param s
     * @return
     */
    public boolean isValidNotOrder(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        int n = s.length();
        int smallCount = 0;
        int midCount = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                smallCount ++;
            }
            if (s.charAt(i) == '[') {
                midCount ++;
            }
            if (s.charAt(i) == '{') {
                maxCount ++;
            }
            if (s.charAt(i) == ')') {
                smallCount --;
            }
            if (s.charAt(i) == ']') {
                midCount --;
            }
            if (s.charAt(i) == '}') {
                maxCount --;
            }
        }
        return smallCount == 0 && midCount == 0 & maxCount == 0;
    }
    
    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        String s = "([])";
        System.out.println(validParentheses.isValid2(s));
    }
}
