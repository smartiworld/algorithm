package com.iworld.algorithm.array.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 54. Spiral Matrix  Medium
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * https://leetcode.com/problems/spiral-matrix/
 * @date 2022/8/11 12:11
 */
public class SpiralMatrix {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int sr = 0;
        int sc = 0;
        int er = matrix.length - 1;
        int ec = matrix[0].length - 1;
        while (sr <= er && sc <= ec) {
            spiralEdge(matrix, ans, sr++, sc++, er--, ec--);
        }
        return ans;
    }
    
    private void spiralEdge(int[][] matrix, List<Integer> ans, int sr, int sc, int er, int ec) {
        if (sr == er) {
            for (int i = sc; i <= ec; i++) {
                ans.add(matrix[sr][i]);
            }
        } else if (sc == ec) {
            for (int i = sr; i <= er; i++) {
                ans.add(matrix[i][sc]);
            }
        } else {
            // 上边 r=sr
            for (int c = sc; c < ec; c++) {
                ans.add(matrix[sr][c]);
            }
            // 右边 c=ec
            for (int r = sr; r < er; r++) {
                ans.add(matrix[r][ec]);
            }
            // 下边 r=er
            for (int c = ec; c > sc; c--) {
                ans.add(matrix[er][c]);
            }
            // 左边 c=sc
            for (int r = er; r > sr; r--) {
                ans.add(matrix[r][sc]);
            }
        }
    }
    
    /**
     * 1, 2, 7, 10
     * 3, 9, 8, 6
     * 5, 7, 9, 15
     *
     * 1,2,7,10,6,15,9,7,5,3,9,8
     *
     * 1, 2, 7
     * 3, 9, 8
     * 5, 7, 9
     *
     * 1,2,7,8,9,7,5,3,9
     *
     * 1, 2, 7
     * 3, 9, 8
     * 5, 7, 9
     * 10,6, 15
     *
     * 1,2,7,8,9,15,6,10,5,3,9,7
     * @param args
     */
    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        //int[][] matrix = {{1, 2, 7, 10}, {3, 9, 8, 6}, {5, 7, 9, 15}};
        //int[][] matrix = {{1, 2, 7}, {3, 9, 8}, {5, 7, 9}};
        int[][] matrix = {{1, 2, 7}, {3, 9, 8}, {5, 7, 9}, {10, 6, 15}};
        List<Integer> ans = spiralMatrix.spiralOrder(matrix);
        System.out.println(ans);
    }
}
