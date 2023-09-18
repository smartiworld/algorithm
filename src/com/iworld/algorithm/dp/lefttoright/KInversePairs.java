package com.iworld.algorithm.dp.lefttoright;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.6.3 k个逆序对
 * 给定一个正数N，代表你有1～N这些数字。在给定一个整数K。
 * 你可以随意排列这些数字，但是每一种排列都有若干个逆序对。返回有多少种排列，正好有K个逆序对
 *
 * 例子1:
 * Input: n = 3, k = 0
 * Output: 1
 * 解释：
 * 只有[1,2,3]这一个排列有0个逆序对。
 *
 * 例子2:
 * Input: n = 3, k = 1
 * Output: 2
 * 解释
 * [3,2,1]有(3,2)、(3,1)、(2,1)三个逆序对，所以不达标。
 * 达标的只有：
 * [1,3,2]只有(3,2)这一个逆序对，所以达标。
 * [2,1,3]只有(2,1)这一个逆序对，所以达标。
 * @date 2023/9/8 21:15
 */
public class KInversePairs {

    public int kInversePairsCountDp(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        // dp[i][j] 当前0~i的范围上 有j个逆序对 有几种
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int l = j; l >= Math.max(j - i + 1, 0); l--) {
                    dp[i][j] += dp[i - 1][l];
                }
            }
        }
        return dp[n][k];
    }
    
    /**
     * 存在枚举行为
     * i>j
     * dp[6][5]=dp[5][0~5]
     * dp[6][6]=dp[5][0~5]+dp[5][6]->dp[6][6]=dp[6][5]+dp[5][6]
     * dp[i][j]=dp[i-1][j]+dp[i][j-1]
     * i<=j
     * dp[3][5]=[dp[2][5~3]=dp[2][5]+dp[2][4]]+dp[2][3]
     * dp[3][6]=dp[2][6~4]=dp[2][6]+[dp[2][5]+dp[2][4]]=dp[2][6]+dp[3][5]-dp[2][3]
     * dp[i][j]=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-i]
     * @param n
     * @param k
     * @return
     */
    public int kInversePairsCountDpOpt(int n, int k) {
        // dp[i][j] 当前0~i的范围上 有j个逆序对 有几种
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
                if (i <= j) {
                    dp[i][j] -= dp[i - 1][j - i];
                }
            }
        }
        return dp[n][k];
    }
    
    public static void main(String[] args) {
        KInversePairs kInversePairs = new KInversePairs();
        int n = 1000;
        int k = 1000;
        System.out.println(kInversePairs.kInversePairsCountDp(n, k));
        System.out.println(kInversePairs.kInversePairsCountDpOpt(n, k));
    }
}
