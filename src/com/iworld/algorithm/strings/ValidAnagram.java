package com.iworld.algorithm.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author gq.cai
 * @version 1.0
 * @description 242. Valid Anagram
 * Easy
 * 6875
 * 238
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 * https://leetcode.com/problems/valid-anagram/
 * @date 2022/9/9 18:16
 */
public class ValidAnagram {
    
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int[] count = new int[26];
        for (char c : sChars) {
            count[c - 'a']++;
        }
        for (char c : tChars) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isAnagram2(String s, String t) {
        Character[] sChars = new Character[s.length()];
        Character[] tChars = new Character[t.length()];
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            sChars[i] = chars1[i];
        }
        for (int i = 0; i < tChars.length; i++) {
            tChars[i] = chars2[i];
        }
        Arrays.sort(sChars, new CharsComparator());
        Arrays.sort(tChars, new CharsComparator());
        return Arrays.toString(sChars).equals(Arrays.toString(tChars));
    }
    
    static class CharsComparator implements Comparator<Character> {
        
        @Override
        public int compare(Character c1, Character c2) {
            return c1 - c2;
        }
    }
}
