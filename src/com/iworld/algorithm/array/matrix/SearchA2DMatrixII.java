package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 240. Search a 2D Matrix II
 * Medium
 * 9102
 * 150
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * Example 1:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * Example 2:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -10^9 <= target <= 10^9
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * @date 2022/8/31 11:36
 */
public class SearchA2DMatrixII {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int rl = matrix.length;
        int cl = matrix[0].length;
        int sr = 0;
        int sc = cl - 1;
        while (sr < rl && sc >= 0) {
            if (matrix[sr][sc] > target) {
                sc--;
            } else if (matrix[sr][sc] < target){
                sr++;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int rl = matrix.length;
        int cl = matrix[0].length;
        int sr = 0;
        int sc = cl - 1;
        while (sr < rl || sc < cl) {
            if (matrix[sr][sc] == target) {
                return true;
            }
            if (matrix[sr][sc] > target) {
                if (sc == 0) {
                    return false;
                }
                sc--;
            } else {
                if (sr == rl - 1) {
                    return false;
                }
                sr++;
            }
        }
        return false;
    }
}
