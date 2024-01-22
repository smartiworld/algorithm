package com.iworld.algorithm.dp.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.5.1 最少删除字符 使一个字符变成另一字符子串
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = "abcde"，s2 = "axbc"
 * 返回1。s2删掉'x'就是s1的子串了。
 * @date 2024/1/18 14:17
 */
public class MinDeleteStrChangeTargetSubStr {
    
    
    private static int min = Integer.MAX_VALUE;
    private static int minKMP = Integer.MAX_VALUE;
    
    private static String s = null;
    
    /**
     * 先将s2 从长到短生成所有的子序列
     * 使用kmp算法计算当前子序列是否为s1的子串
     *   是则终止 计算当前子序列长度和s2长度差值
     * @param s1
     * @param s2
     * @return
     */
    public static int minDeleteStrContains(String s1, String s2) {
        s = s1;
        char[] chars = s2.toCharArray();
        getAllSubSequenceContains(chars, 0, "");
        return min;
    }
    
    private static void getAllSubSequenceContains(char[] chars, int index, String subSequence) {
        if (index == chars.length) {
            if (s.contains(subSequence)) {
                min = Math.min(min, chars.length - subSequence.length());
            }
            return ;
        }
        getAllSubSequenceContains(chars, index + 1, subSequence + chars[index]);
        getAllSubSequenceContains(chars, index + 1, subSequence);
    }
    
    /**
     * 先将s2 从长到短生成所有的子序列
     * 使用kmp算法计算当前子序列是否为s1的子串
     *   是则终止 计算当前子序列长度和s2长度差值
     * @param s1
     * @param s2
     * @return
     */
    public static int minDeleteKmp(String s1, String s2) {
        s = s1;
        getAllSubSequence(s2);
        return minKMP;
    }
    
    public static void getAllSubSequence(String s) {
        char[] chars = s.toCharArray();
        getAllSubSequence(chars, 0, "");
    }
    
    private static void getAllSubSequence(char[] chars, int index, String subSequence) {
        if (index == chars.length) {
            if (isContainsKmp(s, subSequence)) {
                minKMP = Math.min(minKMP, chars.length - subSequence.length());
            }
            //ans.add(subSequence);
            return ;
        }
        getAllSubSequence(chars, index + 1, subSequence + chars[index]);
        getAllSubSequence(chars, index + 1, subSequence);
    }
    
    public static boolean isContainsKmp(String s, String subSequence) {
        if (subSequence == null || "".equals(subSequence)) {
            return true;
        }
        if (s == null || "".equals(s)) {
            return false;
        }
        char[] chars = s.toCharArray();
        int[] sameLength = getSameLength(subSequence);
        int i = 0;
        int j = 0;
        while (i < chars.length && j < subSequence.length()) {
            if (chars[i] == subSequence.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = sameLength[j];
            } else {
                i++;
            }
        }
        return j == subSequence.length();
    }
    
    private static int[] getSameLength(String subSequence) {
        if (subSequence.length() == 1) {
            return new int[]{-1};
        }
        char[] chars = subSequence.toCharArray();
        int len = chars.length;
        int[] ans = new int[len];
        ans[0] = -1;
        ans[1] = 0;
        // 前位置字符重复长度 也就是第index位置 开始需要比对
        int index = 0;
        for (int i = 2; i < len; ) {
            if (chars[i] == chars[index]) {
                ans[i] = ++index;
            } else if (index > 0) {
                index = ans[index];
            } else {
                i++;
            }
        }
        return ans;
    }
    
    
    
    /**
     * 生成s1所有子串 计算s2变成s1子串最小编辑距离（只删除）
     * 如果s1的长度较小，s2长度较大，这个方法比较合适
     * @return
     */
    public static int minDeleteEditDistance(String s1, String s2) {
        char[] chars = s1.toCharArray();
        int len2 = s2.length();
        int maxStartLen = Math.min(chars.length, s2.length());
        char[] chars2 = s2.toCharArray();
        for (int i = maxStartLen; i > 0; i--) {
            for (int l = 0; l <= chars.length - i; l++) {
                if (i == len2) {
                    if (new String(chars, l, i).equals(s2)) {
                        return 0;
                    }
                    break;
                }
                int[][] dp = new int[i + 1][len2 + 1];
                for (int j = 1; j <= len2; j++) {
                    dp[0][j] = j;
                }
                for (int k = 1; k <= i; k++) {
                    dp[k][0] = -1;
                }
                for (int k = 1; k <= i; k++) {
                    for (int j = k; j <= len2; j++) {
                        if (k == j) {
                            dp[k][j] = dp[k - 1][j - 1] == -1 || chars[k + l - 1] != chars2[j - 1] ? -1 : 0;
                        } else {
                            if (chars[k + l - 1] == chars2[j - 1]) {
                                dp[k][j] = dp[k - 1][j - 1];
                            } else {
                                dp[k][j] = dp[k][j - 1] == -1 ? -1 : dp[k][j - 1] + 1;
                            }
                        }
                    }
                }
                if (dp[i][len2] != -1) {
                    return dp[i][len2];
                }
            }
        }
        return -1;
    }
    
    private static List<String> getAllSubStr(String s1) {
        List<String> ans = new ArrayList<>();
        ans.add(s1);
        
        return ans;
    }
    
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "aaxbc";
        int minDelete = minDeleteEditDistance(s1, s2);
        System.out.println(minDelete);
        int minDeleteKmp = minDeleteKmp(s1, s2);
        System.out.println(minDeleteKmp);
        int minDeleteStrContains = minDeleteStrContains(s1, s2);
        System.out.println(minDeleteStrContains);
    }
}
