package com.iworld.algorithm.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 171. Excel Sheet Column Number
 * Easy
 * 3581
 * 292
 * Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * Example 1:
 *
 * Input: columnTitle = "A"
 * Output: 1
 * Example 2:
 *
 * Input: columnTitle = "AB"
 * Output: 28
 * Example 3:
 *
 * Input: columnTitle = "ZY"
 * Output: 701
 *
 * Constraints:
 *
 * 1 <= columnTitle.length <= 7
 * columnTitle consists only of uppercase English letters.
 * columnTitle is in the range ["A", "FXSHRXW"].
 * @date 2022/9/3 19:35
 */
public class ExcelSheetColumnNumber {
    
    public int titleToNumber(String columnTitle) {
        char[] str = columnTitle.toCharArray();
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            ans = ans * 26 + (str[i] - 'A') + 1;
        }
        return ans;
    }
    
    public int titleToNumber2(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int n = chars.length - 1;
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            ans += (chars[i] - 'A' + 1) * Math.pow(26, n--);
        }
        return ans;
    }
    
    public int titleToNumber3(String columnTitle) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('A' + i), i + 1);
        }
        char[] chars = columnTitle.toCharArray();
        int n = chars.length - 1;
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            ans += map.get(chars[i]) * Math.pow(26, n--);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        ExcelSheetColumnNumber excelSheetColumnNumber = new ExcelSheetColumnNumber();
        String columnTitle = "AB";
        System.out.println(excelSheetColumnNumber.titleToNumber(columnTitle));
        System.out.println(excelSheetColumnNumber.titleToNumber2(columnTitle));
        System.out.println(excelSheetColumnNumber.titleToNumber3(columnTitle));
    }
}
