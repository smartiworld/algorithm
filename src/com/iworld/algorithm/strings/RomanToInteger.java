package com.iworld.algorithm.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 13. Roman to Integer
 * Easy
 * 8K
 * 470
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together.
 * 12 is written as XII, which is simply X + II.
 * The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * Example 1:
 *
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 * Example 2:
 *
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 3:
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 * https://leetcode.com/problems/roman-to-integer/
 * @date 2022/11/15 23:40
 */
public class RomanToInteger {
    /**
     * 可以将IV看成一个整体处理 IV加4 IX加9 index走两个格
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            switch (chars[i]) {
                case 'I' :
                    nums[i] = 1;
                    break;
                case 'V' :
                    nums[i] = 5;
                    break;
                case 'X' :
                    nums[i] = 10;
                    break;
                case 'L' :
                    nums[i] = 50;
                    break;
                case 'C' :
                    nums[i] = 100;
                    break;
                case 'D' :
                    nums[i] = 500;
                    break;
                case 'M' :
                    nums[i] = 1000;
                    break;
            }
        }
        int ans = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                ans -= nums[i];
            } else {
                ans += nums[i];
            }
        }
        ans += nums[len - 1];
        return ans;
    }
    
    public int romanToInt2(String s) {
        Map<Character, Integer> romanToIntegerMap = new HashMap<>();
        romanToIntegerMap.put('I', 1);
        romanToIntegerMap.put('V', 5);
        romanToIntegerMap.put('X', 10);
        romanToIntegerMap.put('L', 50);
        romanToIntegerMap.put('C', 100);
        romanToIntegerMap.put('D', 500);
        romanToIntegerMap.put('M', 1000);
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && romanToIntegerMap.get(chars[i]) < romanToIntegerMap.get(chars[i + 1])) {
                ans -= romanToIntegerMap.get(chars[i]);
            } else {
                ans += romanToIntegerMap.get(chars[i]);
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();
        String s = "MCMXCIV";
        System.out.println(romanToInteger.romanToInt(s));
        System.out.println(romanToInteger.romanToInt2(s));
    }
}
