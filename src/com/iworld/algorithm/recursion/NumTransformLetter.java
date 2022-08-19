package com.iworld.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 数字转换成字母
 * 给定一个数字类型的字符串 1对应A，2对应B，3对应C 等等
 * 111 可以转化成 AAA KA AK
 * @date 2022/5/7 16:12
 */
public class NumTransformLetter {
    
    /**
     * 给定一个字符串 能有多少种转换
     * @param str
     * @return
     */
    public static int strNumTransformLetterCount(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(0, str.toCharArray());
    }
    
    private static int process(int index, char[] chars) {
        if (index == chars.length) {
            return 1;
        }
        // 当前位置为0 无解 只能是和前面字符拼接一块使用
        if (chars[index] == '0') {
            return 0;
        }
        // 处理当前位置对应一个字符
        int oneBitCount = process(index + 1, chars);
        // 如果当前位置是1 可以连续下一位 转换一个字符
        if (chars[index] == '1' && index + 2 <= chars.length) {
            oneBitCount = oneBitCount + process(index + 2, chars);
        }
        // 如果当前位置是2 并且下一位是1-6中间数字 可以连续下一位 转换一个字符
        if (chars[index] == '2' && index + 2 <= chars.length
                && chars[index + 1] >= '0' && chars[index + 1] < '7') {
            oneBitCount = oneBitCount + process(index + 2, chars);
        }
        return oneBitCount;
    }
    
    /**
     * 给定一个字符串 能有多少种转换
     * @param str
     * @return
     */
    public static List<String> strNumTransformLetter(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charMap = new char[27];
        int star = 65;
        for (int i = 1; i < charMap.length; i++) {
            charMap[i] = (char)star ++;
        }
        List<String> result = new ArrayList<>();
        String everyResult = "";
        process2(0, str.toCharArray(), result, everyResult, charMap);
        return result;
    }
    
    private static int process2(int index, char[] chars, List<String> result, String everyResult, char[] charMap) {
        if (index == chars.length) {
            result.add(everyResult);
            return 1;
        }
        // 当前位置为0 无解 只能是和前面字符拼接一块使用
        if (chars[index] == '0') {
            return 0;
        }
        String newStr = everyResult + charMap[Integer.parseInt(String.valueOf(chars[index]))];
        int oneBitCount = process2(index + 1, chars, result, newStr, charMap);
        // 如果当前位置是2 并且下一位是1-6中间数字 可以连续下一位 转换一个字符
        if (chars[index] == '1' && index + 2 <= chars.length) {
            newStr = everyResult + charMap[Integer.parseInt(chars[index] + String.valueOf(chars[index + 1]))];
            oneBitCount = oneBitCount + process2(index + 2, chars, result, newStr, charMap);
        }
        // 如果当前位置是2 并且下一位是1-6中间数字 可以连续下一位 转换一个字符
        if (chars[index] == '2' && index + 2 <= chars.length
                && chars[index + 1] >= '0' && chars[index + 1] < '7') {
            newStr = everyResult + charMap[Integer.parseInt(chars[index] + String.valueOf(chars[index + 1]))];
            oneBitCount = oneBitCount + process2(index + 2, chars, result, newStr, charMap);
        }
        return oneBitCount;
    }
    
    public static void main(String[] args) {
        String a = "111111111111111111111111111111111111111111111";
        System.out.println(strNumTransformLetterCount(a));
        System.out.println(strNumTransformLetter(a));
    }
}
