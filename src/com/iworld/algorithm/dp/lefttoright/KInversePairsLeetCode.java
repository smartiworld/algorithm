package com.iworld.algorithm.dp.lefttoright;

/**
 * @author gq.cai
 * @version 1.0
 * @description 629. K Inverse Pairs Array
 * Hard
 * 2K
 * 220
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
 *
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.
 * Since the answer can be huge, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
 * Example 2:
 *
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 * https://leetcode.com/problems/k-inverse-pairs-array/
 * @date 2023/9/8 21:15
 */
public class KInversePairsLeetCode {

    public int kInversePairsCountDp(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        int mod = 1000000007;
        // dp[i][j] 当前0~i的范围上 有j个逆序对 有几种
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int l = j; l >= Math.max(j - i + 1, 0); l--) {
                    dp[i][j] += dp[i - 1][l];
                    dp[i][j] %= mod;
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
        if (n < 1 || k < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        // dp[i][j] 当前0~i的范围上 有j个逆序对 有几种
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                if (j >= i) {
                    // + mod 防止dp[i][j] < dp[i - 1][j - i]
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }
    
    
    public static void main(String[] args) {
        KInversePairsLeetCode kInversePairs = new KInversePairsLeetCode();
        int n = 1000;
        int k = 1000;
        System.out.println(kInversePairs.kInversePairsCountDp(n, k));
        System.out.println(kInversePairs.kInversePairsCountDpOpt(n, k));
    }
}
