package com.iworld.algorithm.array.matrix;

import com.iworld.algorithm.util.MatrixUtils;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.1.5 最大包含1的边正方形边长
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 * 只要求边为1 正方形内部可以为0
 * @date 2023/12/1 10:51
 */
public class MaximalSquareEdge {
    
    public static int maximalEdge(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 当前位置右边和当前位置连续为1的长度
        int[][] right = new int[row][col];
        // 当前位置下边和当前位置连续为1的长度
        int[][] bottom = new int[row][col];
        fillMatrix(matrix, right, bottom);
        int maxEdge = Math.min(row, col);
        for (int k = maxEdge; k > 0; k--) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // 当前位置向右向下的长度都大于当前遍历的边长 再计算剩下两个边是否也满足当前条件
                    if (right[i][j] >= k && bottom[i][j] >= k) {
                        // 当前点向右扩张k长度列所到点列位置
                        int startY = j + k - 1;
                        // 当前点向下扩张k长度行所到点列位置
                        int startX = i + k - 1;
                        if (right[startX][j] >= k && bottom[i][startY] >= k) {
                            return k;
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    public static void fillMatrix(int[][] matrix, int[][] right, int[][] bottom) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int j = col - 1; j >= 0; j--) {
            for (int i = 0; i < row; i++) {
                right[i][j] = matrix[i][j] == 1 ? 1 : 0;
                if (j < col - 1 && matrix[i][j] == 1) {
                    right[i][j] += right[i][j + 1];
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                bottom[i][j] = matrix[i][j] == 1 ? 1 : 0;
                if (i < row - 1 && matrix[i][j] == 1) {
                    bottom[i][j] += bottom[i + 1][j];
                }
            }
        }
    }
    
    public static int maximalEdgeForce(int[][] matrix) {
        if (matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    int left = process(matrix, i, j, 0, 0, row, col);
                    for (int k = left; k > 0; k--) {
                        int top = process(matrix, i, j, 1, k, row, col);
                        int rightStartY = j + top - 1;
                        int right = process(matrix, i, rightStartY, 2, top, row, col);
                        int bottomStartX = i + right - 1;
                        int bottom = process(matrix, bottomStartX, j, 3, right, row, col);
                        if (bottom < k) {
                            continue;
                        }
                        max = Math.max(max, bottom);
                    }
                }
            }
        }
        
        return max;
    }
    
    /**
     *
     * @param matrix
     * @param startX 开始行号
     * @param startY 开始列号
     * @param edge   计算哪条边 0-左边 1-上边 2-右边 3-下边
     * @param maxLen 最大长度
     * @param row    最大行
     * @param col    最大列
     * @return
     */
    private static int process(int[][] matrix, int startX, int startY, int edge, int maxLen, int row, int col) {
        if ((startX == row - 1 || startY == col - 1) && maxLen == 0) {
            return 1;
        }
        int len = 0;
        if (edge == 0 || edge == 2) {
            for (int i = startX; i < row; i++) {
                if (matrix[i][startY] != 1) {
                    break;
                }
                len++;
                if (maxLen > 0 && len == maxLen) {
                    break;
                }
            }
        }
        if (edge == 1 || edge == 3) {
            for (int j = startY; j < col; j++) {
                if (matrix[startX][j] != 1) {
                    break;
                }
                len++;
                if (maxLen > 0 && len == maxLen) {
                    break;
                }
            }
        }
        return len;
    }
    
    
    public static void main(String[] args) {
        int times = 1000;
        int row = 10;
        int col = 10;
        int maxValue = 1;
        for (int i = 0; i < times; i++) {
            int[][] matrix = MatrixUtils.generatorIntegerMatrix(row, col, maxValue);
            int m1 = maximalEdge(matrix);
            int m2 = maximalEdgeForce(matrix);
            if (m1 != m2) {
                MatrixUtils.printMatrix(matrix);
                System.out.println(m1 + "=====" + m2);
            }
        }
        int[][] matrix = {
                {1,0,1,0,0,1,1,0,0,1},
                {1,0,0,0,0,1,1,0,1,0},
                {0,0,0,1,1,1,1,1,1,0},
                {0,0,0,1,1,0,0,1,1,0},
                {0,1,1,1,1,1,1,1,1,1},
                {0,1,0,1,0,0,0,0,1,0},
                {0,1,0,1,1,0,1,0,0,1},
                {1,0,1,0,1,1,1,1,1,1},
                {1,1,0,0,1,0,0,0,1,1},
                {0,1,0,1,0,0,1,1,0,1},
        };
        int m1 = maximalEdgeForce(matrix);
        System.out.println(m1);
        
    }
    
}
