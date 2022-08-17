package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 79. Word Search   Medium
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 * https://leetcode.com/problems/word-search/
 * @date 2022/8/17 16:12
 */
public class WordSearch {
    
    public boolean existOpt(char[][] board, String word) {
        int[] boardCharCount = new int['z' - 'A' + 1];
        int[] wordCharCount = new int['z' - 'A' + 1];
        // word 字母出现的数量
        char[] cWord = word.toCharArray();
        for (char c : cWord) {
            wordCharCount[c - 'A']++;
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                boardCharCount[board[r][c] - 'A']++;
            }
        }
        for (char c : cWord) {
            // 矩阵中字符少于目标中字符 不可能匹配出来
            if (boardCharCount[c - 'A'] < wordCharCount[c - 'A']) {
                return false;
            }
        }
        int n = cWord.length;
        // word开头字母在board的数量大于word结尾字母数量
        if (boardCharCount[cWord[0] - 'A'] > wordCharCount[cWord[n - 1] - 'A']) {
            char[] reverseWord = new char[n];
            for (int i = 0, j = n - 1; j >= 0; i++, j--) {
                reverseWord[i] = cWord[j];
            }
            cWord = reverseWord;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, cWord, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean exist(char[][] board, String word) {
        char[] cWord = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, cWord, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean process(char[][] board, int i, int j, char[] cWord, int k) {
        if (k >= cWord.length) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != cWord[k]) {
            return false;
        }
        board[i][j] = 0;
        boolean status = process(board, i + 1, j, cWord, k + 1) || process(board, i - 1, j, cWord, k + 1)
                || process(board, i, j + 1, cWord, k + 1) || process(board, i, j - 1, cWord, k + 1);
        board[i][j] = cWord[k];
        return status;
    }
    
    //[["A","B","C","E"],
    // ["S","F","C","S"],
    // ["A","D","E","E"]]
    // see
    
    int[][] dirs = new int[][] {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    
    public boolean exist1(char[][] board, String word) {
        int[] alphFreqForBoard = new int['z' - 'A' + 1];
        int[] alphFreqForWord = new int['z' - 'A' + 1];
        
        // count the alphabet occurance frequence in the word
        for (char c : word.toCharArray()) {
            alphFreqForWord[c - 'A']++;
        }
        
        // count the alphabet occurance frequence in the board
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                alphFreqForBoard[board[r][c] - 'A']++;
            }
        }
        
        for (char c : word.toCharArray()) {
            if (alphFreqForBoard[c - 'A'] < alphFreqForWord[c - 'A']) {
                return false;
            }
        }
        String tmpWord = word;
        if (alphFreqForBoard[word.charAt(0) - 'A'] > alphFreqForWord[word.charAt(word.length() - 1) - 'A']) {
            char[] reverseWord = new char[word.length()];
            for (int i = 0, j = word.length() - 1; j >= 0; i++, j--) {
                reverseWord[i] = word.charAt(j);
            }
            tmpWord = new String(reverseWord);
        }
        
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == tmpWord.charAt(0)) {
                    if (dfs(board, r, c, tmpWord, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int r, int c, String word, int level) {
        if (level == word.length() - 1) {
            return true;
        }
        char temp = board[r][c];
        board[r][c] = '#';
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] != '#' && board[nr][nc] == word.charAt(level + 1)) {
                if (dfs(board, nr, nc, word, level + 1)) {
                    board[r][c] = temp;
                    return true;
                }
            }
        }
        board[r][c] = temp;
        return false;
    }
}
