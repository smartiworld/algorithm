package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 动态规划  清洗咖啡杯  有序数组
 * 一个数组代表每个人喝完咖啡准备洗杯子的时间 只有一台咖啡机 一次只能洗一个杯子 洗杯子耗时a
 * 洗完才可以洗下一个杯子，每个咖啡杯子也可以自然挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 如果选择了洗杯子 则挥发时间不会影响，返回所有咖啡杯变干净的最早时间
 * 三个参数int[] arr, int a, int b
 * @date 2022/5/18 22:13
 */
public class DPWashCoffeeCup {
    
    public static int minWashTime(int[] arr, int a, int b) {
        if (arr.length == 0) {
            return 0;
        }
        if (a > b) {
            return arr[arr.length - 1] + b;
        }
        return process(arr, a, b, 0, 0);
    }
    
    /**
     * 当前来到index位置杯子 前面0~index-1已经处理完毕
     * 当前位置两种可能情况 洗杯子和不洗杯子
     * @param arr        待洗杯子时间
     * @param a          洗杯子时间
     * @param b         杯子挥发时间
     * @param index     当前来到杯子位置
     * @param washTime  当前咖啡机可以洗的时间
     * @return
     */
    private static int process(int[] arr, int a, int b, int index, int washTime) {
        if (index == arr.length - 1) {
            // 需要洗的时间 和机器可以洗的时间取最大值 然后加上洗杯子花费时间
            int wash = Math.max(washTime, arr[index]) + a;
            // 不洗 等当前杯子可以洗的时间加挥发时间
            int unWash = arr[index] + b;
            // 两种情况取小 即为花费最小时间
            return Math.min(wash, unWash);
        }
        // 洗当前的杯子结束的时间
        int washCurrentTime = Math.max(arr[index], washTime) + a;
        // 洗当前杯子 后续杯子变干净处理最小时间
        int washNextTime = process(arr, a, b, index + 1, washCurrentTime);
        // 当前杯子洗的时间 和后续时间取大  为杯子变干净需要的时间
        int washMaxTime = Math.max(washCurrentTime, washNextTime);
        // 不洗当前杯子需要时间
        int unWashCurrentTime = arr[index] + b;
        // 不洗当前杯子 后续杯子变干净的时间
        int unWashNextTime = process(arr, a, b, index + 1, washTime);
        // 当前杯子不洗的时间 和后续时间取大  为杯子变干净需要的时间
        int unWashMaxTime = Math.max(unWashCurrentTime, unWashNextTime);
        // 从两种情况中选出使用时间小的
        return Math.min(washMaxTime, unWashMaxTime);
    }
    
    public static int minWashTimeDp(int[] arr, int a, int b) {
        int length = arr.length;
        if (length == 0) {
            return 0;
        }
        if (a > b) {
            return arr[length - 1] + b;
        }
        // 所有杯子都洗的情况
        int allWashTime = 0;
        for (int i = 0; i < length; i++) {
            allWashTime = Math.max(allWashTime, arr[i]) + a;
        }
        int[][] dp = new int[length][allWashTime + 1];
        // 最后一个位置杯子最小时间
        for (int i = 0; i <= allWashTime; i++) {
            dp[length - 1][i] = Math.min(Math.max(i, arr[length - 1]) + a, arr[length - 1] + b);
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = 0; j <= allWashTime ; j++) {
                // 洗当前的杯子结束的时间
                int washCurrentTime = Math.max(arr[i], j) + a;
                int washMaxTime = washCurrentTime;
                if (washCurrentTime <= allWashTime) {
                    // 洗当前杯子 后续杯子变干净处理最小时间
                    int washNextTime = dp[i + 1][washCurrentTime];
                    // 当前杯子洗的时间 和后续时间取大  为杯子变干净需要的时间
                    washMaxTime = Math.max(washCurrentTime, washNextTime);
                }
                // 不洗当前杯子需要时间
                int unWashCurrentTime = arr[i] + b;
                // 不洗当前杯子 后续杯子变干净的时间
                int unWashNextTime = dp[i + 1][j];
                // 当前杯子不洗的时间 和后续时间取大  为杯子变干净需要的时间
                int unWashMaxTime = Math.max(unWashCurrentTime, unWashNextTime);
                // 从两种情况中选出使用时间小的
                dp[i][j] = Math.min(washMaxTime, unWashMaxTime);
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 10};
        int a = 3;
        int b = 10;
        System.out.println(minWashTime(arr, a, b));
        System.out.println(minWashTimeDp(arr, a, b));
    }
}
