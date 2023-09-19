package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.5.1 给定一个字符串str，求最长回文子序列长度
 * 516. Longest Palindromic Subsequence
 * Medium
 * 9K
 * 314
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * @date 2023/9/18 19:15
 */
public class LongestPalindromeSubSequence {
    
    /**
     * dp[i][j]表示字符串从左侧i位置到右侧j位置 回文子序列的长度 当前字符串是回文子序列存在四种可能
     * 1.同时包括i位置和j位置 此时需要i位置字符和j位置字符相等 dp[i][j] = dp[i + 1][j - 1] + 2
     * 2.包括i位置但是不包括j位置此时dp[i][j] = dp[i][j - 1]，或者不包括i位置但是包括j位置此时dp[i][j] = dp[i + 1][j]两者取大为当前字符串公共子序列
     * @param s
     * @return
     */
    public static int longestPalindromeSubSequenceDp(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
            if (i < len - 1) {
                dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
            }
        }
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (chars[i] == chars[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);;
                }
            }
        }
        return dp[0][len - 1];
    }
    
    public static void main(String[] args) {
        String s = "abab";
        System.out.println(longestPalindromeSubSequenceDp(s));
    }
}
