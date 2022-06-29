package com.iworld.algorithm.manacher;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/6/23 13:54
 */
public class LongestPalindromeStr {
    
    public String longestPalindrome(String s) {
        int c = -1;
        int r = -1;
        char[] chars = dleString(s);
        int n = chars.length;
        int[] cache = new int[n];
        int i = 0;
        int max = 0;
        int index = 0;
        while (i < n) {
            cache[i] = r > i ? Math.min(r - i, cache[(c << 1) - i]) : 1;
            while (i + cache[i] < n && i - cache[i] > -1) {
                if (chars[i + cache[i]] == chars[i - cache[i]]) {
                    cache[i] ++;
                } else {
                    break;
                }
            }
            if (i + cache[i] > r) {
                c = i;
                r = i + cache[i];
            }
            if (cache[i] > max) {
                max = cache[i];
                index = i;
            }
            i++;
        }
        return generatorString(chars, index, max);
    }
    
    private char[] dleString(String s) {
        char[] chars = new char[(s.length() << 1) + 1];
        chars[0] = '#';
        int index = 1;
        for (int i = 0; i < s.length(); i++) {
            chars[index++] = s.charAt(i);
            chars[index++] = '#';
        }
        return chars;
    }
    
    private String generatorString (char[] chars, int index, int radius) {
        char[] result = new char[radius - 1];
        int start = index - radius + 2;
        for (int i = 0; i < result.length; i++) {
            result[i] = chars[start];
            start = start + 2;
        }
        return new String(result);
    }
    
    
    private int begin, maxLen;
    public String longestPalindrome2(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        expandCenter(str, n >> 1, 0);
        return new String(str, begin, maxLen);
    }
    
    /**
     * idx来到对比的位置
     * @param str
     * @param idx        到对比的位置
     * @param direction  控制左右
     */
    private void expandCenter(char[] str, int idx, int direction) {
        int n = str.length, i = idx - 1, j = idx + 1;
        // 对比左边字符和当前位置是否相同
        while (i >= 0 && str[i] == str[idx]) {
            i--;
        }
        // 对比右边字符和当前位置是否相同
        while (j < n && str[j] == str[idx]) {
            j++;
        }
        int x = i, y = j;
        // 对比左右对称字符是否相同
        while (x >= 0 && y < n && str[x] == str[y]) {
            x--;
            y++;
        }
        // 结算当前回文字符长度
        if (y - x - 1 > maxLen) {
            begin = x + 1;
            maxLen = y - x - 1;
        }
        // 剩余字符串长度如果超过当前已经的出回文长度
        if (i + 1 << 1 > maxLen && direction <= 0) {
            expandCenter(str, i, -1);
        }
        if (n - j << 1 > maxLen && direction >= 0) {
            expandCenter(str, j, 1);
        }
    }
    private int lo;
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        for (int i = 0; i < len-1; i++) {
            //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i);
            //assume even length
            extendPalindrome(s, i, i+1);
        }
        return s.substring(lo, lo + maxLen);
    }
    
    /**
     * 对比给定传入字符位置
     * @param s
     * @param j 需要对比的左字符
     * @param k 需要对比的右字符
     */
    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        // 结算长度 和起始位置
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
    
    public static void main(String[] args) {
        LongestPalindromeStr longestPalindromeStr = new LongestPalindromeStr();
        String s = "babad";
        System.out.println(longestPalindromeStr.longestPalindrome(s));
    }
}
