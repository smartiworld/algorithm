package com.iworld.algorithm.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 字符串递归
 * @date 2022/5/5 21:19
 */
public class StringRecursion {
    
    /**
     * 获取一个字符串所有的子字符串
     * 子串，次序不变  不能间断
     * @param str
     * @return
     */
    public static List<String> getAllSubStr(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        subStrRecursion2(0, str.toCharArray(), "", result);
        return result;
    }
    
    private static void subStrRecursion(int index, char[] chars, String str, List<String> result) {
        if (index == chars.length) {
            result.add(str);
            return ;
        }
        for (int i = index; i < chars.length; i++) {
            subStrRecursion(index + 1, chars, str + chars[i], result);
        }
    }
    
    private static void subStrRecursion2(int index, char[] chars, String str, List<String> result) {
        String s = "";
        for (int i = 0; i < chars.length; i++) {
            s = s + chars[i];
            result.add(s);
            for (int j = i + 1; j < chars.length; j++) {
                s = s + chars[j];
                result.add(s);
            }
            s = "";
        }
    }
    
    /**
     * 获取一个字符串所有的子序列
     * 子序列：顺序一致 可以不连续的字符串
     * @param str
     * @return
     */
    public static List<String> getAllSubSequence(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        subSequenceRecursion(0, str.toCharArray(), "", result);
        return result;
    }
    
    /**
     * 递归拼接子序列
     * @param index  当前来到的位置
     * @param chars  当前字符串字符数组 不会变化
     * @param str    拼接结果
     * @param result 结果集合
     */
    private static void subSequenceRecursion(int index, char[] chars, String str, List<String> result) {
        if (index == chars.length) {
            result.add(str);
            return ;
        }
        // 不选择当前位置字符所拼接的序列 传入str 还是使用str
        subSequenceRecursion(index + 1, chars, str, result);
        // 选择当前位置字符所拼接的序列  传入str拼接当前位置字符str + chars[index]
        subSequenceRecursion(index + 1, chars, str + chars[index], result);
    }
    
    /**
     * 获取一个字符串所有字面值不重复的子序列
     * 子序列：顺序一致 可以不连续的字符串
     * @param str
     * @return
     */
    public static HashSet<String> getAllNoSameSubSequence(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        HashSet<String> result = new HashSet<>();
        noSameSubSequenceRecursion(0, str.toCharArray(), "", result);
        return result;
    }
    
    /**
     * 递归拼接子序列
     * @param index  当前来到的位置
     * @param chars  当前字符串字符数组 不会变化
     * @param str    拼接结果
     * @param result 结果集合
     */
    private static void noSameSubSequenceRecursion(int index, char[] chars, String str, HashSet<String> result) {
        if (index == chars.length) {
            result.add(str);
            return ;
        }
        // 不选择当前位置字符所拼接的序列 传入str 还是使用str
        noSameSubSequenceRecursion(index + 1, chars, str, result);
        // 选择当前位置字符所拼接的序列  传入str拼接当前位置字符str + chars[index]
        noSameSubSequenceRecursion(index + 1, chars, str + chars[index], result);
    }
    
    /**
     * 字符串全排列
     * 字符串字符每个位置所有字符都需要放一次
     * @param str
     */
    public static List<String> stringAllArrangement(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        stringAllArrangementRecursion(0, str.toCharArray(), result);
        return result;
    }
    
    /**
     * 递归排列字符
     * @param index   当前来到的index位置
     * @param chars   当前处理的字符数组
     * @param result  字符串结果集
     */
    private static void stringAllArrangementRecursion(int index, char[] chars, List<String> result) {
        if (index == chars.length) {
            result.add(String.valueOf(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            // 交换当前位置字符
            swap(chars, i, index);
            stringAllArrangementRecursion(index + 1, chars, result);
            // 恢复现场
            swap(chars, i, index);
        }
    }
    
    /**
     * 字符串全排列  不重复
     * 字符串字符每个位置所有字符都需要放一次
     * @param str
     */
    public static List<String> noSameStringAllArrangement(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        noSameStringAllArrangementRecursion(0, str.toCharArray(), result);
        return result;
    }
    
    /**
     * 递归排列字符 字面值不等 不重复  分支限界
     * @param index   当前来到的index位置
     * @param chars   当前处理的字符数组
     * @param result  字符串结果集
     */
    private static void noSameStringAllArrangementRecursion(int index, char[] chars, List<String> result) {
        if (index == chars.length) {
            result.add(String.valueOf(chars));
            return;
        }
        // 位置字典  只针对单次index
        boolean[] dictionary = new boolean[26];
        // index 以后的位置都可能要和index位置交换
        for (int i = index; i < chars.length; i++) {
            // 如果是相同字符已经和index交换过位置了  后面相同字符就不需要再交换位置
            if (!dictionary[chars[i] - 'a']) {
                dictionary[chars[i] - 'a'] = true;
                // 交换当前位置字符
                swap(chars, i, index);
                noSameStringAllArrangementRecursion(index + 1, chars, result);
                // 恢复现场
                swap(chars, i, index);
            }
        }
    }
    
    private static void swap(char[] chars, int i, int j) {
        if (i == j) {
            return ;
        }
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
    
    public static void main(String[] args) {
        String str = "aab";
        List<String> abc = getAllSubStr(str);
        System.out.println(abc);
        List<String> abc1 = getAllSubSequence(str);
        System.out.println(abc1);
        List<String> abc2 = stringAllArrangement(str);
        System.out.println(abc2);
        List<String> abc3 = noSameStringAllArrangement(str);
        System.out.println(abc3);
    }
}
