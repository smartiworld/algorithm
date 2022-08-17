package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 73. Set Matrix Zeroes  Medium
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 * Example 1:
 *
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 *
 *
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2 31 <= matrix[i][j] <= 2 31 - 1
 *
 *
 * Follow up:
 *
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * https://leetcode.com/problems/set-matrix-zeroes/
 * @date 2022/8/17 10:04
 */
public class SetMatrixZeroes {
    
    public void setZeroes(int[][] matrix) {
        boolean rowZeroStatus = false;
        boolean colZeroStatus = false;
        // 首行0状态标记
        int i = 0;
        for (; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colZeroStatus = true;
                break;
            }
        }
        // 首列0状态标记
        int j = 0;
        for (; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowZeroStatus = true;
                break;
            }
        }
        // 在首行首列打标记 如果matrix[i][j]==0 在当前0行和0列分别打上0标记
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowZeroStatus) {
            for (j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (colZeroStatus) {
            for (i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    
    public void setZeroesOpt(int[][] matrix) {
        // 首行是否有0
        boolean rowZeroStatus = false;
        int j = 0;
        for (; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowZeroStatus = true;
                break;
            }
        }
        // 当前位置数字=0 将0行当前列和当前行0列做上0标记
        int i = 1;
        for (; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 从后遍历如果当前行0列 当前列0行为0 刷新当前位置数字=0
        for (i = matrix.length - 1; i > 0; i--) {
            for (j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 标记首行
        if (rowZeroStatus) {
          for (j = 0; j < matrix[0].length; j++) {
              matrix[0][j] = 0;
          }
        }
    }
}
