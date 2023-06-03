package com.iworld.algorithm.array.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 378. Kth Smallest Element in a Sorted Matrix
 * Medium
 * 8274
 * 293
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 *
 * Follow up:
 *
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity?
 * The solution may be too advanced for an interview but you may find reading this paper fun.
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * @date 2022/10/19 14:57
 */
public class KthSmallestElementInSortedMatrix {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        if (k == 1) {
            return matrix[0][0];
        }
        PriorityQueue<MatrixEntry> queue = new PriorityQueue<>(new MatrixValueComparator());
        boolean[][] isQueue = new boolean[row][col];
        isQueue[0][0] = true;
        int count = 0;
        MatrixEntry me = new MatrixEntry(0, 0, matrix[0][0]);
        queue.add(me);
        while (!queue.isEmpty()) {
            me = queue.poll();
            if (++count == k) {
                break;
            }
            int curRow = me.row;
            int curCol = me.col;
            int nextRow = curRow + 1;
            if (nextRow < row && !isQueue[nextRow][curCol]) {
                queue.add(new MatrixEntry(nextRow, curCol, matrix[nextRow][curCol]));
                isQueue[nextRow][curCol] = true;
            }
            int nextCol = curCol + 1;
            if (nextCol < col && !isQueue[curRow][nextCol]) {
                queue.add(new MatrixEntry(curRow, nextCol, matrix[curRow][nextCol]));
                isQueue[curRow][nextCol] = true;
            }
        }
        return me.val;
    }
    
    public static class MatrixEntry {
        public int row;
        public int col;
        public int val;
        
        public MatrixEntry(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    
    public static class MatrixValueComparator implements Comparator<MatrixEntry> {
        
        @Override
        public int compare(MatrixEntry m1, MatrixEntry m2) {
            return m1.val - m2.val;
        }
    }
    
    public static int kthSmallest2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];
        int ans = 0;
        while (left <= right) {
            // 计算中间数
            int mid = left + ((right - left) >> 1);
            // 在矩阵中找出小于当前mid的最大数 和在矩阵中的排名
            Info info = noMoreNum(matrix, mid);
            if (info.num < k) {
                // 中间数排名小于k 推大中间数
                left = mid + 1;
            } else {
                // 中间数排名大于等于k
                ans = info.near;
                right = mid - 1;
            }
        }
        return ans;
    }
    
    public static int kthSmallest3(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];
        int ans = 0;
        while (left <= right) {
            // 计算中间数
            int mid = left + ((right - left) >> 1);
            // 在矩阵中找出小于当前mid的最大数 和在矩阵中的排名
            Info info = noMoreNum(matrix, mid);
            if (info.num < k) {
                // 中间数排名小于k 推大中间数
                left = mid + 1;
            } else if(info.num > k) {
                // 中间数排名大于等于k
                ans = info.near;
                right = mid - 1;
            } else {
                ans = info.near;
                break;
            }
        }
        return ans;
    }
    
    public static class Info {
        // 矩阵中的值
        public int near;
        // 当前值在矩阵中第num小
        public int num;
        
        public Info(int n1, int n2) {
            near = n1;
            num = n2;
        }
    }
    
    /**
     * 找出最接近小于value的位置值 并且返回当前值在矩阵中第几小
     * @param matrix
     * @param value
     * @return
     */
    public static Info noMoreNum(int[][] matrix, int value) {
        // 最接近小于value的值
        int near = Integer.MIN_VALUE;
        // 第num小
        int num = 0;
        int rowEnd = matrix.length;
        int colEnd = matrix[0].length;
        int row = 0;
        int col = colEnd - 1;
        while (row < rowEnd && col >= 0) {
            if (matrix[row][col] <= value) {
                // 当前位置小于目标值 决策选出最大小于目标值的数
                near = Math.max(near, matrix[row][col]);
                // 计算出当前第几小 把小于当前值数加上
                num += col + 1;
                // 去大的可能位置
                row++;
            } else {
                // 当前值大于目标值 只能去小的可能位置
                col--;
            }
        }
        return new Info(near, num);
    }
    
    
    public static void main(String[] args) {
        int RUNNING = -1 << COUNT_BITS;
        System.out.println((RUNNING | 0));
        System.out.println(Integer.toBinaryString((RUNNING | 0)));
        Integer totalCount = 501;
        Integer count = 0;
        int totalTimes = (totalCount - 1) / 500 + 1;
        int times = 0;
        if (totalCount > 0) {
            count = 500;
            times++;
            while (count < totalCount && times < totalTimes) {
                count += 1;
                times++;
            }
        }
        System.out.println("totalCount==" + totalCount + ",count===" + count + ",totalTimes===" + totalTimes + ",times===" + totalTimes);
        System.out.println(Integer.toBinaryString(-1 << (Integer.SIZE - 3)));
        System.out.println(Integer.toBinaryString(1 << (Integer.SIZE - 3)));
        System.out.println(Integer.toBinaryString(2 << (Integer.SIZE - 3)));
        System.out.println(Integer.toBinaryString(3 << (Integer.SIZE - 3)));
        int[][] a = {{}};
        System.out.println(a.length);
        //int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int[][] matrix = {
                {1,2,3,50},
                {49,78,79,80},
                {78,79,80,81},
                {79,80,81,100}
        };
        int k = 1;
        KthSmallestElementInSortedMatrix kthSmallestElementInSortedMatrix = new KthSmallestElementInSortedMatrix();
        System.out.println(kthSmallestElementInSortedMatrix.kthSmallest(matrix, k));
        System.out.println(kthSmallest2(matrix, k));
        System.out.println(kthSmallest3(matrix, k));
    }
}
