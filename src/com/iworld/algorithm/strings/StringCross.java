package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定三个字符串str1 str2 aim 如果aim包含str1 和str2所有的字符 并且str1和str2原顺序不会变
 * 例：str1=abc str2=123 aim=a12bc3
 * @date 2023/1/13 15:14
 */
public class StringCross {
    
    public boolean isCross(String str1, String str2, String aim) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int m = char1.length;
        int n = char2.length;
        char[] aims = aim.toCharArray();
        int z = aims.length;
        if (m + n != z) {
            return false;
        }
        boolean[][] isCross = new boolean[m + 1][n + 1];
        return true;
    }
}
