package com.iworld.algorithm.array.lr;

/**
 * @author gq.cai
 * @version 1.0
 * @description 392. Is Subsequence
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 104
 * s and t consist only of lowercase English letters.
 *
 *
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
 * and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 * https://leetcode.com/problems/is-subsequence/
 * @date 2024/11/28 15:45
 */
public class IsSubsequence {
    
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int sLen = sChars.length;
        int tLen = tChars.length;
        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < sLen && tIndex < tLen) {
            while (sIndex < sLen && tIndex < tLen && sChars[sIndex] == tChars[tIndex]) {
                sIndex++;
                tIndex++;
            }
            if (sIndex >= sLen) {
                return true;
            }
            tIndex++;
        }
        return false;
    }
    
    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        IsSubsequence iq = new IsSubsequence();
        System.out.println(iq.isSubsequence(s, t));
    }
}
