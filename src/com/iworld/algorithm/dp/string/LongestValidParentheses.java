package com.iworld.algorithm.dp.string;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.1.3 最长有效括号
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 * 32. Longest Valid Parentheses
 * Hard
 * 11.9K
 * 375
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
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 * https://leetcode.com/problems/longest-valid-parentheses/
 * @date 2023/11/28 19:50
 */
public class LongestValidParentheses {
    
    public static int maxLen(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[] dp = new int[len];
        // dp[0]=1 一个字符的时候一定为0
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (chars[i] == ')') {
                // dp[i - 1]前位置最长有效括号
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre - 1 >= 0 ? dp[pre - 1] : 0);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
    
    
}
