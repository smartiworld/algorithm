package com.iworld.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 140. Word Break II
 * Given a string s and a dictionary of strings wordDict,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * https://leetcode.com/problems/word-break-ii/
 * @date 2022/9/26 13:04
 */
public class WordBreakII {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        process(s, 0, new HashSet<>(wordDict), result, new StringBuilder());
        return result;
    }
    
    private void process(String s, int index, HashSet<String> wordDict, List<String> result, StringBuilder sb) {
        if (index == s.length()) {
            result.add(sb.toString());
            return ;
        }
        for (int i = index; i < s.length(); i++) {
            if (wordDict.contains(s.substring(index, i + 1))) {
                String substring = s.substring(index, i + 1);
                sb.append(substring);
                if (i + 1 < s.length()) {
                    sb.append(" ");
                }
                process(s, i + 1, wordDict, result, sb);
                if (i + 1 < s.length()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                int remove = sb.length() - substring.length();
                sb.delete(remove, sb.length());
            }
        }
    }
    
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        process2(s, 0, new HashSet<>(wordDict), result, new LinkedList<>());
        return result;
    }
    
    private void process2(String s, int index, HashSet<String> wordDict, List<String> result, LinkedList<String> queue) {
        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iterator = queue.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                sb.append(next);
            }
            result.add(sb.toString());
            return ;
        }
        for (int i = index; i < s.length(); i++) {
            if (wordDict.contains(s.substring(index, i + 1))) {
                String substring = s.substring(index, i + 1);
                queue.addLast(substring);
                if (i + 1 < s.length()) {
                    queue.addLast(" ");
                }
                process2(s, i + 1, wordDict, result, queue);
                if (i + 1 < s.length()) {
                    queue.pollFirst();
                }
                queue.pollFirst();
            }
        }
    }
    
    public List<String> wordBreak3(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        WordBreakIIPrefixTree tree = new WordBreakIIPrefixTree();
        for (String str : wordDict) {
            WordBreakIIPrefixTree cur = tree;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (cur.nexts[chars[i] - 'a'] == null) {
                    cur.nexts[chars[i] - 'a'] = new WordBreakIIPrefixTree();
                }
                cur = cur.nexts[chars[i] - 'a'];
            }
            cur.path = str;
            cur.end = true;
        }
        boolean[] dp = getDp(s, tree);
        process3(s.toCharArray(), 0, dp, result, new LinkedList<>(), tree);
        return result;
    }
    
    private void process3(char[] chars, int index, boolean[] dp, List<String> result, List<String> path, WordBreakIIPrefixTree tree) {
        if (index == chars.length) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                builder.append(path.get(i) + " ");
            }
            builder.append(path.get(path.size() - 1));
            result.add(builder.toString());
        } else {
            WordBreakIIPrefixTree cur = tree;
            for (int end = index; end < chars.length; end++) {
                WordBreakIIPrefixTree node = cur.nexts[chars[end] - 'a'];
                if (node == null) {
                    break;
                }
                cur = node;
                if (cur.end && dp[end + 1]) {
                    path.add(cur.path);
                    process3(chars, end + 1, dp, result, path, tree);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    
    private boolean[] getDp(String s, WordBreakIIPrefixTree tree) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int index = n - 1; index >= 0; index--) {
            WordBreakIIPrefixTree cur = tree;
            for (int i = index; i < n; i++) {
                WordBreakIIPrefixTree next = cur.nexts[s.charAt(i) - 'a'];
                if (next == null) {
                    break;
                }
                if (next.end && dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
                cur = next;
            }
        }
        return dp;
    }
    
    public List<String> wordBreak4(String s, List<String> wordDict) {
        char[] str = s.toCharArray();
        WordBreakIIPrefixTree root = gettrie(wordDict);
        boolean[] dp = getdp2(s, root);
        ArrayList<String> path = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        process4(str, 0, root, dp, path, ans);
        return ans;
    }
    
    public void process4(char[] str, int index, WordBreakIIPrefixTree root, boolean[] dp, ArrayList<String> path,
                               List<String> ans) {
        if (index == str.length) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                builder.append(path.get(i) + " ");
            }
            builder.append(path.get(path.size() - 1));
            ans.add(builder.toString());
        } else {
            WordBreakIIPrefixTree cur = root;
            for (int end = index; end < str.length; end++) {
                int road = str[end] - 'a';
                if (cur.nexts[road] == null) {
                    break;
                }
                cur = cur.nexts[road];
                if (cur.end && dp[end + 1]) {
                    path.add(cur.path);
                    process4(str, end + 1, root, dp, path, ans);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    
    public WordBreakIIPrefixTree gettrie(List<String> wordDict) {
        WordBreakIIPrefixTree root = new WordBreakIIPrefixTree();
        for (String str : wordDict) {
            char[] chs = str.toCharArray();
            WordBreakIIPrefixTree node = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new WordBreakIIPrefixTree();
                }
                node = node.nexts[index];
            }
            node.path = str;
            node.end = true;
        }
        return root;
    }
    
    public boolean[] getdp2(String s, WordBreakIIPrefixTree root) {
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            WordBreakIIPrefixTree cur = root;
            for (int end = i; end < N; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end && dp[end + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp;
    }
    
    static class WordBreakIIPrefixTree {
        WordBreakIIPrefixTree[] nexts;
        boolean end;
        String path;
        WordBreakIIPrefixTree() {
            nexts = new WordBreakIIPrefixTree[26];
            end = false;
            path = null;
        }
    }
    
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        wordDict = Arrays.asList("cats","dog","sand","and","cat");
        WordBreakII wordBreakII = new WordBreakII();
        System.out.println(wordBreakII.wordBreak(s, wordDict));
        System.out.println(wordBreakII.wordBreak2(s, wordDict));
        System.out.println(wordBreakII.wordBreak3(s, wordDict));
        System.out.println(wordBreakII.wordBreak4(s, wordDict));
    }
}
