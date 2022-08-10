package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 44. Wildcard Matching   Hard
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 *
 * Constraints:
 *
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 * @date 2022/8/9 19:27
 */
public class DpWildcardMatching {
    
    public boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        return process(sChars, 0, pChars, 0);
    }
    
    private boolean process(char[] sChars, int si, char[] pChars, int pi) {
        if (si == sChars.length) {
            if (pi == pChars.length) {
                return true;
            }
            return pChars[pi] == '*' && process(sChars, si, pChars, pi + 1);
        }
        if (pi == pChars.length) {
            return false;
        }
        if (pChars[pi] != '*') {
            return (sChars[si] == pChars[pi] || pChars[pi] == '?') && process(sChars, si + 1, pChars, pi + 1);
        } else {
            for (int i = 0; i <= sChars.length - si; i++) {
                if (process(sChars, si + i, pChars, pi + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean isMatch2(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        return process2(sChars, 0, pChars, 0);
    }
    
    /**
     * 斜率优化
     * @param sChars
     * @param si
     * @param pChars
     * @param pi
     * @return
     */
    private boolean process2(char[] sChars, int si, char[] pChars, int pi) {
        if (si == sChars.length) {
            if (pi == pChars.length) {
                return true;
            }
            return pChars[pi] == '*' && process2(sChars, si, pChars, pi + 1);
        }
        if (pi == pChars.length) {
            return false;
        }
        if (pChars[pi] != '*') {
            return (sChars[si] == pChars[pi] || pChars[pi] == '?') && process2(sChars, si + 1, pChars, pi + 1);
        } else {
            if (process2(sChars, si, pChars, pi + 1)) {
                return true;
            }
            return process2(sChars, si + 1, pChars, pi);
        }
    }
    
    public boolean isMatchDp(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int sl = sChars.length;
        int pl = pChars.length;
        boolean[][] dp = new boolean[sl + 1][pl + 1];
        dp[sl][pl] = true;
        for (int i = pl - 1; i >= 0; i--) {
            dp[sl][i] = pChars[i] == '*' && dp[sl][i + 1];
        }
        for (int i = sl - 1; i >= 0; i--) {
            for (int j = pl - 1; j >= 0; j--) {
                if (pChars[j] != '*') {
                    dp[i][j] = (sChars[i] == pChars[j] || pChars[j] == '?') && dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
        String s = "aaa", p = "***";
        DpWildcardMatching wildcardMatching = new DpWildcardMatching();
        System.out.println(wildcardMatching.isMatch(s, p));
        System.out.println(wildcardMatching.isMatch2(s, p));
        System.out.println(wildcardMatching.isMatchDp(s, p));
    }
}
