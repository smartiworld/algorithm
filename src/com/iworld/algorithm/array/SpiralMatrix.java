package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 59.螺旋矩阵
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，
 * 且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * 提示：
 * 1 <= n <= 20
 *
 * 链接：https://leetcode.cn/problems/spiral-matrix-ii
 * @date 2022/5/27 23:50
 */
public class SpiralMatrix {
    
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int i = 0, j = 0, cur = 2;
        res[0][0] = 1;
        while (cur <= n * n) {
            while (j < n - 1 && res[i][j + 1] == 0) {
                // 右
                res[i][++j] = cur++;
            }
            while (i < n - 1 && res[i + 1][j] == 0) {
                // 下
                res[++i][j] = cur++;
            }
            while (j > 0 && res[i][j - 1] == 0) {
                // 左
                res[i][--j] = cur++;
            }
            while (i > 0 && res[i - 1][j] == 0) {
                // 上
                res[--i][j] = cur++;
            }
        }
        return res;
    }
    
    public int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];
        // 行下标
        int i = 0;
        // 列下标
        int j = 0;
        // 已经转了几圈
        int offset = 0;
        // 设置初始位置
        res[0][0] = 1;
        // 开始数字
        int start = 2;
        while (offset < n) {
            // 上  此时i=0的已经全部处理完毕
            while (j + 1 < n - offset && res[i][j + 1] == 0) {
                res[i][++j] = start ++;
            }
            // 右  此时i=0已经处理 i++
            while (i + 1 < n - offset && res[i + 1][j] == 0) {
                res[++i][j] = start ++;
            }
            // 下
            while (j - 1 >= 0 && res[i][j - 1] == 0) {
                res[i][--j] = start ++;
            }
            // 左 i=0处理完毕 不包含0的位置
            while (i - 1 > 0 && res[i - 1][j] == 0) {
                res[--i][j] = start ++;
            }
            offset ++;
        }
        return res;
    }
    
    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] ints = spiralMatrix.generateMatrix2(5);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j] + ",");
            }
            System.out.println();
        }
    }
}
