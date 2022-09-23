package com.iworld.algorithm.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 139. Word Break
 * Medium
 * 12242
 * 524
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * https://leetcode.com/problems/word-break/
 * @date 2022/9/21 12:46
 */
public class WordBreak {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        return process(s, 0, new HashSet<>(wordDict)) != 0;
    }
    
    private int process(String s, int index, Set<String> wordDict) {
        if (index >= s.length()) {
            return 1;
        }
        int ans = 0;
        for (int i = index; i < s.length(); i++) {
            if (wordDict.contains(s.substring(index, i + 1))) {
                ans += process(s, i + 1, wordDict);
            }
        }
        return ans;
    }
    
    public boolean wordBreakDp(String s, List<String> wordDict) {
        Set<String> hSet = new HashSet<>(wordDict);
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int ans = 0;
            for (int index = i; index < n; index++) {
                if (hSet.contains(s.substring(i, index + 1))) {
                    ans += dp[index + 1];
                }
            }
            dp[i] = ans;
        }
        return dp[0] != 0;
    }
    
    public boolean wordBreakDpUsePrefixTree(String s, List<String> wordDict) {
        PrefixTree root = new PrefixTree();
        for (String str : wordDict) {
            PrefixTree cur = root;
            char[] chars = str.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (cur.nexts[chars[j] - 'a'] == null) {
                    cur.nexts[chars[j] - 'a'] = new PrefixTree();
                }
                cur = cur.nexts[chars[j] - 'a'];
            }
            cur.end = true;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int ans = 0;
            PrefixTree cur = root;
            for (int index = i; index < n; index++) {
                PrefixTree next = cur.nexts[s.charAt(index) - 'a'];
                if (next == null) {
                    break;
                }
                if (next.end) {
                    ans += dp[index + 1];
                }
                cur = next;
            }
            dp[i] = ans;
        }
        return dp[0] != 0;
    }
    
    static class PrefixTree {
        boolean end;
        PrefixTree[] nexts;
        PrefixTree() {
            nexts = new PrefixTree[26];
            end = false;
        }
    }
    
    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak(s, wordDict));
        System.out.println(wordBreak.wordBreakDp(s, wordDict));
        System.out.println(wordBreak.wordBreakDpUsePrefixTree(s, wordDict));
    }
}
