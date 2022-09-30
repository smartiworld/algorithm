package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 123. Best Time to Buy and Sell Stock III
 * Hard
 * 7062
 * 140
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later,
 * as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * @date 2022/9/29 20:23
 */
public class BestTimeToBuyAndSellStockIII {
    
    public int maxProfit(int[] prices) {
        // 0-i位置最小
        int min = prices[0];
        // 第一次交易可以获得的最大值
        int firstSellMax = 0;
        // 第二次买获得的收益 初始0位置为负数  把前一次交易赚的钱放入当前值
        int secondBuyMax = -prices[0];
        // 第二次卖出获得收益
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            // 当前位置加第二次买后剩余 为当前位置第二次卖所获收益
            ans = Math.max(ans, prices[i] + secondBuyMax);
            // 当前位置pk min 找出最小值
            min = Math.min(min, prices[i]);
            // 第一次卖出获得最大收益 当前位置-（0-i的最小值）
            firstSellMax = Math.max(firstSellMax, prices[i] - min);
            // 第二次买入后收益 为第一次卖出获得最大收益-当前位置价格
            secondBuyMax = Math.max(firstSellMax - prices[i], secondBuyMax);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII bestTimeToBuyAndSellStockIII = new BestTimeToBuyAndSellStockIII();
        int[] prices = {7,6,4,3,1};
        System.out.println(bestTimeToBuyAndSellStockIII.maxProfit(prices));
    }
}
