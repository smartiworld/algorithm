package com.iworld.algorithm.kmp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 从左到右模型 KMP 匹配字符串
 * 给两个字符串 一个需要从第一个字符串中找出匹配第二个字符串的初始位置 如果未匹配第二个字符串则返回-1
 * @date 2022/6/6 11:06
 */
public class StringMatcher {
    
    public static int getMatchIndex(String str, String mather) {
        if (str == null || mather == null || str.length() < mather.length()) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] res = getSameStrLength(mather);
        while (x < str.length() && y < mather.length()) {
            // 字符匹配 匹配位置后移
            if (str.charAt(x) == mather.charAt(y)) {
                x ++;
                y ++;
            } else if (y > 0) {
                // 加速  如果位置上两字符不匹配 跳转匹配字符头尾匹配位置的后一个字符
                // 例 aabaaba mather：aabaabc 最后一个位置不匹配 mather跳转到res[c前一字符头尾匹配长度=2] 跳转到2的位置
                y = res[y];
            } else {
                x ++;
            }
        }
        return y == mather.length() ? x - y : -1;
    }
    
    /**
     * 获取位置字符前面字符前后相等串的长度 不包含当前字符
     * 默认第一位记位-1第二位记位0
     * res[0]=-1 res[1]=0
     * aabaac 到c字符串时 前两位和后两位字符串长度相等 记res[5]=2
     * 加速逻辑：
     * 来到当前位置字符时 检查当前位置前一个字符和前位置字符长度下一个字符是否相等 如果相等则用前面字符相等长度加1
     * 如果不相等 跳到前位置字符长度位置 重复上述步骤
     * acbacdacbactc
     * res[t的位置]=5 到c字符的时候 跳到5位置和c字符前一位置是否相等 如果相等res[c的位置]=res[t的位置]++
     * 如果不相等取res[d得位置]=2 判断c的前一位置字符是否等2位置字符 如果相等res[c的位置]=res[2]++ 不相等重复上述步骤
     * @param mather
     * @return
     */
    private static int[] getSameStrLength(String mather) {
        if (mather.length() == 1) {
            return new int[]{-1};
        }
        int[] res = new int[mather.length()];
        res[0] = -1;
        res[1] = 0;
        // 记录上一个字符相同头尾的长度 也为字符不匹配要跳位置的下标
        int n = 0;
        for (int i = 2; i < mather.length();) {
            if (mather.charAt(i - 1) == mather.charAt(n)) {
                // 当前位置前位置字符和前位置头尾匹配下一字符 如果相等长度+1
                res[i++] = ++n;
            } else if (n > 0) {
                // 不相等则跳到前位置字符头尾匹配长度位置
                n = res[n];
            } else {
                i ++;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        String str = "aabbaccdabb";
        String mather = "dabc";
        System.out.println(getMatchIndex(str, mather));
    }
}
