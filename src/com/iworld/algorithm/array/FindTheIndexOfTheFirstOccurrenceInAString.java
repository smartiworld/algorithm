package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 28. Find the Index of the First Occurrence in a String
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 *
 * Constraints:
 *
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string
 * @date 2024/11/19 19:23
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {
    
    public int strStr(String haystack, String needle) {
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int hLen = h.length;
        int nLen = n.length;
        if (hLen < nLen) {
            return -1;
        }
        for (int i = 0; i < hLen - nLen; i++) {
            int j = 0;
            int k = i;
            while (j < nLen && h[k] == n[j]) {
                j++;
                k++;
            }
            if (j == nLen) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        String haystack = "a";
        String needle = "a";
        FindTheIndexOfTheFirstOccurrenceInAString f = new FindTheIndexOfTheFirstOccurrenceInAString();
        System.out.println(f.strStr(haystack, needle));
    }
}
