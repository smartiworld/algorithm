package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 322. Coin Change
 * Medium
 * 14210
 * 316
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 * https://leetcode.com/problems/coin-change/
 * @date 2022/10/12 17:26
 */
public class CoinChange {
    
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int count = process(coins, 0, amount, 0);
        return count == Integer.MAX_VALUE ? -1 : count;
    }
    
    private int process(int[] coins, int index, int restMount, int count) {
        if (restMount == 0) {
            return count;
        }
        if (restMount < 0 || index == coins.length) {
            return Integer.MAX_VALUE;
        }
        int use = process(coins, index, restMount - coins[index], count + 1);
        int no = process(coins, index + 1, restMount, count);
        return Math.min(use, no);
    }
    
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int count = process(coins, 0, amount);
        return count == Integer.MAX_VALUE ? -1 : count;
    }
    
    private int process(int[] coins, int index, int restMount) {
        if (restMount == 0) {
            return 0;
        }
        if (restMount < 0 || index == coins.length) {
            return Integer.MAX_VALUE;
        }
        int use = process(coins, index, restMount - coins[index]);
        if (use != Integer.MAX_VALUE) {
            use += 1;
        }
        int no = process(coins, index + 1, restMount);
        return Math.min(use, no);
    }
    
    public int coinChangeDp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int j = 0; j <= amount; j++) {
            dp[n][j] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= amount; j++) {
                int use = Integer.MAX_VALUE;
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    use = dp[i][j - coins[i]] + 1;
                }
                dp[i][j] = Math.min(use, dp[i + 1][j]);
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
    
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(coins, amount));
        System.out.println(coinChange.coinChange2(coins, amount));
        System.out.println(coinChange.coinChangeDp(coins, amount));
    }
}
