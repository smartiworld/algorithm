package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3. Longest Substring Without Repeating Characters
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
public class SubStrWithoutRepeating {
    
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        Integer[] indexCache = new Integer[256];
        for (int l = 0, r = 0; r <= s.length(); r++) {
            if (r == s.length() || indexCache[s.charAt(r)] != null) {
                max = Math.max(max, r - l);
                if (r == s.length()) {
                    break;
                }
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
            j = (cache[s.charAt(i)] > 0) ? Math.max(j, cache[s.charAt(i)]) : j;
            cache[s.charAt(i)] = i + 1;
            result = Math.max(result, i - j + 1);
        }
        return result;
    }
    
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int l = 0, r = 0, longest = 0;
        int[] chars = new int[95];
        int offset = 32;
        
        while (r < s.length()) {
            int c = s.charAt(r);
            l = Math.max(chars[c - offset], l);
            longest = Math.max(longest, r - l + 1);
            chars[c - offset] = r + 1;
            r++;
        }
        
        return longest;
    }
    
    public static void main(String[] args) {
        SubStrWithoutRepeating subStrWithoutRepeating = new SubStrWithoutRepeating();
        String s = "abba";
        System.out.println(subStrWithoutRepeating.lengthOfLongestSubstring(s));
        System.out.println(subStrWithoutRepeating.lengthOfLongestSubstring2(s));
    }
}
