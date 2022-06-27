package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Constraints:
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 * https://leetcode.com/problems/longest-common-prefix/
 * @date 2022/6/27 17:41
 */
public class LongestCommonPrefix {
    
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        if (prefix.isEmpty()) {
            return "";
        }
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int s = 0;
            while (s < str.length() && s < prefix.length() && str.charAt(s) == prefix.charAt(s)) {
                s++;
            }
            if (s == 0) {
                return "";
            }
            prefix = prefix.substring(0, s);
        }
        return prefix;
    }
    
    public String longestCommonPrefix2(String[] strs) {
        if(strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++){
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
    
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix2(strs));
    }
}
