package com.iworld.algorithm.dp.string;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.3.4 两个字符的最长公共子序列
 * 1143. Longest Common Subsequence
 * Medium
 * 12.5K
 * 161
 * Companies
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with
 * some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 * https://leetcode.com/problems/longest-common-subsequence/
 * @date 2023/12/15 19:02
 */
public class LongestCommonSubsequence {
    
    public static int maxLenSubSequence(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        return process(str1, str2, len1 - 1, len2 - 1);
    }
    
    // str1[0....i1] 与 str2[0......i2]的最长公共子序列长度是多少？
    public static int process(char[] str1, char[] str2, int i1, int i2) {
        // 只剩一个字符时 字符相等 公共子序列长度为1
        if (i1 == 0 && i2 == 0) {
            return str1[i1] == str2[i2] ? 1 : 0;
        }
        // i1 和 i2 不同时为0
        if (i1 == 0) {
            // str1[0..0] str2[0...i2 - 1]
            // str1 剩最后一个字符的时候 str2 i2 不为最后字符 i1 i2位置字符相等 或者遍历str2剩余字符 存在相等i1 则为1 否则为0
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1, i2 - 1) == 1) ? 1 : 0;
        }
        
        if (i2 == 0) {
            // str2 剩最后一个字符的时候 str1 i1 不为最后字符 i1 i2位置字符相等 或者遍历str1剩余字符 存在相等i2 则为1 否则为0
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1 - 1, i2) == 1) ? 1 : 0;
        }
        // i1 和 i2 都不是0
        // 最长公共子序列结尾，不是以str1[i1]与str2[i2]结尾的
        int p1 = process(str1, str2, i1 - 1, i2 - 1);
        int p2 = process(str1, str2, i1, i2 - 1);
        int p3 = process(str1, str2, i1 - 1, i2);
        int p4 = 0;
        if (str1[i1] == str2[i2]) {
            p4 = p1 + 1;
        }
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }
    
    public static int maxLenSubSequenceDp(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = dp[i - 1][0] == 1 || chars1[i] == chars2[0] ? 1 : 0;
        }
        for (int j = 1; j < len2; j++) {
            dp[0][j] = dp[0][j - 1] == 1 || chars1[0] == chars2[j] ? 1 : 0;
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                // 以当前i j结尾 并且i j位置字符相等
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
    
    public static void main(String[] args) {
        String s1 = "sergs19ngj12";
        String s2 = "adszi19ngj02";
        System.out.println(maxLenSubSequenceDp(s1, s2));
    }
}
