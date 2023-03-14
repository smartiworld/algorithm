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
 *
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class01/Code01_LongestIncreasingPath.java
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
    
    public static int longestIncreasingPathDp(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[m.length][m[0].length];
        // dp[i][j] (i,j)出发，走出的最长链长度
        int max = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                // 每一个(i,j)位置出发，都尝试
                max = Math.max(max, maxIncrease(m, dp, i + 1, j, m[i][j]) + 1);
                max = Math.max(max, maxIncrease(m, dp, i, j + 1, m[i][j]) + 1);
                max = Math.max(max, maxIncrease(m, dp, i - 1, j, m[i][j]) + 1);
                max = Math.max(max, maxIncrease(m, dp, i, j - 1, m[i][j]) + 1);
            }
            
        }
        return max;
    }
    
    // 来到的当前位置是i,j位置
    // p 上一步值是什么
    // 从(i,j)位置出发，走出的最长链，要求：上一步是可以迈到当前步上的
    public static int maxIncrease(int[][] m, int[][] dp, int i, int j, int p) {
        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] <= p) {
            return 0;
        }
        if (dp[i][j] == 0) { // i,j 出发，当前没算过
            dp[i][j] = maxIncrease(m, dp, i + 1, j, m[i][j]) + 1;
            dp[i][j] = Math.max(dp[i][j], maxIncrease(m, dp, i, j + 1, m[i][j]) + 1);
            dp[i][j] = Math.max(dp[i][j], maxIncrease(m, dp, i - 1, j, m[i][j]) + 1);
            dp[i][j] = Math.max(dp[i][j], maxIncrease(m, dp, i, j - 1, m[i][j]) + 1);
        }
        return dp[i][j];
    }
    
    public static void main(String[] args) {
        LongestIncreasingPathInMatrix longestIncreasingPathInMatrix = new LongestIncreasingPathInMatrix();
        int[][] matrix = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(longestIncreasingPathInMatrix.longestIncreasingPath(matrix));
        System.out.println(longestIncreasingPathInMatrix.longestIncreasingPath2(matrix));
    }
}
