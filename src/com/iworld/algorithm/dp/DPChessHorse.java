package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 动态规划
 * 给定一个位置 马从初始(0,0)位置出发给定固定步数 返回马从初始位置到给定位置 有多少种不同走法
 * 棋盘9*10
 * @date 2022/6/9 13:59
 */
public class DPChessHorse {
    
    public static int jumTargetCount(int x, int y, int step) {
        return process(x, y, step);
    }
    
    /**
     * x代表横向 y代表纵向
     * @param x     横向 0-8
     * @param y     纵向 0-9
     * @param step  剩余步数
     * @return
     */
    public static int process(int x, int y, int step) {
        if (step == 0) {
            // 在给定的步数 走到目标位置
            return x == 0 && y == 0 ? 1 : 0;
        }
        // 跳出棋盘外了
        if (x < 0 || y < 0 || x > 9 || y > 10) {
            return 0;
        }
        // 在不考虑棋盘大小的情况下 有八个位置可以一步跳到当前位置
        return process(x + 1, y - 2, step - 1)
                + process(x + 2, y - 1, step - 1)
                + process(x + 2, y + 1, step - 1)
                + process(x + 1, y + 2, step - 1)
                + process(x - 1, y + 2, step - 1)
                + process(x - 2, y + 1, step - 1)
                + process(x - 2, y - 1, step - 1)
                + process(x - 1, y - 2, step - 1);
    }
    
    public static int jumTargetCount2(int x, int y, int step) {
        return process2(0, 0, x, y, step);
    }
    
    /**
     * x代表横向 y代表纵向
     * @param x     横向 0-8
     * @param y     纵向 0-9
     * @param step  剩余步数
     * @return
     */
    public static int process2(int curX, int curY, int x, int y, int step) {
        if (step == 0) {
            // 在给定的步数 走到目标位置
            return curX == x && curY == y ? 1 : 0;
        }
        // 跳出棋盘外了
        if (curX < 0 || curY < 0 || curX > 9 || curY > 10) {
            return 0;
        }
        // 在不考虑棋盘大小的情况下 有八个位置可以一步跳到当前位置
        return process2(curX + 1, curY - 2, x, y, step - 1)
                + process2(curX + 2, curY - 1, x, y,step - 1)
                + process2(curX + 2, curY + 1, x, y,step - 1)
                + process2(curX + 1, curY + 2, x, y,step - 1)
                + process2(curX - 1, curY + 2, x, y,step - 1)
                + process2(curX - 2, curY + 1, x, y,step - 1)
                + process2(curX - 2, curY - 1, x, y,step - 1)
                + process2(curX - 1, curY - 2, x, y,step - 1);
    }
    
    /**
     * 整个棋盘9x10
     * @param x
     * @param y
     * @param step
     * @return
     */
    public static int jumTargetCountDp(int x, int y, int step) {
        int[][][] dp = new int[10][11][step + 1];
        dp[0][0][0] = 1;
        for (int k = 1; k <= step; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 11; j++) {
                    dp[i][j][k] = getDpValue(i + 1, j - 2, k - 1, dp)
                            + getDpValue(i + 1, j + 2, k - 1, dp)
                            + getDpValue(i + 2, j - 1, k - 1, dp)
                            + getDpValue(i + 2, j + 1, k - 1, dp)
                            + getDpValue(i - 1, j - 2, k - 1, dp)
                            + getDpValue(i - 1, j + 2, k - 1, dp)
                            + getDpValue(i - 2, j - 1, k - 1, dp)
                            + getDpValue(i - 2, j + 1, k - 1, dp);
                }
            }
        }
        return dp[x][y][step];
    }
    
    private static int getDpValue (int x, int y, int step, int[][][] dp) {
        if (x < 0 || y < 0 || x > 9 || y > 10) {
            return 0;
        }
        return dp[x][y][step];
    }
    
    public static void main(String[] args) {
        int x = 3;
        int y = 2;
        int step = 7;
        System.out.println(jumTargetCount(x, y, step));
        System.out.println(jumTargetCount2(x, y, step));
        System.out.println(jumTargetCountDp(x, y, step));
    }
}
