package com.iworld.algorithm.qp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 斐波那契数列  快速幂
 * 0 1 1 2 3 5 8 13 21 34 55
 * @date 2023/4/25 13:44
 */
public class FibonacciSequence {
    
    public int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * 矩阵的整数次方
     * 矩阵默认行列相等
     * @param matrix
     * @param power
     * @return
     */
    private int[][] matrixPower(int[][] matrix, int power) {
        // 单位矩阵
        int[][] ans = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            ans[i][i] = 1;
        }
        int[][] base = matrix;
        while (power != 0) {
            if ((power & 1) == 1) {
                ans = matrixMultiMatrix(base, ans);
            }
            base = matrixMultiMatrix(base, base);
            power >>= 1;
        }
        return ans;
    }
    
    /**
     * 两个矩阵相乘结果
     * 结果矩阵行为第一个矩阵行 列为第二个矩阵的列
     * 计算时两个矩阵第一个矩阵列需要和第二矩阵行相同 计算时可以省掉一层循环
     * @param m1
     * @param m2
     * @return
     */
    private int[][] matrixMultiMatrix(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        FibonacciSequence fibonacciSequence = new FibonacciSequence();
        int n = 2;
        int fibonacci = fibonacciSequence.fibonacci(n);
        System.out.println(fibonacci);
        int[][] m1 = {
                {2,3,1},
                {1,3,1}
        };
        int[][] m2 = {
                {2,3,1},
                {1,4,1},
                {5,1,1}
        };
        int[][] ints = fibonacciSequence.matrixMultiMatrix(m1, m2);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.print(ints[i][j] + ",");
            }
            System.out.println();
        }
        int[][] ints1 = fibonacciSequence.matrixPower(m2, 2);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.print(ints[i][j] + ",");
            }
            System.out.println();
        }
    }
    
}
