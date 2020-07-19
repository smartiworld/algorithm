package com.iworld.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/ 438
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * @Autor iworld
 * @Date 2020-07-07 15:16
 */
public class FindAnagramsString {

    public static void main(String[] args) {
        FindAnagramsString findAnagramsString = new FindAnagramsString();
        String s = "abab";
        String t = "ab";
        List<Integer> allAnagrams = findAnagramsString.findAllAnagrams(s, t);
        System.out.println("allAnagrams==" + allAnagrams);
    }
    /**
     * 滑动窗口
     * @param s
     * @param t
     * @return
     */
    public List<Integer> findAllAnagrams(String s, String t){
        List<Integer> anagramsPosition = new ArrayList<>();
        int left = 0;
        int rigth = 0;
        int[] matchStrCounts = new int[26];
        for (int i = 0; i < t.length(); i++) {
            matchStrCounts[t.charAt(i) - 'a'] ++;
        }
        int[] sStrCounts = new int[26];
        while (left <= s.length() - t.length()){
            if (rigth - left < t.length()){
                sStrCounts[s.charAt(rigth ++) - 'a'] ++;
            }else if(rigth - left == t.length()){
                boolean isMatch = true;
                for (int i = 0; i < matchStrCounts.length; i++) {
                    if (sStrCounts[i] < matchStrCounts[i]){
                        isMatch = false;
                        break;
                    }
                }
                if(isMatch){
                    anagramsPosition.add(left);
                }
                sStrCounts[s.charAt(left ++) - 'a'] --;
            }
        }
        return anagramsPosition;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anarams = new ArrayList<>();
        if (s.length() < p.length()) return anarams;  // 排除不可能的情况
        int[] matchStrCounts = new int[26];
        for (int i = 0; i < p.length(); i++) matchStrCounts[p.charAt(i) - 'a']++;
        int[] sStrCounts = new int[26];
        int left = 0, right = 0;   // [l, r]区间是我们需要判定的区间，但是只需要扫描频率数组即可。
        while (left <= s.length() - p.length()) {
            //窗口大小<p.size()，扩大窗口
            if (right - left + 1 <= p.length()) {
                sStrCounts[s.charAt(right ++) - 'a']++; continue;
            }
            int i;
            for (i = 0; i < 26 && sStrCounts[i] == matchStrCounts[i]; i++);
            if (i == 26) anarams.add(left);  // 符合条件
            sStrCounts[s.charAt(left ++) - 'a']--; // 缩小窗口
        }
        return anarams;
    }
}
