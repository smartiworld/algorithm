package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 37. Sudoku Solver
 * Hard
 * 6.9K
 * 186
 * Companies
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 * Example 1:
 *
 * Input: board = [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]]
 * Output: [
 * ["5","3","4","6","7","8","9","1","2"],
 * ["6","7","2","1","9","5","3","4","8"],
 * ["1","9","8","3","4","2","5","6","7"],
 * ["8","5","9","7","6","1","4","2","3"],
 * ["4","2","6","8","5","3","7","9","1"],
 * ["7","1","3","9","2","4","8","5","6"],
 * ["9","6","1","5","3","7","2","8","4"],
 * ["2","8","7","4","1","9","6","3","5"],
 * ["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 * https://leetcode.com/problems/sudoku-solver/
 * @date 2022/11/22 23:16
 */
public class SudokuSolver {
    
    public void solveSudoku(char[][] board) {
        int n = board.length;
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] buckets = new boolean[9][9];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    rows[i][board[i][j] - '1'] = true;
                    cols[j][board[i][j] - '1'] = true;
                    int bucket = 3 * (i / 3) + (j / 3);
                    buckets[bucket][board[i][j] - '1'] = true;
                }
            }
        }
        process(board, 0, 0, rows, cols, buckets);
    }
    
    private boolean process(char[][] board, int row, int col, boolean[][] rows, boolean[][] cols, boolean[][] buckets) {
        if (row == 9) {
            return true;
        }
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        if (board[row][col] == '.') {
            for (int k = 0; k < 9; k++) {
                int bucket = 3 * (row / 3) + (col / 3);
                if (rows[row][k] || cols[col][k] || buckets[bucket][k]) {
                    continue;
                }
                board[row][col] = (char)(k + '1');
                rows[row][k] = true;
                cols[col][k] = true;
                buckets[bucket][k] = true;
                if (process(board, nextRow, nextCol, rows, cols, buckets)) {
                    return true;
                }
                board[row][col] = '.';
                rows[row][k] = false;
                cols[col][k] = false;
                buckets[bucket][k] = false;
            }
            return false;
        } else {
            return process(board, nextRow, nextCol, rows, cols, buckets);
        }
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
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
    }
}