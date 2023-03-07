package com.iworld.algorithm.dp.string;

/**
 * @author gq.cai
 * @version 1.0
 * @description 动态规划  求两个字符串最长公共子序列
 * 子序列 顺序不连续
 * @date 2022/5/18 17:10
 */
public class DPStringLongestSequence {
    /**
     * 最长子序列是否包含当前位置 也即为两个字符串当前位置字符是否相等
     * 1.两个字符串都不包含 那就和当前位置无关系
     * dp[i][j]=dp[i-1][j-1]
     * 2.第一个字符串包含，第二个字符串不包含
     * dp[i][j]=dp[i][j-1]
     * 3.第一个字符串不包含，第二个字符串包含
     * dp[i][j]=dp[i-1][j]
     * 4.都包含 即当前位置肯定肯定算一个 去除当前位置 也就来到第一个场景
     * dp[i][j]=dp[i-1][j-1]+1
     * x|y
     * i|j
     * y和i一定大于等一x 在决定y位置的时候 已经做出了决定
     * @param str1
     * @param str2
     * @return
     */
    public static int longestSequenceDp(String str1, String str2) {
        // 行下标代表str1字符串从0~结尾的过程
        // 列下标代表str1字符串从0~结尾的过程
        int[][] dp = new int[str1.length()][str2.length()];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < str1.length(); i++) {
            dp[i][0] = str2.charAt(0) == str1.charAt(i) ? 1 : 0;
        }
        for (int i = 0; i < str2.length(); i++) {
            dp[0][i] = str1.charAt(0) == str2.charAt(i) ? 1 : 0;
        }
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                // 可以默认不用考虑case1 case2和case3肯定大于等于case1的结果
                // 先决定出不都包含当前位置 哪个大取哪个 case 2 3
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[str1.length() - 1][str2.length() - 1];
    }
    
    public static void main(String[] args) {
        String str1 = "asdr56ghydg";
        String str2 = "asdftghfddf";
        System.out.println(longestSequenceDp(str1, str2));
    }
}
