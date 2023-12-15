package com.iworld.algorithm.dp.matrix;

import java.math.BigDecimal;

/**
 * @author gq.cai
 * @version 1.0
 * @description 221. Maximal Square
 * Medium
 * 9.7K
 * 210
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],
 *                  ["1","0","1","1","1"],
 *                  ["1","1","1","1","1"],
 *                  ["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 *
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 * https://leetcode.com/problems/maximal-square/
 * @date 2023/11/29 19:20
 */
public class MaximalSquare {
    
    
    
    public static int maximalSquare(char[][] matrix) {
        return 0;
    }
    
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(100);
        BigDecimal b = a;
        if (a.intValue() == 100) {
            b = b.divide(BigDecimal.valueOf(10));
        }
        System.out.println(a.doubleValue());
        System.out.println(b.doubleValue());
        // char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix = {{'1','0','1','1','0','1'},
                            {'1','1','1','1','1','1'},
                            {'0','1','1','0','1','1'},
                            {'1','1','1','0','1','0'},
                            {'0','1','1','1','1','1'},
                            {'1','1','0','1','1','1'}};
        System.out.println(maximalSquare(matrix));
    }
}
