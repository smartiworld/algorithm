package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 76. Minimum Window Substring   Hard
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 *
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * https://leetcode.com/problems/minimum-window-substring/
 * @date 2022/8/17 19:49
 */
public class MinimumWindowSubstring {
    
    public String minWindow(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        String ans = "";
        if (sChars.length < tChars.length) {
            return ans;
        }
        // 记录t字符串各字符出现的次数
        int[] tCharCount = new int['z' - 'A' + 1];
        for (char tChar : tChars) {
            tCharCount[tChar - 'A']++;
        }
        // t字符串长度 从s匹配当为0的时候表示已经匹配完全 结算长度
        int allCharCount = tChars.length;
        // 匹配最小长度
        int min = -1;
        // 匹配最小长度的字符的开始位置
        int p = 0;
        // 窗口左边界
        int l = 0;
        // 窗口右边界 不包含
        int r = 0;
        while (r < sChars.length) {
            tCharCount[sChars[r] - 'A']--;
            // 表示当前位置字符和t匹配 需要匹配的长度要减1
            if (tCharCount[sChars[r] - 'A'] >= 0) {
                allCharCount--;
            }
            r++;
            // 如果已经完全匹配
            if (allCharCount <= 0) {
                while (l <= r) {
                    // 结算最小长度
                    if (min == -1 || r - l < min) {
                        p = l;
                        min = r - l;
                    }
                    tCharCount[sChars[l] - 'A']++;
                    if (tCharCount[sChars[l] - 'A'] > 0) {
                        allCharCount++;
                    }
                    l++;
                    // 表示当前字符已经影响匹配目标字符串
                    if (allCharCount > 0) {
                        break;
                    }
                }
            }
        }
        return min == -1 ? "" : s.substring(p, p + min);
    }
    
    public static void main(String[] args) {
        String s = "a";
        String t = "b";
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow(s, t));
    }
}
