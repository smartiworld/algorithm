package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.4.7 最大累加和子矩阵
 * 给定一个整型矩阵，返回子矩阵的最大累加和。
 * @date 2024/1/5 18:22
 */
public class MaxSumSubMatrix {

    public static int maxSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < row; k++) {
            int[] help = new int[col];
            for (int i = k; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    help[j] += matrix[i][j];
                }
                max = Math.max(max, maxSubArray(help));
            }
        }
        return max;
    }
    
    private static int maxSubArray(int[] array) {
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < array.length; j++) {
            cur += array[j];
            max = Math.max(max, cur);
            cur = Math.max(0, cur);
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
                {9,-8,1,3,-2},
                {-3,7,6,-2,4},
                {6,-4,-4,8,-7}};
        int sum = maxSum(matrix);
        System.out.println(sum);
    }
}
