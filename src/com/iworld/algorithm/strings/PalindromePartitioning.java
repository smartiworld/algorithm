package com.iworld.algorithm.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 131. Palindrome Partitioning  Medium
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 * https://leetcode.com/problems/palindrome-partitioning/
 * @date 2022/8/25 12:49
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        boolean[][] dp = createDp(s.toCharArray());
        List<List<String>> ans = new ArrayList<>();
        process(s, 0, ans, new ArrayList<>(), dp);
        return ans;
    }
    
    private void process(String s, int index, List<List<String>> ans, List<String> result, boolean[][] dp) {
        if (index == s.length()) {
            ans.add(new ArrayList<>(result));
            return ;
        }
        // 左边界index 右边界i
        // index
        // index+1
        for (int i = index; i < s.length(); i++) {
            if (dp[index][i]) {
                result.add(s.substring(index, i + 1));
                process(s, i + 1, ans, result, dp);
                result.remove(result.size() - 1);
            }
        }
    }
    
    /**
     * 数组预处理 先创建好整个字符各位置是否为回文串
     * l字符左边界 l字符右边界
     * 可以看作矩形l到r是否为回文 l<r
     * 矩形下半区不存在因为l不可以大于r
     * @param s
     * @return
     */
    private boolean[][] createDp(char[] s) {
        int n = s.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n - 1; i ++) {
            // 对角线l=r的时候 一定是回文
            dp[i][i] = true;
            // 两个字符的时候 判断i和i+1位置字符是否相等 相等则为回文
            dp[i][i + 1] = s[i] == s[i + 1];
        }
        dp[n - 1][n - 1] = true;
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = s[i] == s [j] && dp[i + 1][j - 1];
            }
        }
        return dp;
    }
    
    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        String s = "aab";
        System.out.println(palindromePartitioning.partition(s));
    }
}
