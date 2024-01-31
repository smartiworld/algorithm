package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.6.6 两个字符串是否能按照顺序拼接目标字符串
 * 给定三个字符串str1 str2 aim 如果aim包含str1 和str2所有的字符 并且str1和str2原顺序不会变
 * 例：str1=abc str2=123 aim=a12bc3
 *
 * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符， 而且在aim中属于str1的字符之间保持原来在str1中的顺序
 * 属于str2的字符之间保持 原来在str2中的顺序，
 * 那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成
 * 【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成
 * @date 2023/1/13 15:14
 */
public class StringCross {
    
    /**
     * 可以有重复字符
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean isCross(String str1, String str2, String aim) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int r = chars1.length;
        int c = chars2.length;
        char[] aims = aim.toCharArray();
        int z = aims.length;
        if (r + c != z) {
            return false;
        }
        // 当前str1和str2长度 是否匹配aim同长度字符 下标从0开始表示当前字符0长度时
        boolean[][] isCross = new boolean[r + 1][c + 1];
        isCross[0][0] = true;
        for (int i = 1; i <= r; i++) {
            isCross[i][0] = isCross[i - 1][0] && chars1[i - 1] == aims[i - 1];
        }
        for (int j = 1; j <= c; j++) {
            isCross[0][j] = isCross[0][j - 1] && chars2[j - 1] == aims[j - 1];
        }
        // str1长度和str2长度拼接字符是否可以拼接出等长的cross字符
        // isCross[i][j]  如果isCross[i][j-1]为true表示当前str1 i长度和str2 j-1长度可以拼接出aim i+j-1长度的字符
        // 只需查看str2 j-1位置和aim i+j-1位置处两字符是否相等如果相等 则为true
        // 如果不等需要 看str1 i-1长度和str2 j长度字符是否可以拼接出aim i+j-1长度的字符即isCross[i-1][j]是否为true
        // 然后比较str1 i-1位置字符和aim i+j-1位置处两字符是否相等如果相等 相等为true  其他则为false
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                isCross[i][j] = isCross[i][j - 1] && chars2[j - 1] == aims[i + j - 1] || isCross[i - 1][j] && chars1[i - 1] == aims[i + j - 1];
            }
        }
        return isCross[r][c];
    }
    
    /**
     * str1和str2没有相同字符
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean isCrossNotSame(String str1, String str2, String aim) {
        if (aim == null) {
            return str1 == null && str2 == null;
        }
        if (str1 == null) {
            return aim.equals(str2);
        }
        if (str2 == null) {
            return aim.equals(str1);
        }
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < aim.length(); i++) {
            if (i1 < str1.length() && aim.charAt(i) == str1.charAt(i1)) {
                i1++;
                continue;
            }
            if (i2 < str2.length() && aim.charAt(i) == str2.charAt(i2)) {
                i2++;
                continue;
            }
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String str1 = "aa2";
        String str2 = "aaa31";
        String aim = "aaa3aa21";
        System.out.println(isCrossNotSame(str1, str2, aim));
        System.out.println(isCross(str1, str2, aim));
    }
}
