package com.iworld.algorithm.tree.prefix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 208. Implement Trie (Prefix Tree)
 * Medium
 * 8241
 * 102
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store
 * and retrieve keys in a dataset of strings. There are various applications of this data structure,
 * such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true
 * if the string word is in the trie (i.e., was inserted before),
 * and false otherwise.
 * boolean startsWith(String prefix) Returns true
 * if there is a previously inserted string word that has the prefix prefix,
 * and false otherwise.
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * @date 2022/9/28 20:56
 */
public class ImplementTriePrefixTree {
    
    public static class Trie {
        
        TriePrefixTree root;
        boolean end;
        
        public Trie() {
            root = new TriePrefixTree();
            end = false;
        }
    
        public void insert(String word) {
            char[] chars = word.toCharArray();
            TriePrefixTree cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new TriePrefixTree();
                }
                cur = cur.nexts[index];
            }
            cur.end = true;
        }
    
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TriePrefixTree cur = root;
            int i = 0;
            for (;i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    break;
                }
                cur = cur.nexts[index];
            }
            return cur.end && i == chars.length;
        }
    
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            TriePrefixTree cur = root;
            int i = 0;
            for (;i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    break;
                }
                cur = cur.nexts[index];
            }
            return i == chars.length;
        }
        
        public static class TriePrefixTree {
            public TriePrefixTree[] nexts;
            public boolean end;
            
            public TriePrefixTree() {
                nexts = new TriePrefixTree[26];
                end = false;
            }
        }
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
        System.out.println(trie.search("appl"));
    }
}
