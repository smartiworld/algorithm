package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 62. Unique Paths
 * Medium
 * 12.3K
 * 357
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n,
 * return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * Example 1:
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 * https://leetcode.com/problems/unique-paths/
 * @date 2022/11/14 23:52
 */
public class UniquePaths {
    
    public int uniquePaths(int m, int n) {
        return process(0, 0, m, n);
    }
    
    private int process(int row, int col, int m, int n) {
        if (row == m - 1 && col == n - 1) {
            return 1;
        }
        if (row >= m || col >= n) {
            return 0;
        }
        int ans = 0;
        ans += process(row + 1, col, m, n);
        ans += process(row, col + 1, m, n);
        return ans;
    }
    
    public int uniquePathsDp(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }
    
    /**
     * 排列组合   阶乘
     * c(5,3)=p(5,3)/p(3,3)
     * p(5,3)=5!/(5-3)!  p(3,3)=3!
     * c(5,3)=5!/(2!*3!)=4*5/2 同时约掉上下共同的数
     * 计算阶乘 尽量保证不溢出 每次计算完毕 计算最大公约数
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsMath(int m, int n) {
        // 排列组合的低 一共可以走的步数
        int all = m + n - 2;
        int part = n - 1;
        long a = 1;
        long b = 1;
        // 现在是约掉相同的排列组合 分母为i 分子为j
        for (int i = part + 1, j = 1; i <= all || j <= all - part; i++, j++) {
            a *= i;
            b *= j;
            long gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
        }
        return (int) a;
    }
    
    /**
     * 循环相除法 最大公约数
     * @return
     */
    private long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }
    
    
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(m, n));
        System.out.println(uniquePaths.uniquePathsDp(m, n));
        System.out.println(uniquePaths.uniquePathsMath(m, n));
    }
}
