package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 28. Implement strStr()        Easy
 * Implement strStr().
 * <p>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * https://leetcode.com/problems/implement-strstr/
 * @date 2022/6/30 15:55
 */
public class ImplementStrStr {
    
    /**
     * KMP
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        int[] sameLen = getPreSameLen(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = sameLen[j];
            } else {
                i++;
            }
        }
        return j == needle.length() ? i - j : -1;
    }
    
    private int[] getPreSameLen(String needle) {
        if (needle.length() == 1) {
            return new int[]{-1};
        }
        int[] ans = new int[needle.length()];
        ans[0] = -1;
        ans[1] = 0;
        int n = 0;
        for (int i = 2; i < needle.length(); ) {
            if (needle.charAt(i - 1) == needle.charAt(n)) {
                ans[i++] = ++n;
            } else if (n > 0) {
                n = ans[n];
            } else {
                i++;
            }
        }
        return ans;
    }
    
    public int strStr2(String haystack, String needle) {
        // empty needle appears everywhere, first appears at 0 index
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            // no enough places for needle after i
            if (i + needle.length() > haystack.length()) {
                break;
            }
            
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        ImplementStrStr implementStrStr = new ImplementStrStr();
        String haystack = "aaaaa";
        String needle = "bba";
        System.out.println(implementStrStr.strStr(haystack, needle));
    }
}
