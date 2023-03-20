package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 309. Best Time to Buy and Sell Stock with Cooldown
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve.
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @date 2023/3/20 17:13
 */
public class BestTimeToBuyAndSellStockWithCoolDown {
    
    /**
     *  最优尝试如下：
     * 	 buy[i] : 在0...i范围上，最后一次操作是buy动作，
     * 	 这最后一次操作有可能发生在i位置，也可能发生在i之前
     * 	 buy[i]值的含义是：max{ 所有可能性[之前交易获得的最大收益 - 最后buy动作的收购价格] }
     * 	 比如：arr[0...i]假设为[1,3,4,6,2,7,1...i之后的数字不用管]
     * 	 什么叫，所有可能性[之前交易获得的最大收益 - 最后buy动作的收购价格]？
     * 	 比如其中一种可能性：
     * 	 假设最后一次buy动作发生在2这个数的时候，那么之前的交易只能在[1,3,4]上结束，因为6要cooldown的，
     * 	 此时最大收益是多少呢？是4-1==3。那么，之前交易获得的最大收益 - 最后buy动作的收购价格 = 3 - 2 = 1
     * 	 另一种可能性：
     * 	 再比如最后一次buy动作发生在最后的1这个数的时候，那么之前的交易只能在[1,3,4,6,2]上发生，因为7要cooldown的，
     * 	 此时最大收益是多少呢？是6-1==5。那么，之前交易获得的最大收益 - 最后buy动作的收购价格 = 5 - 1 = 4
     * 	 除了上面两种可能性之外，还有很多可能性，你可以假设每个数字都是最后buy动作的时候，
     * 	 所有可能性中，(之前交易获得的最大收益 - 最后buy动作的收购价格)的最大值，就是buy[i]的含义
     * 	 为啥buy[i]要算之前的最大收益 - 最后一次收购价格？尤其是最后为什么要减这么一下？
     * 	 因为这样一来，当你之后以X价格做成一笔交易的时候，当前最好的总收益直接就是 X + buy[i]了
     *
     * 	 sell[i] :0...i范围上，最后一次操作是sell动作，这最后一次操作有可能发生在i位置，也可能发生在之前
     * 	 sell[i]值的含义：0...i范围上，最后一次动作是sell的情况下，最好的收益
     *
     * 	 于是通过分析，能得到以下的转移方程：
     * 	 buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i])
     * 	 如果i位置没有发生buy行为，说明有没有i位置都一样，那么buy[i] = buy[i-1]，这显而易见
     * 	 如果i位置发生了buy行为, 那么buy[i] = sell[i - 2] - prices[i]，
     * 	 因为你想在i位置买的话，你必须保证之前交易行为发生在0...i-2上，
     * 	 因为如果i-1位置有可能参与交易的话，i位置就要cooldown了，
     * 	 而且之前交易行为必须以sell结束，你才能buy，而且要保证之前交易尽可能得到最好的利润，
     * 	 这正好是sell[i - 2]所代表的含义，并且根据buy[i]的定义，最后一定要 - prices[i]
     *
     * 	 sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i])
     * 	 如果i位置没有发生sell行为，那么sell[i] = sell[i-1]，这显而易见
     * 	 如果i位置发生了sell行为，那么我们一定要找到 {之前获得尽可能好的收益 - 最后一次的收购价格尽可能低}，
     * 	 而这正好是buy[i - 1]的含义！之前所有的"尽可能"中，最好的一个！
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        // 最后一次买入 0-i范围获得最大收益的买入方式
        int[] buy = new int[n];
        buy[0] = -prices[0];
        // 0-1范围上只能在0位置或者1位置买入
        buy[1] = Math.max(buy[0], -prices[1]);
        // 0-i范围最好卖出方式
        int[] sell = new int[n];
        // 0-1范围 如果在0位置只能是在0位置买了然后卖 收益0  如果在1位置卖 就是0-0范围buy最大的收益然后加上当前数字
        sell[1] = Math.max(sell[0], buy[0] + prices[1]);
        for (int i = 2; i < n; i++) {
            // 当前i不参与买 和i参与买
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            // i不参与卖出和i卖出 需要buy[0-i]范围最好买入
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }
    
    public int maxProfitOpt(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        // 最后一次买入 0-i范围获得最大收益的买入方式
        int buy = Math.max(-prices[0], -prices[1]);
        int preSell = 0;
        // 数组走过0-i卖出获得最大收益
        int sell = Math.max(0, -prices[0] + prices[1]);;
        for (int i = 2; i < n; i++) {
            int tmpBuy = buy;
            // 当前i不参与买 和i参与买
            buy = Math.max(buy, preSell - prices[i]);
            preSell = sell;
            // i不参与卖出和i卖出 需要buy[0-i]范围最好买入
            sell = Math.max(preSell, tmpBuy + prices[i]);
        }
        return sell;
    }
    
    public int maxProfit1(int[] prices) {
        // 之前没有做买的决定
        return process(prices,0, false, 0);
    }
    
    private int process(int[] prices, int i, boolean buy, int buyPrices) {
        if (i >= prices.length) {
            return 0;
        }
        // 之前已经买了
        if (buy) {
            // 当前位置不卖 走下一个位置去决定卖不卖
            int noSell = process(prices,i + 1, true, buyPrices);
            // 当前位置决定卖 计算收益卖出获得为prices[i]减去之前买所花费的钱buyPrices，然后来到下两个位置去将要买，中间i+1位置需要冷静不能买
            int yesSell = prices[i] - buyPrices + process(prices, i + 2, false,0);
            // 决策当前位置卖和不卖最大值
            return Math.max(noSell, yesSell);
        } else {
            // 之前没有做买的决定 当前可以买可以不买
            // 当前位置没有决定买 需要下个位置决定买
            int noBuy = process(prices, i + 1, false, 0);
            // 当前位置决定
            int yesBuy = process(prices, i+ 1, false, prices[i]);
            // 当前买和当前位置不买决策出最优
            return Math.max(noBuy, yesBuy);
        }
    }
    
}
