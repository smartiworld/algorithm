package com.iworld.algorithm.util;

import java.util.Random;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2023/12/1 16:34
 */
public class MatrixUtils {
    
    public static int[][] generatorIntegerMatrix(int row, int col, int maxValue) {
        int[][] matrix = new int[row][col];
        Random r = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = r.nextInt(maxValue + 1);
            }
        }
        return matrix;
    }
    
    public static void priceMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        System.out.println("{");
        for (int i = 0; i < row; i++) {
            System.out.print("{");
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j]);
                if (j < col - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("}");
            if (i < row - 1) {
                System.out.print(",");
            }
        }
        System.out.println("}");
    }
    
    public static void main(String[] args) {
        int row = 10;
        int col = 10;
        int maxValue = 1;
        int[][] matrix = generatorIntegerMatrix(row, col, maxValue);
        System.out.println("{");
        for (int i = 0; i < row; i++) {
            System.out.print("{");
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
}
