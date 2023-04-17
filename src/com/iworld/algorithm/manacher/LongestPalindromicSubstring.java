package com.iworld.algorithm.manacher;

/**
 * @author gq.cai
 * @version 1.0
 * @description 5. Longest Palindromic Substring
 * Medium
 * 24.7K
 * 1.5K
 * Given a string s, return the longest
 * palindromic
 *
 * substring
 *  in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * https://leetcode.com/problems/longest-palindromic-substring/
 * @date 2023/4/15 16:35
 */
public class LongestPalindromicSubstring {
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] chars = dealStr(s);
        int len = chars.length;
        // 每个位置缓存的回文半径
        int[] cache = new int[len];
        cache[0] = 1;
        // 最大回文半径
        int r = -1;
        // 最大回文半径中心点
        int c = -1;
        // #a#b#a#b#a#  #a#b#a#a#b#a#
        int maxStart = -1;
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            // 在回文半径外 直接以i点为中心左右匹配
            if (i >= r) {
                int x = i - 1;
                int y = i + 1;
                while (x > -1 && y < len) {
                    if (chars[x] != chars[y]) {
                        break;
                    }
                    x--;
                    y++;
                }
                cache[i] = y - i;
            } else {
                // 当前位置在回文半径内 根据j的回文半径左边界讨论 分三种情况
                // r关于c的对称点
                int l = (c << 1) - r;
                // i关于c的对称点
                int j = (c << 1) - i;
                // j回文左边界 j位置-j的回文半径
                int jL = j - cache[j];
                // 第一种情况 j的回文半径左边界在l~c内 此时i的回文半径和j的回文半径相同
                if (jL > l) {
                    cache[i] = cache[j];
                } else if (jL < l) {
                    //第二种情况 j回文半径左边界在l之前l~c之外 此时i的回文半径为i~r的距离
                    cache[i] = r - i;
                } else {
                    // 第三种情况 j回文半径左边界在l上 此时i的回文半径至少为i~r 从r向右和r关于i的对称点k 向左操作
                    int k = (i << 1) - r;
                    int t = r;
                    while (k > -1 && t < len) {
                        if (chars[k] != chars[t]) {
                            break;
                        }
                        k--;
                        t++;
                    }
                    cache[i] = t - i;
                }
            }
            if (i + cache[i] > r) {
                r = i + cache[i];
                c = i;
            }
            if (cache[i] > max) {
                max = cache[i];
                maxStart = i - max + 1;
                // r - 1 - ((c << 1 - r + 1)) (r - c - 1) << 1
            }
        }
        return generateStr(chars, maxStart, max);
    }
    
    private char[] dealStr(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        char[] result = new char[(len << 1) + 1];
        result[0] = '#';
        int j = 1;
        for (int i = 0; i < len; i++) {
            result[j++] = chars[i];
            result[j++] = '#';
        }
        return result;
    }
    
    private String generateStr(char[] chars, int start, int len) {
        char[] result = new char[len - 1];
        int j = start + 1;
        for (int i = 0; i < result.length; i++) {
            result[i] = chars[j];
            j += 2;
        }
        return new String(result);
    }
    
    /**
     * 计算一个字符最大回文长度
     * longestPalindrome方法种 i>=r 的时候和i<r其中一个条件 都存在以i为中心两边字符串对比
     * 严格根据逻辑分支
     * @param str
     * @return
     */
    public String longestPalindromeOpt(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] dealStr = dealStr(str);
        // 区间不包含r
        int r = -1;
        int c = -1;
        int[] cache = new int[dealStr.length];
        int max = Integer.MIN_VALUE;
        int maxStart = -1;
        for (int i = 0; i < dealStr.length; i++) {
            // 首先不需要对比的位置 当前最大范围到R边界
            // i>r和i<r并且i的对称点j的回文半径刚好在r的回文半径此时 都存在以i为中心字符串对比
            // i<r的时候一种情况j在l和对称点c中间此时i的回文半径为cache[(c << 1) - i],(c << 1) - i为i关于c的对称点j
            // i<r的时候 另一种情况为j的回文半径在l和c之外也就是小于l的时候 此时i的回文最右侧只能到达r的位置，i的回文半径r-l
            // 所以就初始赋值i<=r的时候Math.min(r - i, cache[(c << 1) - i]) 回文半径最小为1 所以i>=r的时候默认赋值1
            cache[i] = r > i ? Math.min(r - i, cache[(c << 1) - i]) : 1;
            // i加当前位置初始半径 在字符串的范围内
            // 此时i的回文半径已经有默认值 然后以i的位置加减i的回文半径所路过的字符比较 相等则扩大当前i的回文半径
            while (i + cache[i] < dealStr.length && i - cache[i] > -1) {
                // 如果以i点为中心 对称点相等 半径增长
                // 否则跳出循环
                if (dealStr[i + cache[i]] == dealStr[i - cache[i]]) {
                    cache[i] ++;
                } else {
                    break;
                }
            }
            // 如果当前位置加当前半径超过原半径位置 更新
            if (i + cache[i] > r) {
                r = i + cache[i];
                c = i;
            }
            if (cache[i] > max) {
                max = cache[i];
                maxStart = i - max + 1;
            }
            max = Math.max(max, cache[i]);
        }
        return generateStr(dealStr, maxStart, max);
    }
    
    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String s = "cbbd";
        System.out.println(longestPalindromicSubstring.longestPalindrome(s));
        System.out.println(longestPalindromicSubstring.longestPalindromeOpt(s));
    }
}
