package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 48. Rotate Image  Medium
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 * https://leetcode.com/problems/rotate-image/
 * @date 2022/8/11 9:29
 */
public class RotateImage {
    
    public void rotate(int[][] matrix) {
        int sr = 0;
        int sc = 0;
        int er = matrix.length - 1;
        int ec = matrix[0].length - 1;
        while (sr < er) {
            rotateEdge(matrix, sr++, sc++, er--, ec--);
        }
    }
    
    /**
     * 正方形矩阵从外向内 按圈处理
     * @param matrix    要旋转的矩阵
     * @param sr        开始行 也即为左下顶点的行
     * @param sc        开始列 也即为左下顶点的列
     * @param er        结束行 也即为右上顶点的行
     * @param ec        结束列 也即为右上顶点的列
     */
    private void rotateEdge(int[][] matrix, int sr, int sc, int er, int ec) {
        for (int i = 0; i < ec - sc; i++) {
            int tmp = matrix[sr][sc + i];
            matrix[sr][sc + i] = matrix[er - i][sc];
            matrix[er - i][sc] = matrix[er][ec - i];
            matrix[er][ec - i] = matrix[sr + i][ec];
            matrix[sr + i][ec] = tmp;
        }
    }
    
    /**
     * 123
     * 456
     * 789
     *
     * 741
     * 852
     * 963
     *
     * 5  1  9  11
     * 2  4  8  10
     * 13 3  6  7
     * 15 14 12 16
     *
     * 15 13 2  5
     * 14 3  4  1
     * 12 6  8  9
     * 16 7  10 11
     * @param args
     */
    public static void main(String[] args) {
        //int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("]");
            System.out.println();
        }
    }
}
