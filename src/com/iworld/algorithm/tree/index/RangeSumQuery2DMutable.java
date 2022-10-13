package com.iworld.algorithm.tree.index;

/**
 * @author gq.cai
 * @version 1.0
 * @description 308.RangeSumQuery2DMutable
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 *
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 *
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * 求(2,1),(4,3)区域面积
 * sumRegion(2, 1, 4, 3) -> 8
 * 更新(3,2)位置值为2
 * update(3, 2, 2)
 * 求(2,1),(4,3)区域面积
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 * 1. The matrix is only modifiable by the update function.
 * 2. You may assume the number of calls to update and sumRegion function is distributed evenly.
 * 3. You may assume that row1 ≤ row2 and col1 ≤ col2.
 * @date 2022/10/13 16:35
 */
public class RangeSumQuery2DMutable {
    
    private int[][] tree;
    
    private int[][] nums;
    
    private int row;
    
    private int col;
    
    public RangeSumQuery2DMutable(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        nums = new int[m][n];
        this.row = m + 1;
        this.col = n + 1;
        tree = new int[row][col];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public void update(int row, int col, int val) {
        if (this.row == 0 || this.col == 0) {
            return ;
        }
        int add = nums[row][col] - val;
        nums[row][col] = val;
        for (int i = row + 1; i < this.row; i += i & (-i)) {
            for (int j = col + 1; j < this.col; j += j & (-j)) {
                tree[i][j] += add;
            }
        }
    }
    
    private int sum(int row, int col) {
        int ans = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                ans += tree[row][col];
            }
        }
        return ans;
    }
    
    /**
     * 计算开始位置到row2,col2区域大小sum(row2, col2)
     * 减去左侧区域sum(row2, col1 - 1) 和上侧区域sum(row1 - 1, col2)
     * 会多减一个左上区域 然后加上这个区域sum(row1 - 1, col1 - 1)
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (this.row == 0 || this.col == 0) {
            return 0;
        }
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row2, col1 - 1) - sum(row1 - 1, col2);
    }
}
