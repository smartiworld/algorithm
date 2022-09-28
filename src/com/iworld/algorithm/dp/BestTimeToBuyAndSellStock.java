package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description  121. Best Time to Buy and Sell Stock
 * Easy
 * 20319
 * 653
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * @date 2022/9/28 20:38
 */
public class BestTimeToBuyAndSellStock {
    
    /**
     * 过程记录最小值 让每个值和当前经过最小值做减 找出最大值
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            ans = Math.max(ans, prices[i] - min);
            min = Math.min(prices[i], min);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        int[] prices = {7,6,4,3,1};
        System.out.println(bestTimeToBuyAndSellStock.maxProfit(prices));
    }
}
