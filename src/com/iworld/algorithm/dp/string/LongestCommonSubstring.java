package com.iworld.algorithm.dp.string;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.3.4 两个字符的最长公共子串
 * @date 2023/12/17 20:33
 */
public class LongestCommonSubstring {
    static int max = 0;
    public static int longestCommonSubstring(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        process(chars1, chars2, chars1.length - 1, chars2.length - 1);
        return max;
    }
    
    public static int process(char[] chars1, char[] chars2, int i1, int i2) {
        if (i1 == 0 && i2 == 0) {
            int ans = chars1[i1] == chars2[i2] ? 1 : 0;
            max = Math.max(max, ans);
            return ans;
        }
        if (i1 == 0) {
            int ans = chars1[i1] == chars2[i2] ? 1 : process(chars1, chars2, i1, i2 - 1);
            max = Math.max(max, ans);
            return ans;
        }
        if (i2 == 0) {
            int ans = chars1[i1] == chars2[i2] ? 1 : process(chars1, chars2, i1 - 1, i2);
            max = Math.max(max, ans);
            return ans;
        }
        int ans = 0;
        if (chars1[i1] == chars2[i2]) {
            ans = process(chars1, chars2, i1 - 1, i2 - 1) + 1;
        }
        max = Math.max(max, ans);
        return ans;
    }
    
    /**
     * 最长公共子串长度
     * @param s1
     * @param s2
     * @return
     */
    public static int longestCommonSubstringDp(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : 0;
        }
        for (int j = 1; j < len2; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : 0;
        }
        int max = 0;
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                dp[i][j] = chars1[i] == chars2[j] ? dp[i - 1][j - 1] + 1 : 0;
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
    
    public static String longestCommonSubstringStr(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : 0;
        }
        for (int j = 1; j < len2; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : 0;
        }
        int max = 0;
        int index = -1;
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                dp[i][j] = chars1[i] == chars2[j] ? dp[i - 1][j - 1] + 1 : 0;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    index = i;
                }
            }
        }
        String result = null;
        if (index != -1) {
            result = new String(chars1, index - max + 1, max);
        }
        return result;
    }
    
    public static void main(String[] args) {
        String s1 = "sergs19n12gj122";
        String s2 = "szi19n12ij122";
        System.out.println(longestCommonSubstring(s1, s2));
        System.out.println(longestCommonSubstringDp(s1, s2));
        System.out.println(longestCommonSubstringStr(s1, s2));
    }
}
