package com.iworld.algorithm.greedy;

import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 切割满足给定条件金条花费最小代价
 * 一条金块被切成两半 是需要花费原金条长度的价值
 * 一条20长度金条，切割一半是需要花费20元
 * 给定一个数组{10,20,30}怎么切割一条60金条 切割到和数组中一样长度，花费最小
 * @date 2022/4/6 23:05
 */
public class SplitGoldUseLessMoney {
    
    public int lessMoney(int[] golds) {
        if (golds== null || golds.length < 1) {
            return 0;
        }
        return process(golds, 0);
    }
    
    public int process(int[] golds, int sum) {
        if (golds.length == 1) {
            return sum;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < golds.length; i++) {
            for (int j = i + 1; j < golds.length; j++) {
                result = Math.min(result, process(mergeArray(golds, i, j), sum + golds[i] + golds[j]));
            }
        }
        return result;
    }
    
    /**
     * 将数组中i和j位置sum 将sum后结果放数组中
     * @param golds
     * @param i
     * @param j
     * @return
     */
    private int[] mergeArray(int[] golds, int i, int j) {
        int[] newGolds = new int[golds.length -1];
        int s = 0;
        for (int k = 0; k < golds.length; k++) {
            if (k != i && k != j) {
                newGolds[s ++] = golds[k];
            }
        }
        newGolds[s] = golds[i] + golds[j];
        return newGolds;
    }
    
    public int  lessMoneyUseGreedy(int[] golds) {
        if (golds== null || golds.length < 1) {
            return 0;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < golds.length; i++) {
            priorityQueue.add(golds[i]);
        }
        int result = 0;
        while (priorityQueue.size() > 1) {
            Integer left = priorityQueue.poll();
            Integer right = priorityQueue.poll();
            int sum = left + right;
            result += sum;
            priorityQueue.add(sum);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] golds = {50, 20, 30, 10};
        SplitGoldUseLessMoney splitGoldUseLessMoney = new SplitGoldUseLessMoney();
        int i = splitGoldUseLessMoney.lessMoneyUseGreedy(golds);
        System.out.println(i);
        int i1 = splitGoldUseLessMoney.lessMoney(golds);
        System.out.println(i1);
    }
}
