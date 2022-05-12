package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 动态规划 机器人指定步数走向目标
 * 排列一行N个位置，记位1~N，N一定大于等于2，开始时机器人人在其中M位置(M一定在1~N中间)
 * 机器人来的1位置，下一步只能走向2位置，如果来的N位置，下一步必须走向N-1，如果在中间位置，下一步可以向左或者右
 * 规定机器人必须走K步，求最终来到P位置的方法有多少种(P在1~N范围)
 * 给定四个参数N M K P
 * @date 2022/5/11 17:45
 */
public class DPRobotMoveToTarget {

    public static int moveToTarget(int n, int m, int k, int p) {
        if (n < 2 || m > n || m < 1 || k <=0 || p < 1 || p > n) {
            return 0;
        }
        return process(n, p, k, m);
    }
    
    /**
     * 递归处理当前走到什么位置
     * @param n       总长度
     * @param p       目标位置
     * @param step    剩余步数
     * @param target  当前所处位置
     * @return
     */
    private static int process(int n, int p, int step, int target) {
        // 步数已经走完
        if (step == 0) {
            // 如果当前位置已经到目标位置返回1 表示有解
            // 如果并且没来到当前位置返回0 表示无解
            return p == target ? 1 : 0;
        }
        int result = 0;
        if (target == n) {
            // 在最后位置 只能向前走target - 1 步数-1
            result = process(n, p, step - 1, target - 1);
        }else if (target == 1) {
            // 在第一个位置  只能向后一步走2  步数-1
            result = process(n, p, step - 1, 2);
        } else {
            // 非头尾位置 可以向前或者向后走，前后是不同结果，前后走的结果相加
            // 向左走target-1  步数-1
            result += process(n, p, step - 1, target - 1);
            // 向右走target+1  步数-1
            result += process(n, p, step - 1, target + 1);
        }
        return result;
    }
    
    /**
     * 记忆化搜索
     * @param n
     * @param m
     * @param k
     * @param p
     * @return
     */
    public static int moveToTargetCache(int n, int m, int k, int p) {
        if (n < 2 || m > n || m < 1 || k <=0 || p < 1 || p > n) {
            return 0;
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return processCache(n, p, k, m, dp);
    }
    
    /**
     * 递归处理当前走到什么位置 记忆化搜索
     * @param n       总长度
     * @param p       目标位置
     * @param step    剩余步数
     * @param target  当前所处位置
     * @return
     */
    private static int processCache(int n, int p, int step, int target, int[][] dp) {
        // 当前缓存中有此步数和位置的值
        if (dp[step][target] != -1) {
            return dp[step][target];
        }
        if (step == 0) {
            // 如果当前位置已经到目标位置返回1 表示有解
            // 如果并且没来到当前位置返回0 表示无解
            dp[step][target] = p == target ? 1 : 0;
            return dp[step][target];
        }
        if (target == n) {
            // 在最后位置 只能向前走target - 1 步数-1
            dp[step][target] = process(n, p, step - 1, target - 1);
        }else if (target == 1) {
            // 在第一个位置  只能向后一步走2  步数-1
            dp[step][target] = process(n, p, step - 1, 2);
            
        } else {
            // 非头尾位置 可以向前或者向后走，前后是不同结果，前后走的结果相加
            // 向左走target-1  步数-1
            dp[step][target] = process(n, p, step - 1, target - 1)
                    + process(n, p, step - 1, target + 1);
        }
        return dp[step][target];
    }
    
    /**
     * 动态规划 改动态规划参照暴力递归步骤
     * target当前走到的位置  step 剩余步数
     * target==1的时候当前位置依赖target+1 step-1的位置
     * target==n的时候当前位置依赖target-1 step-1的位置
     * 其他位置 依赖target+1 step-1 + target-1 step-1
     * 最终使用递归主函数调用传入的step和target
     * 变化参数为步数step和走的位置target 构建二维数组
     * @param n       总长度
     * @param m       起始位置
     * @param k       总步数
     * @param p       目标位置
     * @return
     */
    public static int moveToTargetDp(int n, int m, int k, int p) {
        if (n < 2 || m > n || m < 1 || k <=0 || p < 1 || p > n) {
            return 0;
        }
        int[][] dp = new int[k + 1][n + 1];
        dp[0][p] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == n) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        // 求走k步起始m位置的值
        return dp[k][m];
    }
    
    public static void main(String[] args) {
        int n = 5;
        int m = 2;
        int k = 4;
        int p = 4;
        System.out.println(moveToTarget(n, m, k, p));
        System.out.println(moveToTargetCache(n, m, k, p));
        System.out.println(moveToTargetDp(n, m, k, p));
    }
    
}
