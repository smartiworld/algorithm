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
     * 获取一个字符串所有的子字符串  未实现
     * 子串，次序不变  不能间断
     * @param str
     * @return
     */
    public static List<String> getAllSubStr(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        subStrRecursion(0, str.toCharArray(), "", result, false, 0);
        return result;
    }
    
    /**
     * 递归处理拼接字符串
     * 字符两种状态
     * 1.选择
     * 2.不选择 不选择得时候需要看前字符选择状态
     * 2.1.如果前字符是已选择状态 则需要停止当前递归
     * 2.2.如果前面字符未选择状态 继续
     * 当前字符和前面字符选择状态
     * @param index   来到字符的位置
     * @param chars   字符char数组  不变
     * @param str      已经拼接的字符串
     * @param result   所有结果
     * @param isEnd    告诉后面是否需要结束
     * @param type     调用类型 当前位置需要知道前面字符位置 选择状态 1未选择 2选择
     */
    private static void subStrRecursion(int index, char[] chars, String str, List<String> result, boolean isEnd, int type) {
        if (index == chars.length || isEnd) {
            result.add(str);
            return ;
        }
        if (index > 0 && type == 2) {
            subStrRecursion(index + 1, chars, str, result, true, 1);
        } else {
            subStrRecursion(index + 1, chars, str, result, false, 1);
        }
        subStrRecursion(index + 1, chars, str + chars[index], result, false, 2);
    }
    
    /**
     * 获取一个字符串所有的子字符串
     * 子串，次序不变  不能间断
     * @param str
     * @return
     */
    public static List<String> getAllSubStr2(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        subStrRecursion2(str.toCharArray(), result);
        return result;
    }
    
    private static void subStrRecursion2(char[] chars, List<String> result) {
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
     * 来到index位置 0~index-1位置处理完毕
     * 然后将index后面位置字符和index交换位置排列 执行完后再交换位置 恢复现场
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
        String str = "abcd";
        System.out.println(getAllSubStr(str));
        System.out.println(getAllSubStr2(str));
        List<String> abc1 = getAllSubSequence(str);
        System.out.println(abc1);
        System.out.println(getAllNoSameSubSequence(str));
        List<String> abc2 = stringAllArrangement(str);
        System.out.println(abc2);
        List<String> abc3 = noSameStringAllArrangement(str);
        System.out.println(abc3);
    }
}
