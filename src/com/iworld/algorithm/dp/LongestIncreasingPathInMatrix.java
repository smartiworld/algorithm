package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 329. Longest Increasing Path in a Matrix
 * Hard
 * 7291
 * 111
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * @date 2022/10/17 15:36
 */
public class LongestIncreasingPathInMatrix {
    /**
     * 暴力解
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(process(matrix, i, j), ans);
            }
        }
        return ans;
    }
    
    private int process(int[][] matrix, int i, int j) {
        int ans = 1;
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            ans = process(matrix, i + 1, j) + 1;
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            ans = Math.max(process(matrix, i - 1, j) + 1, ans);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            ans = Math.max(process(matrix, i, j + 1) + 1, ans);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            ans = Math.max(process(matrix, i, j - 1) + 1, ans);
        }
        return ans;
    }
    
    /**
     * 记忆化搜索
     * @param matrix
     * @return
     */
    public int longestIncreasingPath2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(process(matrix, i, j, dp), ans);
            }
        }
        return ans;
    }
    
    private int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int ans = 1;
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            ans = process(matrix, i + 1, j, dp) + 1;
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            ans = Math.max(process(matrix, i - 1, j, dp) + 1, ans);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            ans = Math.max(process(matrix, i, j + 1, dp) + 1, ans);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            ans = Math.max(process(matrix, i, j - 1, dp) + 1, ans);
        }
        dp[i][j] = ans;
        return ans;
    }
    
    public static void main(String[] args) {
        LongestIncreasingPathInMatrix longestIncreasingPathInMatrix = new LongestIncreasingPathInMatrix();
        int[][] matrix = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(longestIncreasingPathInMatrix.longestIncreasingPath(matrix));
        System.out.println(longestIncreasingPathInMatrix.longestIncreasingPath2(matrix));
    }
}
