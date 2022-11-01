package com.iworld.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 51. N-Queens
 * Hard
 * 8571
 * 192
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 * Constraints:
 *
 * 1 <= n <= 9
 * https://leetcode.com/problems/n-queens/
 * @date 2022/10/20 22:13
 */
public class NQueens {
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        // 拜访皇后的位置 下标代表行 值代表列
        int[] record = new int[n];
        process(record, 0, ans);
        return ans;
    }
    
    private void process(int[] record, int row, List<List<String>> ans) {
        int n = record.length;
        if (row == n) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < record.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < record.length; j++) {
                    if (j == record[i]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                result.add(sb.toString());
            }
            ans.add(result);
            return ;
        }
        for (int col = 0; col < n; col++) {
            if (valid(row, col, record)) {
                // 当前行列可以摆放  将当前行列放入皇后
                // valid只校验同列和对角线 不会同行 所以不用清理现场
                record[row] = col;
                process(record, row + 1, ans);
            }
        }
    }
    
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] position = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                position[i][j] = '.';
            }
        }
        // 拜访皇后的位置 下标代表行 值代表列
        int[] record = new int[n];
        process2(record, 0, ans, position);
        return ans;
    }
    
    private void process2(int[] record, int row, List<List<String>> ans, char[][] position) {
        int n = record.length;
        if (row == n) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                result.add(new String(position[i]));
            }
            ans.add(result);
            return ;
        }
        for (int col = 0; col < n; col++) {
            if (valid(row, col, record)) {
                // 当前行列可以摆放  将当前行列放入皇后
                // valid只校验同列和对角线 不会同行 所以不用清理现场
                record[row] = col;
                position[row][col] = 'Q';
                process2(record, row + 1, ans, position);
                position[row][col] = '.';
            }
        }
    }
    
    /**
     * 校验当前位置是否可以拜放皇后
     * @param row       校验的行
     * @param col       校验的列
     * @param record
     * @return
     */
    private boolean valid(int row, int col, int[] record) {
        for (int i = 0; i < row; i++) {
            // record[i] == col 所有行同列
            // Math.abs(row - i) == Math.abs(col - record[i]) 对角线
            if (record[i] == col || Math.abs(row - i) == Math.abs(col - record[i])) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * better
     * @param n
     * @return
     */
    public List<List<String>> solveNQueensUseBit(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] position = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                position[i][j] = '.';
            }
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        process(limit, 0, 0, 0, ans, position, 0);
        return ans;
    }
    
    private void process(int limit, int rowLimit, int leftLimit, int rightLimit, List<List<String>> ans, char[][] position, int row) {
        int n = position.length;
        if (rowLimit == limit) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                result.add(new String(position[i]));
            }
            ans.add(result);
            return ;
        }
        // 所有1不可以放皇后
        int allLimit = rowLimit | leftLimit | rightLimit;
        // 所有1位置可以放皇后 存在32位头部为1的干扰情况
        int allAllow = ~allLimit;
        // 1所有可以允许放皇后点
        // limit只有实际位上为1 其他多余位为0
        // 截取掉左边多余位置的1置为0表示不可以放皇后 实际位置1的为可以放皇后
        int allBitAllow = limit & allAllow;
        while (allBitAllow > 0) {
            // 找出最右侧的1 放入当前位置皇后
            int mostRightOne = allBitAllow & (-allBitAllow);
            // 从允许位置移除掉
            allBitAllow = allBitAllow ^ mostRightOne;
            // 收集答案
            position[row][getColIndex(mostRightOne, n)] = 'Q';
            process(limit, rowLimit | mostRightOne, (leftLimit | mostRightOne) << 1, (rightLimit | mostRightOne) >> 1, ans, position, row + 1);
            // 深度 需还原初始位置
            position[row][getColIndex(mostRightOne, n)] = '.';
        }
    }
    
    /**
     * 获取列的索引 当前位运算是从右向左 数组中列 从左向右
     * 计算mostRightOne从右边数第几位置
     * @param mostRightOne
     * @param col
     * @return
     */
    private int getColIndex(int mostRightOne, int col) {
        int index = 0;
        while (mostRightOne > 0) {
            mostRightOne = mostRightOne >> 1;
            index++;
        }
        return col - index;
    }
    
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        int n = 4;
        System.out.println(nQueens.solveNQueens(n));
        System.out.println(nQueens.solveNQueens2(n));
        System.out.println(nQueens.solveNQueensUseBit(n));
    }
}
