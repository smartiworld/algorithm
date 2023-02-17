package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 从一个字符串中找到最长不包含重复字符的字符串
 * https://github.com/algorithmzuo/trainingcamp003/blob/master/src/class07/Code06_LongestNoRepeatSubstring.java
 * 1.从左到右的遍历 记录字符位置 如果有相同字符 则记录最后位置
 * 2.一个变量记录当前没有重复字符的开始位置 如果出现重复字符 则将开始记录到重复字符开始的位置
 * 3.结算字符长度每次将当前位置和无重复字符开始位置计算字符长度 抓住最大值
 * @date 2023/2/6 20:05
 */
public class LongestNoRepeatSubstring {
    
    public static int maxUnique(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = chars.length;
        int res = 0;
        int left = -1;
        for (int i = 0; i < len; i++) {
            int pre = map[chars[i]];
            left = Math.max(left, pre);
            map[chars[i]] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }
    
    public static String maxUniqueString(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = chars.length;
        int res = 0;
        // 无重复字符开始位置
        int left = -1;
        // 最大字串无重复字符开始位置
        int maxStart = 0;
        for (int i = 0; i < len; i++) {
            int pre = map[chars[i]];
            left = Math.max(left, pre);
            map[chars[i]] = i;
            // 当前所能形成无重复字符子串长度
            int cur = i - left;
            if (cur > res) {
                maxStart = left;
                res = cur;
            }
        }
        return new String(chars, maxStart + 1, res);
    }
    
    public static int maxUniqueZ(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        // map 替代了哈希表   假设字符的码是0~255
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }
        return len;
    }
    
    // for test
    public static String maxUniqueStringZ(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String str = getRandomString(20);
            int i1 = maxUnique(str);
            int i2 = maxUniqueZ(str);
            if (i1 != i2) {
                System.out.println(i1 + "===" + i2 + "===" + str);
            }
            String x = maxUniqueString(str);
            String s = maxUniqueStringZ(str);
            if (!x.equals(s)) {
                System.out.println(x + "=====" + s + "=====" + str);
            }
        }
    }
    // for test
    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }
    
}
