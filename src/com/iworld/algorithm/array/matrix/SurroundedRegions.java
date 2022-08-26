package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 130. Surrounded Regions   Medium
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example 1:
 *
 *
 * Input: board = [['X',"X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Notice that an 'O' should not be flipped if:
 * - It is on the border, or
 * - It is adjacent to an 'O' that should not be flipped.
 * The bottom 'O' is on the border, so it is not flipped.
 * The other three 'O' form a surrounded region, so they are flipped.
 * Example 2:
 *
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 * https://leetcode.com/problems/surrounded-regions/
 * @date 2022/8/25 19:49
 */
public class SurroundedRegions {
    
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    char mark = process(board, i, j) ? 'T' : 'F';
                    board[i][j] = mark;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'T' || board[i][j] == 'F') {
                    boolean changeStatus = board[i][j] == 'T';
                    board[i][j] = '.';
                    change(board, i, j, changeStatus);
                }
            }
        }
    }
    
    private boolean process(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j ==board[0].length) {
            return false;
        }
        if (board[i][j] == 'O') {
            // 不修改O就会死循环
            board[i][j] = '.';
            boolean b1 = process(board, i + 1, j);
            boolean b2 = process(board, i - 1, j);
            boolean b3 = process(board, i, j + 1);
            boolean b4 = process(board, i, j - 1);
            return b1 && b2 && b3 && b4;
        }
        return true;
    }
    
    private void change(char[][] board, int i, int j, boolean changeStatus) {
        if (i < 0 || i == board.length || j < 0 || j ==board[0].length) {
            return ;
        }
        if (board[i][j] == '.') {
            board[i][j] = changeStatus ? 'X' : 'O';
            change(board, i + 1, j, changeStatus);
            change(board, i - 1, j, changeStatus);
            change(board, i, j + 1, changeStatus);
            change(board, i, j - 1, changeStatus);
        }
    }
    
    /**
     * 边界向内感染
     * @param board
     */
    public void solve2(char[][] board) {
        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i < r; i++) {
            // 左边
            if (board[i][0] == 'O') {
                infect(board, i, 0);
            }
            // 右边
            if (board[i][c - 1] == 'O') {
                infect(board, i, c - 1);
            }
        }
        for (int j = 0; j < c; j++) {
            // 上边
            if (board[0][j] == 'O') {
                infect(board, 0, j);
            }
            // 下边
            if (board[r - 1][j] == 'O') {
                infect(board, r - 1, j);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
               if (board[i][j] == 'O') {
                   board[i][j] = 'X';
               }
               if (board[i][j] == 'F') {
                   board[i][j] = 'O';
               }
            }
        }
    }
    
    private void infect(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return ;
        }
        if (board[i][j] == 'O') {
            board[i][j] = 'F';
            infect(board, i + 1, j);
            infect(board, i - 1, j);
            infect(board, i, j - 1);
            infect(board, i, j + 1);
        }
    }
    
    
    public static void main(String[] args) {
        //char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        //char[][] board = {{'O','O','O','O','X','X'},{'O','O','O','O','O','O'},{'O','X','O','X','O','O'},{'O','X','O','O','X','O'},{'O','X','O','X','O','O'},{'O','X','O','O','O','O'}};
        //char[][] board2 = {{'O','O','O','O','X','X'},{'O','O','O','O','O','O'},{'O','X','O','X','O','O'},{'O','X','O','O','X','O'},{'O','X','O','X','O','O'},{'O','X','O','O','O','O'}};
        char[][] board = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        char[][] board2 = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solve(board);
        surroundedRegions.solve2(board2);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < board2.length; i++) {
            for (int j = 0; j < board2[0].length; j++) {
                System.out.print(board2[i][j] + ",");
            }
            System.out.println();
        }
    }
}
