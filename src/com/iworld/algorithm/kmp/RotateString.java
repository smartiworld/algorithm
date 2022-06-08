package com.iworld.algorithm.kmp;

/**
 * @author gq.cai
 * @version 1.0
 * @description KMP  旋转字符串 判断一个字符串是否是 原字符串旋转字符串
 * 例 原串=123456
 * 234561 345612 456123 561234 612345 均为原串的旋转字符串
 * 思路：将原串double连接 使用kmp 计算字串是否为连接串的字串 是字串则为旋转串
 * @date 2022/6/8 11:11
 */
public class RotateString {

    public static boolean isRotateString(String origin, String rotateString) {
        if (origin == null || rotateString == null || origin.length() != rotateString.length()) {
            return false;
        }
        String dOrigin = origin + origin;
        int x = 0;
        int y = 0;
        //记录当前位置字符 前后字符相等的长度
        int[] sameStrLength = getSameStrLength(rotateString);
        while (x < dOrigin.length() && y < rotateString.length()) {
            if (dOrigin.charAt(x) == rotateString.charAt(y)) {
                x ++;
                y ++;
            } else if (y > 0) {
                // 不相等 需要回退y到上一个相等位置的下一个位置
                y = sameStrLength[y];
            } else {
                x ++;
            }
        }
        return y == rotateString.length();
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
        int index = 2;
        // 表示前字符 前面后面不相等的起始位置
        int cn = 0;
        while (index < mather.length()) {
            if (mather.charAt(index - 1) == mather.charAt(cn)) {
                // cn来到当前字符位置前后长度的位置
                res[index++] = ++cn;
            } else if (cn > 0) {
                cn = res[cn];
            } else {
                index ++;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        String origin = "123456";
        String rotateString = "456132";
        System.out.println(isRotateString(origin, rotateString));
    }
    
}
