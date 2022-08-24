package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 125. Valid Palindrome  Easy
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
 * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 *
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 * https://leetcode.com/problems/valid-palindrome/
 * @date 2022/8/24 12:50
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        boolean ans = true;
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            while (l < r && !isLetter(chars[l]) && !isNum(chars[l])) {
                l++;
            }
            while (l < r && !isLetter(chars[r]) && !isNum(chars[r])) {
                r--;
            }
            if (!isEquals(chars[l], chars[r])) {
                return false;
            }
            l++;
            r--;
        }
        return ans;
    }
    
    private boolean isEquals(char c1, char c2) {
        if (isLetter(c1) && isLetter(c2)) {
            return toLowercaseLetter(c1) == toLowercaseLetter(c2);
        }
        return c1 == c2;
    }
    
    private boolean isLetter(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }
    
    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
    
    private char toLowercaseLetter(char c) {
        if (c >= 65 && c <= 90) {
            return (char)(c + 'a' - 'A');
        }
        return c;
    }
    
    public boolean isPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        while (L < R) {
            if (validChar(str[L]) && validChar(str[R])) {
                if (!equal(str[L], str[R])) {
                    return false;
                }
                L++;
                R--;
            } else {
                L += validChar(str[L]) ? 0 : 1;
                R -= validChar(str[R]) ? 0 : 1;
            }
        }
        return true;
    }
    
    public boolean validChar(char c) {
        return isLetter(c) || isNumber(c);
    }
    
    public boolean equal(char c1, char c2) {
        if (isNumber(c1) || isNumber(c2)) {
            return c1 == c2;
        }
        return (c1 == c2) || (Math.max(c1, c2) - Math.min(c1, c2) == 32);
    }
    
    public boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }
    
    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        //raceacar
        String s = "ab2a";
        System.out.println(validPalindrome.toLowercaseLetter('Z'));
        System.out.println(validPalindrome.isPalindrome(s));
    }
    
}
