package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 289. Game of Life
 * Medium
 * 5226
 * 462
 * According to Wikipedia's article: "The Game of Life, also known simply as Life,
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells,
 * where each cell has an initial state: live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * 任何少于两个活邻居的活细胞都会死掉 好像由于人口不足造成
 * Any live cell with two or three live neighbors lives on to the next generation.
 * 任何有两个或者三个活邻居的活细胞都可以活到下一代
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * 任何超过三个活邻居的活细胞都会死掉 好像人口过剩一样
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * 任何通过三个活邻居的死细胞都会变成活着状态 像通过繁殖
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 * Example 1:
 *
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * Example 2:
 *
 *
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 *
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array.
 * In principle, the board is infinite,
 * which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 * https://leetcode.com/problems/game-of-life/
 * @date 2022/10/9 13:19
 */
public class GameOfLife {
    
    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int count = neighborCount(board, i, j);
                // 当前位置为1的时候 需要修改原值时将1设置为0 中间态3
                if ((board[i][j] & 1) == 1 && (count < 2 || count > 3)) {
                    setBoardValue(board, i, j);
                }
                // 当前位置为0的时候 三个活邻居 当前需要活 需要修改原值时将0设置为1 中间态2
                if ((board[i][j] & 1) == 0 && count == 3) {
                    setBoardValue(board, i, j);
                }
            }
        }
        // 修改要改的值 2是0-1 3是1-0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }
    
    public void gameOfLife2(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int count = neighborCount(board, i, j);
                //活的时候设置
                if (count == 3 || (count == 2 && board[i][j] == 1)) {
                    setBoardValue(board, i, j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }
    
    private int neighborCount(int[][] board, int i, int j) {
        int count = 0;
        count += isLive(board, i - 1, j) ? 1 : 0;
        count += isLive(board, i - 1, j + 1) ? 1 : 0;
        count += isLive(board, i, j + 1) ? 1 : 0;
        count += isLive(board, i + 1, j + 1) ? 1 : 0;
        count += isLive(board, i + 1, j) ? 1 : 0;
        count += isLive(board, i + 1, j - 1) ? 1 : 0;
        count += isLive(board, i, j - 1) ? 1 : 0;
        count += isLive(board, i - 1, j - 1) ? 1 : 0;
        return count;
    }
    
    private boolean isLive(int[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length && (board[i][j] & 1) == 1;
    }
    
    private void setBoardValue(int[][] board, int i, int j) {
        board[i][j] |= 2;
    }
}
