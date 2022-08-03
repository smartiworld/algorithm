package com.iworld.algorithm.strings;

import java.util.LinkedList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * @date 2022/6/28 13:12
 */
public class LetterCombinationsPhoneNumber {
    
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        // 下标表示字符中数字
        String[] mapping = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // 当前队列头部长度表示将要处理的位置
        ans.add("");
        // 拼接字符长度和原始字符长度一致时 结束
        while (ans.peek().length() != digits.length()) {
            // remove长度代表处理原字符串的位置
            String remove = ans.pop();
            // 手机键盘字母字母从2开始 map下标从0开始 所以需要-'2'
            String map = mapping[digits.charAt(remove.length()) - '2'];
            for (char c : map.toCharArray()) {
                ans.add(remove + c);
            }
        }
        return ans;
    }
    
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        process(digits, 0, new StringBuilder(), ans, map);
        return ans;
    }
    
    /**
     *
     * @param digits
     * @param index
     * @param sb
     * @param ans
     * @param map
     */
    private void process(String digits, int index, StringBuilder sb, LinkedList<String> ans, String[] map) {
        if (digits.length() == index) {
            ans.add(sb.toString());
            return ;
        }
        String cur = map[digits.charAt(index) - '2'];
        for (int i = 0; i < cur.length(); i++) {
            sb.append(cur.charAt(i));
            process(digits, index + 1, sb, ans, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    
    public static void main(String[] args) {
        LetterCombinationsPhoneNumber letterCombinationsPhoneNumber = new LetterCombinationsPhoneNumber();
        String digits = "35";
        System.out.println(letterCombinationsPhoneNumber.letterCombinations(digits));
        System.out.println(letterCombinationsPhoneNumber.letterCombinations2(digits));
    }
}
