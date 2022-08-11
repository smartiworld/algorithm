package com.iworld.algorithm.array.matrix;

/**
 * @author gq.cai
 * @version 1.0
 * @description 之字形打印矩阵
 * {{1, 2, 7}
 *  {3, 9, 8}
 *  {5, 7, 9}}
 *  1,2,3,5,9,7,8,7,9
 * @date 2022/8/11 10:37
 */
public class ZigzagMatrix {
    /**
     * 记录两个点 两点划过的轨迹 对两点形成的斜线打印
     * A点从上面划过 B点从下划过 最终到矩阵最后位置
     * @param matrix
     */
    public void zigzag(int[][] matrix) {
        //
        int tr = 0;
        int tc = 0;
        int br = 0;
        int bc = 0;
        int er = matrix.length - 1;
        int ec = matrix[0].length - 1;
        boolean topToBottom = false;
        while (tr <= er && tc <= ec) {
            printZigzag(matrix, tr, tc, br, bc, topToBottom);
            tr = tc == ec ? tr + 1 : tr;
            tc = tc == ec ? tc : tc + 1;
            // bc 依赖br需要先处理bc再处理br
            bc = br == er ? bc + 1 : bc;
            br = br == er ? br : br + 1;
            topToBottom = !topToBottom;
        }
    }
    
    private void printZigzag(int[][] matrix, int tr, int tc, int br, int bc, boolean topToBottom) {
        while (tr <= br && bc <= tc) {
            if (topToBottom) {
                System.out.print(matrix[tr++][tc--]);
            } else {
                System.out.print(matrix[br--][bc++]);
            }
            System.out.print(",");
        }
    }
    
    /**
     * 1, 2, 7, 10
     * 3, 9, 8, 6
     * 5, 7, 9, 15
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 7, 10}, {3, 9, 8, 6}, {5, 7, 9, 15}};
        ZigzagMatrix zigzagMatrix = new ZigzagMatrix();
        zigzagMatrix.zigzag(matrix);
    }
    
}
