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
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
        KthSmallestElementInSortedMatrix kthSmallestElementInSortedMatrix = new KthSmallestElementInSortedMatrix();
        System.out.println(kthSmallestElementInSortedMatrix.kthSmallest(matrix, k));
    }
}
