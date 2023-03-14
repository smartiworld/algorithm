package com.iworld.algorithm.array.matrix;

import java.util.TreeSet;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.1.3
 * 给定一个二维数组matrix，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子矩阵累加和
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class01/Code03_MaxSumofRectangleNoLargerThanK.java
 * @date 2023/3/14 15:04
 */
public class MaxSubMatrixSumNoLargerThanK {
    
    /**
     * 以当前位置结尾的矩阵
     * 压缩矩阵
     * 1.从0~0 0~1 .。。 0~n-1行累加为一行数组 在单行数组上最子数组累加和不超过k的计算
     * 2.然后再从1~1 1~2 .。。 1-n-1 直到n-1~n-1累加为一行数组 在单行数组上最子数组累加和不超过k的计算 找出最大值
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubMatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int r = 0; r < row; r++) {
            // 矩阵压缩后的累加和数组
            int[] help = new int[col];
            // i-r区间矩阵累加到help数组
            for (int i = r; i < row; i++) {
                int sum = 0;
                // 每增加变化一行就需要重置累加和有序表 不然会有前面数组计算和干扰
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int j = 0; j < col; j++) {
                    // 累加之前行数组
                    help[j] += matrix[i][j];
                    sum += help[j];
                    Integer ceiling = set.ceiling(sum - k);
                    if (ceiling != null) {
                        max = Math.max(max, sum - ceiling);
                    }
                    set.add(sum);
                }
            }
        }
        return max;
    }
    
    public int maxSumSubMatrix2(int[][] matrix, int k) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length, res = Integer.MIN_VALUE;
        TreeSet<Integer> sumSet = new TreeSet<>();
        for (int s = 0; s < row; s++) { // s开始行
            int[] colSum = new int[col];
            for (int e = s; e < row; e++) { // e结束行
                // 子矩阵必须包含s~e行的数，且只包含s~e行的数
                sumSet.add(0);
                int rowSum = 0;
                for (int c = 0; c < col; c++) {
                    colSum[c] += matrix[e][c];
                    rowSum += colSum[c];
                    Integer it = sumSet.ceiling(rowSum - k);
                    if (it != null) {
                        res = Math.max(res, rowSum - it);
                    }
                    sumSet.add(rowSum);
                }
                sumSet.clear();
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,8}, {8,9,1,3}, {5,3,5,12}};
        MaxSubMatrixSumNoLargerThanK maxSubMatrixSumNoLargerThank = new MaxSubMatrixSumNoLargerThanK();
        int k = 22;
        System.out.println(maxSubMatrixSumNoLargerThank.maxSumSubMatrix(matrix, k));
    }
}
