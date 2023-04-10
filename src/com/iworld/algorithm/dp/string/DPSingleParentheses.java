package com.iworld.algorithm.dp.string;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单一括号动态规划 32. Longest Valid Parentheses
 * Hard
 * 10.7K
 * 340
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses
 * substring
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'.
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 * @date 2022/7/13 11:30
 */
public class DPSingleParentheses {
    
    /**
     * 返回括号最长有效长度 当前字符来到'('有效长度为0 来到')'时查看前括号有效长度前字符如果匹配则长度为前字符有效长度+2+前有效长度
     * @return
     */
    public int maxEffectiveLength(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                // 当前位置-前位置有效括号长度 并且前面有效长度前一位为'('才可以匹配
                if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                    // 当前位置匹配了前面括号长度为i-1有效长度+2 如果左边匹配括号前还存在有效长度则 加上前面长度
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 1) > 0 ? dp[i - dp[i - 1] - 2] : 0);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        DPSingleParentheses dpSingleParentheses = new DPSingleParentheses();
        // ))()((())))()((())()
        System.out.println(dpSingleParentheses.maxEffectiveLength("))()((())))()((())()"));
    }
}
