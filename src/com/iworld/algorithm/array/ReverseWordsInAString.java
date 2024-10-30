package com.iworld.algorithm.array;

import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 151.reverse words in a string
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 *
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * @date 2024/10/28 20:00
 */
public class ReverseWordsInAString {
    
    public String reverseWords(String s) {
        LinkedList<Character> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean spaceFlag = false;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                while (!queue.isEmpty()) {
                    spaceFlag = true;
                    stringBuilder.append(queue.pollLast());
                }
                if (spaceFlag) {
                    stringBuilder.append(' ');
                    spaceFlag = false;
                }
            } else {
                queue.addLast(chars[i]);
            }
        }
        if (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                stringBuilder.append(queue.pollLast());
            }
        }else {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        
        return stringBuilder.toString();
    }
    
    public String reverseWordsLessSpace(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        char[] ans = new char[n];
        // 单词左边界
        int left = n - 1;
        // 单词右边界
        int right = n - 1;
        int ansIndex = 0;
        while (left >= 0) {
            // 定位到单词右边界
            while (right >= 0 && chars[right] == ' ') {
                right--;
            }
            left = right;
            // 左边界从右边界开始 遍历整个单词 来到开始字符前一个位置
            while (left >= 0 && chars[left] != ' ') {
                left--;
            }
            // 表示当前有词语可以收集 首词时不填充空格
            if (left < right && ansIndex > 0) {
                ans[ansIndex++] = ' ';
            }
            for (int i = left + 1; i <= right; i++) {
                ans[ansIndex++] = chars[i];
            }
            right = left;
        }
        return new String(ans, 0, ansIndex);
    }
    
    public String reverseWordsBest(String s) {
    
        char text[] = s.toCharArray();
        char[] reverse = new char[s.length()];
        
        int reversedSize = reversedSize(text, reverse,0);
        return new String(reverse,0, reversedSize);
    }
    
    private int reversedSize(char text[], char reverse[], int index) {
        if(index == text.length) {
            return 0;
        }
        while(index < text.length && text[index] == ' ') {
            index++;
        }
        int endIndex = index;
        while(endIndex < text.length && text[endIndex] != ' ') {
            endIndex++;
        }
        int reversedSize = reversedSize(text, reverse, endIndex);
        if(reversedSize > 0 ) {
            reverse[reversedSize] = ' ';
            reversedSize++;
        }
        for(int i = index ; i < endIndex ; i++) {
            reverse[reversedSize++] = text[i];
        }
        return reversedSize;
        
    }
    
    public static void main(String[] args) {
        ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();
        String s = "the sky is blue";
        System.out.println(reverseWordsInAString.reverseWords(s));
        System.out.println(reverseWordsInAString.reverseWordsLessSpace(s));
    }
}
