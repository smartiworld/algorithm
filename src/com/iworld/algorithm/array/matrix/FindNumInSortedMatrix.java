package com.iworld.algorithm.array.matrix;

import com.iworld.algorithm.util.MatrixUtils;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.2.1 排序矩阵中查找目标值
 * 在行也有序、列也有序的二维数组中，找num，找到返回true，否则false
 * @date 2023/12/7 21:03
 */
public class FindNumInSortedMatrix {
    
    public static boolean findNum(int[][] matrix, int num) {
        int row = matrix.length;
        int col = matrix[0].length;
        int curRow = 0;
        int curCol = col - 1;
        while (curRow < row && curCol >= 0) {
            int cur = matrix[curRow][curCol];
            if (cur == num) {
                return true;
            }
            if (cur > num) {
                curCol --;
            } else {
                curRow ++;
            }
        }
        return false;
    }
    
    public static boolean findNumForce(int[][] matrix, int num) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (matrix[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int times = 1000;
        int row = 15;
        int col = 15;
        int maxValue = 50;
        for (int i = 0; i < times; i++) {
            int[][] ints = MatrixUtils.generatorSortIntegerMatrix(row, col, maxValue);
            int num = (int) (Math.random() * 100);
            boolean num1 = findNum(ints, num);
            boolean numForce = findNumForce(ints, num);
            if (num1 != numForce) {
                MatrixUtils.printMatrix(ints);
                System.out.println(num);
                System.out.println(num1 + "===" + numForce);
                break;
            }
        }
    }
}
