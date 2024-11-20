package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 6.zigzag-conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * @date 2024/10/30 19:43
 */
public class ZigzagConversion {
    
    public String convert(String s, int numRows) {
        if (s == null || s.length() <= numRows || numRows == 1) {
            return s;
        }
        int row = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (row < numRows) {
            int index = row;
            while (index < s.length()) {
                stringBuilder.append(s.charAt(index));
                if (row > 0 && row < numRows - 1 && index + 2 * (numRows - row - 1) < s.length()) {
                    stringBuilder.append(s.charAt(index + 2 * (numRows - row - 1)));
                }
                index = index + 2 * (numRows - 1);
            }
            row++;
        }
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        ZigzagConversion zigzagConversion = new ZigzagConversion();
        String exceptResult = "PINALSIGYAHRPI";
        System.out.println(zigzagConversion.convert(s, numRows));
        System.out.println(zigzagConversion.convert(s, numRows).equals(exceptResult));
    }
}
