package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个字符串，如果可以在字符串任意位置添加字符最少添加几个可以让字符串整体是回文串
 * https://github.com/algorithmzuo/trainingcamp003/blob/master/src/class08/Code06_PalindromeMinAdd.java
 * @date 2023/2/6 13:07
 */
public class PalindromeMinAdd {
    
    /**
     * 以样本长度一个做行一个做列 动态规划
     * l-r 字符串开头l和字符串结尾r
     * 动态规划表 表示l-r形成回文串 最少需要添加几个字符 只填右上部分 l<=r
     * 动态转移
     * 1.l~r范围 先处理l~r-1范围 再补r 在l-1的位置 dp[l][r - 1]
     * 2.l~r范围 先处理l+1~r范围 再补l 在r+1的位置 dp[l + 1][r] + 1
     * 3.l~r范围 l和r位置字符相等 处理l+1~r-1范围  dp[l + 1][r - 1]
     * @param str
     * @return
     */
    public int getMinCount(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[][] dp = getDp(chars);
        return dp[0][len - 1];
    }
    
    private int[][] getDp(char[] chars) {
        int len = chars.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            if (chars[i] != chars[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }
    
    /**
     * 原字符串拼成回文串 使用最少字符 返回拼接好的回文串
     * @param str
     * @return
     */
    public String getPalindrome(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[][] dp = getDp(chars);
        int min = dp[0][len - 1];
        char[] res = new char[len + min];
        int left = 0;
        int right = res.length - 1;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            if (dp[l][r] == dp[l + 1][r - 1]) {
                // l和r字符一样 结果左边和右边填充l字符 原字符处理l+1到r-1范围
                res[left++] = res[right--] = chars[r--];
                l++;
            } else {
                if (dp[l + 1][r] < dp[l][r - 1]) {
                    // 原字符依赖l+1到r位置 需要处理l字符在
                    res[left++] = chars[l];
                    res[right--] = chars[l++];
                } else {
                    res[left++] = chars[r];
                    res[right--] = chars[r--];
                }
            }
        }
        return new String(res);
    }
    
    public static void main(String[] strs) {
        PalindromeMinAdd palindromeMinAdd = new PalindromeMinAdd();
        String str = "AB1CD2EFG3H43IJK2L1MN";
        String palindrome = palindromeMinAdd.getPalindrome(str);
        System.out.println(palindrome);
        int minCount = palindromeMinAdd.getMinCount(str);
        System.out.println(minCount);
    }
}
