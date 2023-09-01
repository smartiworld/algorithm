package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.2.6 往返选择最大路径
 * 给定一个矩阵matrix，先从左上角开始，每一步只能往右或者往下走，走到右下角。然后从右下角出发，每一步只能往上或者往左走，再回到左上角。
 * 任何一个位置的数字，只能获得一遍。返回最大路径和。
 * @date 2023/3/17 19:49
 */
public class CherryPickup {
    
    /**
     * 可以走重复路 但是一个单元格值只能获取一次
     * 一个往返看作一个整体
     * @param matrix
     * @return
     */
    public int maxSumPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        return process(matrix, 0, 0, false, row, col);
    }
    
    private int process(int[][] matrix, int r, int c, boolean reverse, int row, int col) {
        if (r == 0 && c == 0 && reverse) {
            return matrix[0][0];
        }
        int cur = matrix[r][c];
        matrix[r][c] = 0;
        if (reverse) {
            int process = 0;
            if (r == 0) {
                process = process(matrix, r, c - 1, reverse, row, col);
                matrix[r][c] = cur;
                return cur + process;
            }
            if (c == 0) {
                process = process(matrix, r - 1, c, reverse, row, col);
                matrix[r][c] = cur;
                return cur + process;
            }
            process = Math.max(process(matrix, r, c - 1, reverse, row, col), process(matrix, r - 1, c, reverse, row, col));
            matrix[r][c] = cur;
            return cur + process;
        } else {
            if (r == row - 1 && c == col - 1) {
                // 到最后一个单元格后 开始往返
                int process = Math.max(process(matrix, r, c - 1, true, row, col), process(matrix, r - 1, c, true, row, col));
                matrix[r][c] = cur;
                return cur + process;
            }
            if (r == row - 1) {
                int process = process(matrix, r, c + 1, reverse, row, col);
                matrix[r][c] = cur;
                return cur + process;
            }
            if (c == col - 1) {
                int process = process(matrix, r + 1, c, reverse, row, col);
                matrix[r][c] = cur;
                return cur + process;
            }
            int process = Math.max(process(matrix, r, c + 1, reverse, row, col), process(matrix, r + 1, c, reverse, row, col));
            matrix[r][c] = cur;
            return cur + process;
        }
    }
    
    /**
     * 两个点同时走相同步数
     * @param matrix
     * @return
     */
    public int maxSumPath1(int[][] matrix) {
        return process1(matrix, 0, 0, 0);
    }
    
    /**
     * 两个人同时从左上角向右下角移动 两人同时移动 如果到达同一个格子 格子内东西只能被一人领走
     * 两人移动轨迹只能向下或者向右走，一个人走到右下角的时候 另一人肯定也走到右下角
     * 第一个人走到的行列和第二人走的行可以推出第二人走到的列 两人同时移动
     * @param matrix
     * @param fr     第一个人走到的行
     * @param fc     第一个人走到的列
     * @param sr     第二个人走到的行
     * @return
     */
    private int process1(int[][] matrix, int fr, int fc, int sr) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (fr == row - 1 && fc == col - 1) {
            return matrix[fr][fc];
        }
        // 第二个走的列 两人同时走 所以走的步数一样 第一个人走了fr+fc 第二人走的行sr
        int sc = fr + fc - sr;
        int ans = Integer.MIN_VALUE;
        // 第一个向下走 第二个向下走
        if (fr + 1 < row && sr + 1 < row) {
            ans = process1(matrix, fr + 1, fc, sr + 1);
        }
        // 第一个向下走 第二个向右走
        if (fr + 1 < row && sc + 1 < col) {
            int process = process1(matrix, fr + 1, fc, sr);
            ans = Math.max(ans, process);
        }
        // 第一个向右走 第二个向下走
        if (fc + 1 < col && sr + 1 < row) {
            int process = process1(matrix, fr, fc + 1, sr + 1);
            ans = Math.max(ans, process);
        }
        // 第一个向右走 第二个向右走
        if (fc + 1 < col && sc + 1 < col) {
            int process = process1(matrix, fr, fc + 1, sr);
            ans = Math.max(ans, process);
        }
        // 如果当前两个人来的是同一个位置只返回一个当前位置值
        if (fr == sr) {
            return matrix[fr][fc] + ans;
        }
        // 当前两人来到不是同一个位置 分别返回两个人值
        return matrix[fr][fc] + matrix[sr][sc] + ans;
    }
    
    public int maxSumPath1Dp(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        
        return process(matrix, 0, 0, false, row, col);
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,1,1},
                {0,0,1,0,1},
                {0,0,1,0,1},
                {0,0,1,1,1}};
        CherryPickup cherryPickup = new CherryPickup();
        System.out.println(cherryPickup.maxSumPath(matrix));
        System.out.println(cherryPickup.maxSumPath1(matrix));
    }
    
    
}
