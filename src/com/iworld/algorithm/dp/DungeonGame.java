package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description Dungeon Game
 * 174. Dungeon Game
 * Hard
 * 4.9K
 * 87
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid.
 * Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons (represented by negative integers),
 * so the knight loses health upon entering these rooms;
 * other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
 *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 *
 *
 * Example 1:
 *
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 * Example 2:
 *
 * Input: dungeon = [[0]]
 * Output: 1
 *
 * Constraints:
 *
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 * https://leetcode.com/problems/dungeon-game/
 * 骑士从左上角出发 每次只能向右或者向左走最后到达右下角见到公主
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class02/Code05_DungeonGame.java
 * @date 2023/2/17 13:23
 */
public class DungeonGame {
    
    public int calculateMinimumHP(int[][] dungeon) {
        return process(dungeon, 0, 0, dungeon.length - 1, dungeon[0].length - 1);
    }
    
    private int process(int[][] dungeon, int r, int c, int row, int col) {
        // 来到了最后一个位置
        if (r == row && c == col) {
            return dungeon[r][c] < 0 ? 1 - dungeon[r][c] : 1;
        }
        int cur = dungeon[r][c];
        // 来到了最后一行
        int minNextNeed;
        if (r == row) {
            minNextNeed = process(dungeon, r, c + 1, row, col);
        } else if (c == col) {
            // 来到了最后一列
            minNextNeed = process(dungeon, r + 1, c, row, col);
        } else {
            minNextNeed = Math.min(process(dungeon, r + 1, c, row, col), process(dungeon, r, c + 1, row, col));
        }
        if (cur < 0) {
            // 当前位置是负数 当前位置需要打败才可以走下一个位置
            return -cur + minNextNeed;
        } else if (cur >= minNextNeed) {
            return 1;
        } else {
            return minNextNeed - cur;
        }
    }
    
    public int calculateMinimumHPDp(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = dungeon[row - 1][col - 1] < 0 ? 1 - dungeon[row - 1][col - 1] : 1;
        for (int i = row - 2; i >= 0; i--) {
            int next = dp[i + 1][col - 1];
            int cur = dungeon[i][col - 1];
            setDp(dp, cur, next, i, col - 1);
        }
        for (int j = col - 2; j >= 0; j--) {
            int next = dp[row - 1][j + 1];
            int cur = dungeon[row - 1][j];
            setDp(dp, cur, next, row - 1, j);
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                int next = Math.min(dp[i][j + 1], dp[i + 1][j]);
                int cur = dungeon[i][j];
                setDp(dp, cur, next, i, j);
            }
        }
        return dp[0][0];
    }
    
    private void setDp(int[][] dp, int cur, int next, int r, int c) {
        int setDp;
        if (cur < 0) {
            setDp = next - cur;
        } else if (cur >= next) {
            // 当前可以覆盖后面掉的血量 最少使用1滴血过关
            setDp = 1;
        } else {
            // 当前位置需要补差值  可以走到下一个格子可以过关
            setDp = next - cur;
        }
        dp[r][c] = setDp;
    }
    
    public static void main(String[] args) {
        //int[][] dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        int[][] dungeon = {{2}, {1}};
        DungeonGame dungeonGame = new DungeonGame();
        int i = dungeonGame.calculateMinimumHP(dungeon);
        System.out.println(i);
        int i2 = dungeonGame.calculateMinimumHPDp(dungeon);
        System.out.println(i2);
    }
    
}
