package com.iworld.algorithm.dp;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 贪吃蛇游戏
 * 一个二维数组每个格子可以为正可以为负，一只小蛇从第一列任何任何位置出发，遇到正数直接增加长度，负数减少长度 走下一个位置时只能走右上，右和右下
 * 小蛇有个超能力，能使当前位置值 变为相反数，只能使用一次超能力 任何时候长度为负数则结束
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class05/Code02_SnakeGame.java
 * @date 2023/3/3 18:10
 */
public class SnakeGame {
    
    /**
     * 需要走到数组最后一列
     * @param matrix
     * @return
     */
    public int walkEnd(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int ans = -1;
        for (int i = 0; i < row; i++) {
            int[] next = process(matrix, i, 0, row, col);
            ans = Math.max(ans, Math.max(next[0], next[1]));
        }
        return ans;
    }
    
    /**
     * 返回结果 数组第一个是没有使用超能力 第二个位置是没有使用超能力
     * @param matrix
     * @param r
     * @param c
     * @param row
     * @param col
     * @return
     */
    private int[] process(int[][] matrix, int r, int c, int row, int col) {
        if (c == col - 1) {
            return new int[]{matrix[r][c], -matrix[r][c]};
        }
        int[] next = process(matrix, r, c + 1, row, col);
        int nextNoUse = next[0];
        int nextUse = next[1];
        if (r - 1 >= 0) {
            int[] topNext = process(matrix, r - 1, c + 1, row, col);
            nextNoUse = Math.max(nextNoUse, topNext[0]);
            nextUse = Math.max(nextUse, topNext[1]);
        }
        if (r + 1 < row) {
            int[] bottomNext = process(matrix, r + 1, c + 1, row, col);
            nextNoUse = Math.max(nextNoUse, bottomNext[0]);
            nextUse = Math.max(nextUse, bottomNext[1]);
        }
        int noUse = matrix[r][c];
        int use = -matrix[r][c];
        // 当前使用超能力 可能在当前位置使用也可能在后面位置使用
        use = use + nextNoUse;
        use = Math.max(noUse + nextUse, use);
        // 当前位置不使用 当前位置和以后位置都不会使用
        noUse = noUse + nextNoUse;
        return new int[]{noUse, use};
    }
    
    public int walkEndDp(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int ans = -1;
        int[][] useDp = new int[row][col];
        int[][] unUseDp = new int[row][col];
        for (int i = 0; i < row; i++) {
            useDp[i][0] = -matrix[i][0];
            unUseDp[i][0] = matrix[i][0];
        }
        for (int j = 1; j < col; j++) {
            for (int i = 0; i < row; i++) {
                int preUse = useDp[i][j - 1];
                int preUnUse = unUseDp[i][j - 1];
                if (i - 1 >= 0) {
                    preUse = Math.max(preUse, useDp[i - 1][j - 1]);
                    preUnUse = Math.max(preUnUse, unUseDp[i - 1][j - 1]);
                }
                if (i + 1 < row) {
                    preUse = Math.max(preUse, useDp[i + 1][j - 1]);
                    preUnUse = Math.max(preUnUse, unUseDp[i + 1][j - 1]);
                }
                // 当前位置包含之前位置都不使用超能力
                int unUse = matrix[i][j];
                // 当前位置或者之前位置使用过超能力
                int use = -matrix[i][j];
                use += preUnUse;
                use = Math.max(use, unUse + preUse);
                unUse += preUnUse;
                useDp[i][j] = use;
                unUseDp[i][j] = unUse;
            }
        }
        for (int i = 0; i < row; i++) {
            ans = Math.max(ans, Math.max(useDp[i][col - 1], unUseDp[i][col - 1]));
        }
        return ans;
    }
    
    /**
     * 任何数组位置停下
     * @param matrix
     * @return
     */
    public static int walk(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] ans = process(matrix, i, j);
                res = Math.max(res, Math.max(ans[0], ans[1]));
            }
        }
        return res;
    }
    
    // 从假想的最优左侧到达(i,j)的旅程中
    // 0) 在没有使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
    // 1) 在使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
    public static int[] process(int[][] m, int i, int j) {
        if (j == 0) { // (i,j)就是最左侧的位置
            return new int[] { m[i][j], -m[i][j] };
        }
        int[] preAns = process(m, i, j - 1);
        // 所有的路中，完全不使用能力的情况下，能够到达的最好长度是多大
        int preUnuse = preAns[0];
        // 所有的路中，使用过一次能力的情况下，能够到达的最好长度是多大
        int preUse = preAns[1];
        if (i - 1 >= 0) {
            preAns = process(m, i - 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        if (i + 1 < m.length) {
            preAns = process(m, i + 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        // preUnuse 之前旅程，没用过能力
        // preUse 之前旅程，已经使用过能力了
        int no = -1; // 之前没使用过能力，当前位置也不使用能力，的最优解
        int yes = -1; // 不管是之前使用能力，还是当前使用了能力，请保证能力只使用一次，最优解
        if (preUnuse >= 0) {
            no = m[i][j] + preUnuse;
            yes = -m[i][j] + preUnuse;
        }
        if (preUse >= 0) {
            yes = Math.max(yes, m[i][j] + preUse);
        }
        return new int[] { no, yes };
    }
    
    public static int walkDp(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int ans = -1;
        int[][] useDp = new int[row][col];
        int[][] unuseDp = new int[row][col];
        for (int i = 0; i < row; i++) {
            unuseDp[i][0] = matrix[i][0];
            useDp[i][0] = -matrix[i][0];
            ans = Math.max(ans, Math.max(useDp[i][0], unuseDp[i][0]));
        }
        for (int j = 1; j < col; j++) {
            for (int i = 0; i < row; i++) {
                int preUse = useDp[i][j - 1];
                int preUnuse = unuseDp[i][j - 1];
                if (i - 1 >= 0) {
                    preUse = Math.max(preUse, useDp[i - 1][j - 1]);
                    preUnuse = Math.max(preUnuse, unuseDp[i - 1][j - 1]);
                }
                if (i + 1 < row) {
                    preUse = Math.max(preUse, useDp[i + 1][j - 1]);
                    preUnuse = Math.max(preUnuse, unuseDp[i + 1][j - 1]);
                }
                int unuse = -1; // 之前没使用过能力，当前位置也不使用能力，的最优解
                int use = -1; // 不管是之前使用能力，还是当前使用了能力，请保证能力只使用一次，最优解
                if (preUnuse >= 0) {
                    unuse = matrix[i][j] + preUnuse;
                    use = -matrix[i][j] + preUnuse;
                }
                if (preUse >= 0) {
                    use = Math.max(use, matrix[i][j] + preUse);
                }
                useDp[i][j] = use;
                unuseDp[i][j] = unuse;
                ans = Math.max(ans, Math.max(useDp[i][j], unuseDp[i][j]));
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        int[][] matrix1 = {
//                {3, 6, 5, 2, 9},
//                {8, 3, 5, 5, 8},
//                {0, 4, 0, 9, -1},
//                {2, 8, 4, -6, -1}
                {-4, -7, -9, 9, 6}
        };
        int ans11 = walk(matrix1);
        int ans44 = walkDp(matrix1);
        System.out.println(ans11);
        System.out.println(ans44);
        int ans22 = snakeGame.walkEnd(matrix1);
        int ans33 = snakeGame.walkEndDp(matrix1);
        System.out.println(ans22);
        System.out.println(ans33);
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[][] matrix = generateRandomArray(5, 5, 10);
            int ans1 = walk(matrix);
            int ans4 = walkDp(matrix);
            int ans2 = snakeGame.walkEnd(matrix);
            int ans3 = snakeGame.walkEndDp(matrix);
//            if (ans1 != ans2) {
//                for (int j = 0; j < matrix.length; j++) {
//                    System.out.println(Arrays.toString(matrix[j]));
//                }
//                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
//                break;
//            }
            if (ans2 != ans3) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans2: " + ans2 + "   ans3:" + ans3);
                break;
            }
            if (ans1 != ans4) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans4:" + ans4);
                break;
            }
        }
        System.out.println("finish");
    }
    
    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[(int) (Math.random() * row) + 1][(int) (Math.random() * col) + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }
}
