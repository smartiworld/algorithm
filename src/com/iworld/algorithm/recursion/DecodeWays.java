package com.iworld.algorithm.recursion;

/**
 * @author gq.cai
 * @version 1.0
 * @description 91. Decode Ways   Medium
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters
 * using the reverse of the mapping above (there may be multiple ways). For example,
 * "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06"
 * cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 * https://leetcode.com/problems/decode-ways/
 * @date 2022/8/18 17:34
 */
public class DecodeWays {
    
    public int numDecodings(String s) {
        return process(s.toCharArray(), 0);
    }
    
    private int process(char[] s, int index) {
        if (index == s.length) {
            return 1;
        }
        if (s[index] <= '0') {
            return 0;
        }
        int count = process(s, index + 1);
        if (index + 2 <= s.length && s[index] < '3') {
            if (s[index] == '1' || (s[index + 1] >= '0' && s[index + 1] < '7')) {
                count += process(s, index + 2);
            }
        }
        return count;
    }
    
    // 潜台词：str[0...index-1]已经转化完了，不用操心了
    // str[index....] 能转出多少有效的，返回方法数
    public static int process2(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }
        // index还有字符, 又不是‘0’
        // 1) （index 1 ~ 9）
        int ways = process2(str, index + 1);
        // 2) (index index + 1) -> index + 2 ....
        if (index + 1 == str.length) {
            return ways;
        }
        // (index index + 1) "23" -> 23 "17" -> 17
        int num = (str[index] - '0') * 10 + str[index + 1] - '0';
        // num > 26
        if (num <= 26) {
            ways += process2(str, index + 2);
        }
        return ways;
    }
    
    public int numDecodingsDp(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] > '0') {
                dp[i] = dp[i + 1];
                if (i + 2 <= n && chars[i] < '3') {
                    if (chars[i] == '1' || (chars[i + 1] >= '0' && chars[i + 1] < '7')) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }
    
    public int numDecodingsDp2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] > '0') {
                dp[i] = dp[i + 1];
                if (i + 1 == n) {
                    continue;
                }
                int num = (chars[i] - '0') * 10 + chars[i + 1] - '0';
                // num > 26
                if (num <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }
    
    // 2611055971756562
    public static void main(String[] args) {
        String s = "111111111111111111111111111111111111111111111";
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings(s));
        System.out.println(decodeWays.numDecodingsDp(s));
        System.out.println(decodeWays.numDecodingsDp2(s));
    }
}
