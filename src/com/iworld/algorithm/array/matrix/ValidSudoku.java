package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 36. Valid Sudoku   Medium
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 * Example 1:
 *
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 * https://leetcode.com/problems/valid-sudoku/
 * @date 2022/7/6 12:44
 */
public class ValidSudoku {
    
    /**
     * 三个数组分别表示行上数字是否出现 列上数字是否出现 每一个块上数字是否出现
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int r = board.length;
        int c = board[0].length;
        int ns = 9;
        // [board.length]行数量 [9]0-8 + 1数字是否出现
        // 当前行1-9出现状态 下标为0-8 [1-9]-1;
        boolean[][] rows = new boolean[r][ns];
        boolean[][] cols = new boolean[c][ns];
        // 3*3方块 数字出现状态 一共9个块 一个块有9个数字
        boolean[][] buckets = new boolean[9][9];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] != '.') {
                    // 横着数 第一行0+0,0+1,0+2第二行3+0,3+1,3+2第三行6+0,6+1,6+2
                    int bucket = 3 * (i / 3) + j / 3;
                    if (rows[i][board[i][j] - '1'] || cols[j][board[i][j] - '1'] || buckets[bucket][board[i][j] - '1']) {
                        return false;
                    }
                    rows[i][board[i][j] - '1'] = true;
                    cols[j][board[i][j] - '1'] = true;
                    buckets[bucket][board[i][j] - '1'] = true;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        char[][] board =
                {{'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};
        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.isValidSudoku(board));
    }
    
}
