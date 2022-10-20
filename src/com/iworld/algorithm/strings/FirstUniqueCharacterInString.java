package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 387. First Unique Character in a String
 * Easy
 * 6835
 * 231
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * @date 2022/10/20 13:15
 */
public class FirstUniqueCharacterInString {
    
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] indexs = new int[26];
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            indexs[chars[i] - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (indexs[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        String s = "loveleetcode";
        FirstUniqueCharacterInString firstUniqueCharacterInString = new FirstUniqueCharacterInString();
        System.out.println(firstUniqueCharacterInString.firstUniqChar(s));
    }
}
