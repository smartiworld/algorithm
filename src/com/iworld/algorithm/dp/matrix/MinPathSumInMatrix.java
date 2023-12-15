package com.iworld.algorithm.dp.matrix;

import com.iworld.algorithm.util.MatrixUtils;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.3.3 矩阵最小路径和
 * 给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下
 * 每一步只能向右或者向下，沿途经过的数字要累加起来
 * 最后请返回最小的路径和
 *
 * 动态规划的空间压缩技巧！
 * @date 2023/12/14 21:26
 */
public class MinPathSumInMatrix {
    
    public static int minPathSum(int[][] matrix) {
        return process(matrix, 0, 0, matrix.length, matrix[0].length);
    }
    
    public static int process(int[][] matrix, int r, int c, int row, int col) {
        if (r == row - 1 && c == col - 1) {
            return matrix[r][c];
        }
        int ans = Integer.MIN_VALUE;
        if (r + 1 < row) {
            ans = process(matrix, r + 1, c, row, col);
        }
        if (c + 1 < col) {
            int right = process(matrix, r, c + 1, row, col);
            ans = Math.max(ans, right);
        }
        return matrix[r][c] + ans;
    }
    
    public static int minPathSumDp(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = matrix[row - 1][col - 1];
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = matrix[i][col - 1] + dp[i + 1][col - 1];
        }
        for (int j = col - 2; j >= 0; j--) {
            dp[row - 1][j] = matrix[row - 1][j] + dp[row - 1][j + 1];
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = matrix[i][j] + Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
    
    /**
     * 当前位置依赖当前位置右边或者下边 最左位置只依赖当前位置下边位置
     * 减少一维空间 当前位置比较后重新压入数组 作为前位置右侧做对比 前位置下面数值未变化
     * 只有当前位置更新得时候会压入下面位置
     * @param matrix
     * @return
     */
    public static int minPathSumDpOpt(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];
        dp[col - 1] = matrix[row - 1][col - 1];
        for (int j = col - 2; j >= 0; j--) {
            dp[j] = matrix[row - 1][j] + dp[j + 1];
        }
        for (int i = row - 2; i >= 0; i--) {
            dp[col - 1] = matrix[i][col - 1] + dp[col - 1];
            for (int j = col - 2; j >= 0; j--) {
                dp[j] = matrix[i][j] + Math.max(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
    
    
    public static void main(String[] args) {
        int row = 15;
        int col = 15;
        int maxValue = 50;
        int times = 1000;
        for (int i = 0; i <= times; i++) {
            int[][] matrix = MatrixUtils.generatorIntegerMatrix(row, col, maxValue);
            int i1 = minPathSum(matrix);
            int i2 = minPathSumDp(matrix);
            int i3 = minPathSumDpOpt(matrix);
            if (i1 != i2) {
                System.out.println("i1===" + i1 + "===i2===" + i2);
                MatrixUtils.printMatrix(matrix);
            } else {
                System.out.println("success 1");
            }
            if (i1 != i3) {
                System.out.println("i1===" + i1 + "===i3===" + i3);
                MatrixUtils.printMatrix(matrix);
            } else {
                System.out.println("success 2");
            }
        }
        System.out.println("end");
    }
    
}
