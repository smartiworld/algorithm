package com.iworld.algorithm.array.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 212. Word Search II
 * Hard
 * 6761
 * 284
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * Example 1:
 *
 *
 * Input: board = [
 * ["o","a","a","n"],
 * ["e","t","a","e"],
 * ["i","h","k","r"],
 * ["i","f","l","v"]
 * ], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 *
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 * https://leetcode.com/problems/word-search-ii/
 * 给定一个字符类型的二维数组board，和一个字符串组成的列表words。
 * 可以从board任何位置出发，每一步可以走向上、下、左、右，四个方向，
 * 但是一条路径已经走过的位置，不能重复走。
 * 返回words哪些单词可以被走出来。
 * 例子
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * @date 2022/9/14 23:07
 */
public class WordSearchII {
    
    
    public List<String> findWords(char[][] board, String[] words) {
        TriePrefixTree root = new TriePrefixTree();
        for (int i = 0; i < words.length; i++) {
            TriePrefixTree cur = root;
            char[] chars = words[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (cur.next[chars[j] - 'a'] == null) {
                    cur.next[chars[j] - 'a'] = new TriePrefixTree();
                }
                cur = cur.next[chars[j] - 'a'];
            }
            cur.word = new String(chars);
            cur.end = true;
        }
        int row = board.length;
        int col = board[0].length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                process(board, i, j, root, ans);
            }
        }
        return ans;
    }
    
    private void process(char[][] board, int r, int c, TriePrefixTree head, List<String> ans) {
        // 位置不合法
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == 0) {
            return ;
        }
        TriePrefixTree next = head.next[board[r][c] - 'a'];
        // 当前位置没有匹配
        if (next == null) {
            return;
        }
        if (next.end) {
            ans.add(next.word);
            next.end = false;
        }
        char tmp = board[r][c];
        board[r][c] = 0;
        process(board, r + 1, c, next, ans);
        process(board, r - 1, c, next, ans);
        process(board, r, c + 1, next, ans);
        process(board, r, c - 1, next, ans);
        board[r][c] = tmp;
    }
    
    public static class TriePrefixTree {
        public TriePrefixTree[] next;
        public boolean end;
        // 结尾时存入整个字符串
        public String word;
    
        public TriePrefixTree () {
            next = new TriePrefixTree[26];
            end = false;
        }
    
        @Override
        public String toString() {
            return "TriePrefixTree{" +
                    "word='" + word + '\'' +
                    '}';
        }
    }
    
    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath","pea","eat","rain","oathi","oathk","oathf","oate","oathii","oathfi","oathfii"};
        WordSearchII wordSearchII = new WordSearchII();
        System.out.println(wordSearchII.findWords(board, words));
    }
}
