package com.iworld.algorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 从项目中赚取更多的钱
 * 给一定的初始资金，一定项目 做项目启动资金和收益金额，项目一个一个的做不可以同时做,最多可以赚多少钱
 * @date 2022/4/10 11:37
 */
public class ObtainMostMoney {
    
    /**
     * 从固定数量项目中获取最大收益
     * 先按照花费金额倒序排序 取最小的小根堆花费金额和拥有金额比较 小于等于表示可以做，然后将可以做的项目放入收益大根堆中，
     * 取收益最高的项目来做，每次做一个项目，然后再从未做项目中找是否可以做的项目，可做项目重复放入收益大根堆中，重复
     * 最后无项目可做返回
     * @param k       最多可做项目
     * @param w       初始金额
     * @param costs   花费金额
     * @param profits 收益金额
     * @return
     */
    public int obtainMostMoneyFromProject(int k, int w, int[] costs, int[] profits) {
        if (costs == null || costs.length == 0 || profits == null || profits.length == 0) {
            return w;
        }
        // 花费金额小根堆
        PriorityQueue<Project> costQueue = new PriorityQueue<>(new CostComparator());
        // 收益金额大根堆
        PriorityQueue<Project> profitsQueue = new PriorityQueue<>(new ProfitsComparator());
        for (int i = 0; i < costs.length; i++) {
            costQueue.add(new Project(costs[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            // 将当前所有可以做的项目放入收益大根堆 首先做收益比较大的项目
            while (!costQueue.isEmpty() && costQueue.peek().cost <= w) {
                profitsQueue.add(costQueue.poll());
            }
            if (profitsQueue.isEmpty()) {
                return w;
            }
            w += profitsQueue.poll().profit;
        }
        return w;
    }
    
    public static void main(String[] args) {
        ObtainMostMoney obtainMostMoney = new ObtainMostMoney();
        int[] costs = {1, 5, 15, 20, 100};
        int[] profits = {5, 10, 12, 15, 10};
        int k = 2;
        int w = 10;
        int i = obtainMostMoney.obtainMostMoneyFromProject(k, w, costs, profits);
        System.out.println(i);
    }
    
    private static class CostComparator implements Comparator<Project> {
    
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }
    
    private static class ProfitsComparator implements Comparator<Project> {
    
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    }

    public static class Project {
        int cost;
        int profit;
        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
}
