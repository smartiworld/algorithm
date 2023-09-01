package com.iworld.algorithm.array.ac;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gq.cai
 * @version 1.0
 * @description AC自动机  一个文章中匹配给定多个字符串
 * 1.将匹配数组创建前缀树
 * 2.创建fail指针
 * 2.1首层节点fail指向根节点
 * @date 2022/11/3 20:11
 */
public class ACAutomaton {
    
    private Trie root;
    
    public static class Trie {
        private char c;
        private Trie[] nexts;
        private String end;
        
        public Trie() {
            nexts = new Trie[26];
        }
        public Trie(char c) {
            this.c = c;
            nexts = new Trie[26];
        }
    }
    
    public ACAutomaton() {
        root = new Trie();
    }
    
    public void insert(String str) {
        if (str == null) {
            return ;
        }
        char[] chars = str.toCharArray();
        int n = chars.length;
        Trie cur = root;
        for (int i = 0; i < n; i++) {
            int index = chars[i] - 'a';
            Trie trie = cur.nexts[index];
            if (trie == null) {
                trie = new Trie(chars[i]);
                cur.nexts[index] = trie;
            }
            cur = trie;
        }
        cur.end = str;
    }
    
    /**
     * 构造fail指针
     */
    private void build() {
        Queue<Trie> queue = new LinkedList<>();
        queue.add(root);
        Trie cur = null;
        Trie cfail = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            
            for (int i = 0; i < 26; i++) {
            
            }
        }
    }
}
