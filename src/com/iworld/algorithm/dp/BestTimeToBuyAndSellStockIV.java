package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 188. Best Time to Buy and Sell Stock IV
 * Hard
 * 5675
 * 191
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * Constraints:
 *
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * @date 2022/9/30 12:44
 */
public class BestTimeToBuyAndSellStockIV {
    
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k >= (n >> 1)) {
            return mostBuyAndSell(prices);
        }
        return getDp(k, prices);
    }
    
    private int getDp(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][k + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 当前位置不参与交易
                dp[i][j] = dp[i - 1][j];
                // i位置参与卖出 0~i p代表最后买入时机
                for (int p = 0; p <= i; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[p][j - 1] - prices[p] + prices[i]);
                }
            }
        }
        return dp[n - 1][k];
    }
    
    /**
     * dp[i][j]表示0-1范围交易j次最大收益
     * dp[3][1] 枚举行为
     * prices[0]+prices[3]算作一次交易 前只能是j-1次
     * dp[0][0]-prices[0]+prices[3]
     * dp[1][0]-prices[1]+prices[3]
     * dp[2][0]-prices[2]+prices[3]
     * dp[3][0]-prices[3]+prices[3]
     * dp[4][1] 枚举行为
     * prices[0]+prices[3]算作一次交易 前只能是j-1次
     * dp[0][0]-prices[0]+prices[4]
     * dp[1][0]-prices[1]+prices[4]
     * dp[2][0]-prices[2]+prices[4]
     * dp[3][0]-prices[3]+prices[4]
     * dp[4][0]-prices[4]+prices[4]
     * 当前位置提出自己位置 pre=dp[4][0]
     * 除去当前位置卖 之前最好收益需要减去当前买
     * 当前位置最好交易 则为当前位置不交易dp[j][i - 1] 和当前位置卖出  prices[i] + best最大值
     * best=dp[4][0]-prices[4]
     * 存在枚举行为
     * @param k
     * @param prices
     * @return
     */
    private int getDp2(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[k + 1][n];
        int ans = 0;
        for (int j = 1; j <= k; j++) {
            int pre = dp[j][0];
            // 前位置买最优
            int best = pre - prices[0];
            for (int i = 1; i < n; i++) {
                // 0-i位置j-1次交易最优
                pre = dp[j - 1][i];
                // best 0~i-1位置最优买入 +prices[i]表示0-1位置整个交易最优
                dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);
                // pre - prices[i]当前买入 和前买入最优
                best = Math.max(best, pre - prices[i]);
                ans = Math.max(dp[j][i], ans);
            }
        }
        return ans;
    }
    
    private int mostBuyAndSell(int[] prices) {
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - min);
            min = prices[i];
        }
        return ans;
    }
    
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIV bestTimeToBuyAndSellStockIV = new BestTimeToBuyAndSellStockIV();
        int k = 2;
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(bestTimeToBuyAndSellStockIV.maxProfit(k, prices));
    }
}
