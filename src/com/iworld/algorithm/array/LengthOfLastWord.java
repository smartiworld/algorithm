package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 58. length of last word
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 *
 * A word is a maximal
 * substring
 *  consisting of non-space characters only.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Hello World"
 * Output: 5
 * Explanation: The last word is "World" with length 5.
 * Example 2:
 *
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 * Explanation: The last word is "moon" with length 4.
 * Example 3:
 *
 * Input: s = "luffy is still joyboy"
 * Output: 6
 * Explanation: The last word is "joyboy" with length 6.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only English letters and spaces ' '.
 * There will be at least one word in s.
 * https://leetcode.com/problems/length-of-last-word/
 * @date 2024/10/27 14:15
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int lastLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                if (lastLen > 0) {
                    break;
                }
                continue;
            }
            lastLen++;
        }
        return lastLen;
    }
}
