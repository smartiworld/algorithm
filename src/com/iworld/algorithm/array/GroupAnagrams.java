package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 49. Group Anagrams   Medium
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * https://leetcode.com/problems/group-anagrams/
 * @date 2022/8/12 10:20
 */
public class GroupAnagrams {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> strMap = new HashMap<>();
        for (String str : strs) {
            char[] cStr = str.toCharArray();
            Arrays.sort(cStr);
            String tmp = new String(cStr);
            List<String> sortStrs = strMap.get(tmp);
            if (sortStrs == null) {
                sortStrs = new ArrayList<>();
                strMap.put(tmp, sortStrs);
            }
            sortStrs.add(str);
        }
        Set<Map.Entry<String, List<String>>> entries = strMap.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            ans.add(entry.getValue());
        }
        return ans;
    }
    
    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams.groupAnagrams(strs));
    }
}
