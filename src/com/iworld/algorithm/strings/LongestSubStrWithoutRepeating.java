package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3. Longest Substring Without Repeating Characters    Medium
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * @date 2022/6/22 13:20
 */
public class LongestSubStrWithoutRepeating {
    
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        // 下标为当前字符ascii value字符在串中下标
        Integer[] indexCache = new Integer[256];
        for (int l = 0, r = 0; r <= s.length(); r++) {
            // 当前已经来到字符串最后位置 或者缓存中已经存在当前字符
            if (r == s.length() || indexCache[s.charAt(r)] != null) {
                // 以l开始位置最长不重复结算
                max = Math.max(max, r - l);
                if (r == s.length()) {
                    break;
                }
                // 最小位置不能小于前出现位置的下一个位置 abba
                l = Math.max(l, indexCache[s.charAt(r)] + 1);
            }
            indexCache[s.charAt(r)] = r;
        }
        return max;
    }
    
    public int lengthOfLongestSubstring2(String s) {
        int result = 0;
        int[] cache = new int[256];
        for (int i = 0, j = 0; i < s.length(); i++) {
            // 存在现同字符则跳左边界
            j = (cache[s.charAt(i)] > 0) ? Math.max(j, cache[s.charAt(i)]) : j;
            cache[s.charAt(i)] = i + 1;
            result = Math.max(result, i - j + 1);
        }
        return result;
    }
    
    /**
     * 使用左右边界
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 记录当前字符长度左边最大重复位置
        int l = 0;
        // 遍历字符位置
        int r = 0;
        int longest = 0;
        // ascii做下标 value为字符出现位置
        int[] chars = new int[95];
        int offset = 32;
        while (r < s.length()) {
            int c = s.charAt(r);
            // 计算当前字符出现重复字符位置最大值
            l = Math.max(chars[c - offset], l);
            longest = Math.max(longest, r - l + 1);
            chars[c - offset] = r + 1;
            r++;
        }
        return longest;
    }
    
    public static void main(String[] args) {
        LongestSubStrWithoutRepeating subStrWithoutRepeating = new LongestSubStrWithoutRepeating();
        String s = "abba";
        System.out.println(subStrWithoutRepeating.lengthOfLongestSubstring(s));
        System.out.println(subStrWithoutRepeating.lengthOfLongestSubstring2(s));
        System.out.println(subStrWithoutRepeating.lengthOfLongestSubstring3(s));
    }
}
