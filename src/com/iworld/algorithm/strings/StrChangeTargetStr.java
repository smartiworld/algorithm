package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.5.2 最小编辑距离
 * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
 * 【举例】
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=2 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=100 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
 * str1="abc"，str2="abc"，ic=5，dc=3，rc=2 不用编辑了，本来就是一样的字符串，所以返回0
 * @date 2024/1/11 17:09
 */
public class StrChangeTargetStr {
    
    /**
     * str1 转换成 str2 花费最小代价
     * 讨论以str 以i结尾 str2 以j结尾
     * @param str1
     * @param str2
     * @param ic   插入一个字符代价
     * @param dc   删除一个字符代价
     * @param rc   替换一个字符代价
     * @return
     */
    public static int minCostStrChangeTargetStr(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        if (str1 == null) {
            return str2.length() * ic;
        }
        if (str2 == null) {
            return str1.length() * dc;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        // str1 行长度变为 str2 列长度 所花费最小代价
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i * dc;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j * ic;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // str1 i位置字符和str2 j位置字符相等 当前代价和前代价相同
                // 如果
                if (chars1[i - 1] == chars1[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                // str1 0~i-1对应str2 0~j位置 最后str1 i位置需要执行删除
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                // str1 0~i 对应str2 0~j-1位置 最后str1 需要增加来对应str2 的j位置
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
            }
        }
        return dp[len1][len2];
    }
}
