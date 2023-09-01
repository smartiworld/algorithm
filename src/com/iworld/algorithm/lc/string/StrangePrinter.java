package com.iworld.algorithm.lc.string;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/**
 * @author gq.cai
 * @version 1.0
 * @description 664. Strange Printer
 * Hard
 * 1.1K
 * 97
 * There is a strange printer with the following two special properties:
 *
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
 * Given a string s, return the minimum number of turns the printer needed to print it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of lowercase English letters.
 * https://leetcode.com/problems/strange-printer/
 * @date 2023/6/8 15:50
 */
public class StrangePrinter {
    /**
     * 范围上尝试
     * f(l,r)=f(l,k) + f(k+1,r) - (chars[k] == chars[k + 1] ? 1 : 0)
     * 将范围问题交子问题去处理 如果中间分裂处前后字符相同则将结果-1
     * 尝试方式 每一个位置都尝试反转一次
     * 范围尝试对角线开始尝试 左下角没有结果
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[][] dp = new int[len][len];
        dp[len - 1][len - 1] = 1;
        for (int i = 0; i < len - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 1 : 2;
        }
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                int min = j - i + 1;
                for (int k = i; k <= j; k++) {
                
                }
                dp[i][j] = min;
            }
        }
        return 0;
    }
    
    private Integer a = 1;
    
    private String name = "a";
    
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = new Date();
        date.setHours(9);
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
    
    public static <T> TreeMap<String, String> convertToStringParam(T t) throws IllegalAccessException {
        TreeMap<String, String> result = new TreeMap<>();
        Class<?> clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (Field field : declaredFields) {
            result.put(field.getName(), field.get(t).toString());
        }
        return result;
    }
}
