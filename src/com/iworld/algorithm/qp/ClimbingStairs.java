package com.iworld.algorithm.qp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 70. Climbing Stairs
 * Easy
 * 15.4K
 * 458
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 * https://leetcode.com/problems/climbing-stairs/
 * @date 2022/11/19 17:12
 */
public class ClimbingStairs {
    
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    public int climbStairsDp(int n) {
        if (n == 1) {
            return 1;
        }
        int prePre = 1;
        int pre = 2;
        for (int i = 3; i <= n; i++) {
           int tmp = pre + prePre;
           prePre = pre;
           pre = tmp;
        }
        return pre;
    }
    
    /**
     * 快速幂
     * FnFn-1 = F2F1*{{x,y}{m,n}}^n-2
     * 矩阵计算
     * @param n
     * @return
     */
    public int climbStairsQuickPower(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1,1},{1,0}};
        int[][] multiMatrix = matrixPower(base, n - 2);
        // FnFn-1 = |2,1|*{{x,y},{a,b}}  multiMatrix
        // Fn=2x+y
        return 2 * multiMatrix[0][0] + multiMatrix[1][0];
    }
    
    /**
     * 矩阵p次方
     * @param m
     * @param p
     * @return
     */
    private int[][] matrixPower(int[][] m, int p) {
        int[][] ans = new int[m.length][m[0].length];
        // 单位矩阵对角线是1
        for (int i = 0; i < ans.length; i++) {
            ans[i][i] = 1;
        }
        // 需要幂计算的数
        int[][] base = m;
        for ( ; p != 0; p >>= 1) {
            if ((p & 1) == 1) {
                ans = multiMatrix(ans, base);
            }
            base = multiMatrix(base, base);
        }
        return ans;
    }
    
    /**
     * 两个矩阵相乘
     * @param m1
     * @param m2
     * @return
     */
    private int[][] multiMatrix(int[][] m1, int[][] m2) {
        // 相乘结果矩阵 行是m1的行，列是m2的列
        int[][] ans = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairsQuickPower(3));
    }
}
