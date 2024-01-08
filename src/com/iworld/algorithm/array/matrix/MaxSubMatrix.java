package com.iworld.algorithm.array.matrix;

import com.iworld.algorithm.util.ArrayUtil;

/**
 * @author gq.cai
 * @version 1.0
 * @description 面试题 17.24. 最大子矩阵
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 *
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 *
 * 注意：本题相对书上原题稍作改动
 *
 * 示例：
 *
 * 输入：
 * [
 *    [-1,0],
 *    [0,-1]
 * ]
 * 输出：[0,1,0,1]
 * 解释：输入中标粗的元素即为输出所表示的矩阵
 *
 * 说明：
 *
 * 1 <= matrix.length, matrix[0].length <= 200
 * https://leetcode.cn/problems/max-submatrix-lcci/description/
 * @date 2024/1/5 19:43
 */
public class MaxSubMatrix {
    
    public int[] getMaxMatrix(int[][] matrix) {
        int[] result = new int[4];
        int r1 = 0;
        int c1 = 0;
        int r2 = 0;
        int c2 = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < row; k++) {
            int[] help = new int[col];
            for (int i = k; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    help[j] += matrix[i][j];
                }
                int[] subMax = maxSubArray(help);
                if (subMax[0] > max) {
                    max = subMax[0];
                    r1 = k;
                    c1 = subMax[1];
                    r2 = i;
                    c2 = subMax[2];
                }
            }
        }
        result[0] = r1;
        result[1] = c1;
        result[2] = r2;
        result[3] = c2;
        return result;
    }
    
    /**
     * 计算最大累加数组 并返回数组范围下标
     * 0位置 最大累加和
     * 1位置 左边界
     * 2位置 右边界
     * @param array
     * @return
     */
    public int[] maxSubArray(int[] array) {
        int[] ans = new int[3];
        int cur = 0;
        int max = Integer.MIN_VALUE;
        int c1 = 0;
        int tmp = c1;
        int c2 = 0;
        for (int j = 0; j < array.length; j++) {
            if (cur == 0) {
                tmp = j;
            }
            cur += array[j];
            if (cur > max) {
                max = cur;
                c2 = j;
                c1 = tmp;
            }
            cur = Math.max(0, cur);
        }
        ans[0] = max;
        ans[1] = c1;
        ans[2] = c2;
        return ans;
    }
    
    public static void main(String[] args) {
        // 9  -8  1  3
        int[][] matrix = {
                {9,-8,1,3,-2},
                {-3,7,6,-2,4},
                {6,-4,-4,8,-7}};
        //int[][] matrix = {{-4, -5}};
        MaxSubMatrix maxSubMatrix = new MaxSubMatrix();
        int[] maxMatrix = maxSubMatrix.getMaxMatrix(matrix);
        ArrayUtil.printArray(maxMatrix);
        int[] maxMatrixBest = maxSubMatrix.getMaxMatrixBest1(matrix);
        ArrayUtil.printArray(maxMatrixBest);
        int[] maxMatrixBest2 = maxSubMatrix.getMaxMatrixBest2(matrix);
        ArrayUtil.printArray(maxMatrixBest2);
    }
    
    public int[] getMaxMatrixBest1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSumY = new int[m + 1][n];
        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= m; i++) {
                preSumY[i][j] = preSumY[i - 1][j] + matrix[i - 1][j];
            }
        }
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        int curStartIdx = 0;
        int[] res = new int[4];
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                curSum = 0;
                curStartIdx = 0;
                for (int k = 0; k < n; k++) {
                    int cur = preSumY[j + 1][k] - preSumY[i][k];
                    curSum += cur;
                    if (curSum <= cur) {
                        curSum = cur;
                        curStartIdx = k;
                    }
                    if (curSum > max) {
                        max = curSum;
                        res[0] = i;
                        res[1] = curStartIdx;
                        res[2] = j;
                        res[3] = k;
                    }
                }
            }
        }
        return res;
    }
    
    public int[] getMaxMatrixBest2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSumX = new int[m][n + 1];
        // 列累加和
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                preSumX[i][j] = preSumX[i][j - 1] + matrix[i][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        int curStartIdx = 0;
        int[] res = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                curSum = 0;
                curStartIdx = 0;
                for (int k = 0; k < m; k++) {
                    int cur = preSumX[k][j + 1] - preSumX[k][i];
                    curSum += cur;
                    if (curSum <= cur) {
                        curSum = cur;
                        curStartIdx = k;
                    }
                    if (curSum > max) {
                        max = curSum;
                        res[0] = curStartIdx;
                        res[0] = i;
                        res[2] = k;
                        res[3] = j;
                    }
                }
            }
        }
        
        return res;
    }
    
}
