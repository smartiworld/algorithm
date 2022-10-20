package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 395. Longest Substring with At Least K Repeating Characters
 * Medium
 * 4662
 * 379
 * Given a string s and an integer k,
 * return the length of the longest substring of s
 * such that the frequency of each character in this substring is greater than or equal to k.
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^5
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * @date 2022/10/14 17:47
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    
    public int longestSubstring(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 0 || len < k) {
            return 0;
        }
        if (k == 1) {
            return len;
        }
        // 统计每个字符出现得次数
        int[] counts = new int[26];
        counts[0] = 0;
        for (char c : chars) {
            counts[c - 'a']++;
        }
        // 出现频率小于k的字符
        char badChar = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0 && counts[i] < k) {
                badChar = (char) ('a' + i);
                break;
            }
        }
        if (badChar == 0) {
            return len;
        }
        int res = 0;
        // 找到小于当前k
        int index = s.indexOf(badChar);
        // 排除当前不满足k次的字符
        int left = longestSubstring(s.substring(0, index), k);
        int right = longestSubstring(s.substring(index + 1), k);
        res = Math.max(left, right);
        return res;
    }
    
    /**
     * better
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring2(String s, int k) {
        char[] chars = s.toCharArray();
        return process(chars, 0, chars.length - 1, k);
    }
    
    /**
     * 拆分法 找出不符合k次的字符 左右递归调用
     * @param chars 字符数组
     * @param l     chars处理左边界
     * @param r     chars处理有边界
     * @param k     k次
     * @return
     */
    private int process(char[] chars, int l, int r, int k) {
        int len = r - l + 1;
        // 没有处理
        if (len <= 0 || len < k) {
            return 0;
        }
        if (k == 1) {
            return len;
        }
        // 统计字符出现频率
        int[] counts = new int[26];
        for (int i = l; i <= r; i++) {
            counts[chars[i] - 'a']++;
        }
        // 记录小于k次字符
        char less = 0;
        // 找出频率小于k的字符
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0 && counts[i] < k) {
                less = (char) ('a' + i);
                break;
            }
        }
        if (less == 0) {
            return len;
        }
        int lessIndex = l;
        for (int i = l; i <= r; i++) {
            if (chars[i] == less) {
                lessIndex = i;
                break;
            }
        }
        int left = process(chars, l, lessIndex - 1, k);
        int right = process(chars, lessIndex + 1, r, k);
        return Math.max(left, right);
    }
    
    
    // 会超时，但是思路的确是正确的
    public static int longestSubstring3(String s, int k) {
        return process2(s.toCharArray(), 0, s.length() - 1, k);
    }
    
    public static int process2(char[] str, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        int[] counts = new int[26];
        for (int i = l; i <= r; i++) {
            counts[str[i] - 'a']++;
        }
        char few = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0 && min > counts[i]) {
                few = (char) (i + 'a');
                min = counts[i];
            }
        }
        if (min >= k) {
            return r - l + 1;
        }
        int pre = 0;
        int max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            if (str[i] == few) {
                max = Math.max(max, process2(str, pre, i - 1, k));
                pre = i + 1;
            }
        }
        if (pre != r + 1) {
            max = Math.max(max, process2(str, pre, r, k));
        }
        return max;
    }
}
