package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 背包问题  left to right model 动态规划
 * 给定两个长度都位N的数组weights和values
 * weights[i]表示i位置物品重量values[i]表示i位置物品价值
 * 给定一个正整数bag 表示一个可以承受bag重量的袋子，装的物品不能超过袋子承受重量
 * 返回你能装下的最大价值
 * @date 2022/5/7 23:33
 */
public class DPBackpack {

    public static int obtainMaxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || bag <= 0) {
            return 0;
        }
        return process(0, weights, values, bag, 0);
    }
    
    public static int obtainMaxValue2(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || bag <= 0) {
            return 0;
        }
        return process2(0, weights, values, bag);
    }
    
    public static int obtainMaxValue3(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || bag <= 0) {
            return 0;
        }
        return process3(0, weights, values, bag);
    }
    
    public static int obtainMaxValueCache(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || bag <= 0) {
            return 0;
        }
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int i = 0; i <= weights.length; i++) {
            for (int j = 0; j <= bag; j++) {
                dp[i][j] = -1;
            }
        }
        return processCache(0, weights, values, bag, 0, dp);
    }
    
    public static int obtainMaxValue3Cache(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || bag <= 0) {
            return 0;
        }
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int i = 0; i <= weights.length; i++) {
            for (int j = 0; j <= bag; j++) {
                dp[i][j] = -1;
            }
        }
        return process3Cache(0, weights, values, bag, dp);
    }
    
    /**
     * 递归处理背包选择物品价值
     * @param index      当前来到的位置 0-index-1已做完处理
     * @param weights    重量数组 不变
     * @param values     价值数组 不变
     * @param laveBag    剩余容量
     * @param value      已经选择物品价值
     * @return
     */
    public static int process(int index, int[] weights, int[] values, int laveBag, int value) {
        // 如果背包剩余容量小于0 表示此时无解 返回价值0
        if (laveBag < 0) {
            return 0;
        }
        // 当前位置已经来到最后位置，背包剩余容量还有空间 返回价值
        if (index == weights.length) {
            return value;
        }
        // 不选择当前位置 剩余容量和选择价值都不变处理后续位置
        int unSelectValue = process(index + 1, weights, values, laveBag, value);
        // 选择当前位置 剩余容量=前剩余容量-当前物品重量  选择物品总价值=前总价值+当前物品价值 处理后续位置
        int selectValue = process(index + 1, weights, values, laveBag - weights[index], value + values[index]);
        // 选择和不选择种找出最大价值
        return Math.max(unSelectValue, selectValue);
    }
    
    /**
     * 递归处理背包选择物品价值
     * @param index      当前来到的位置 0-index-1已做完处理
     * @param weights    重量数组 不变
     * @param values     价值数组 不变
     * @param laveBag    剩余容量
     * @return
     */
    public static int process2(int index, int[] weights, int[] values, int laveBag) {
        // 如果背包剩余容量小于0 表示此时无解 返回价值0
        if (laveBag < 0) {
            return 0;
        }
        // 当前位置已经来到最后位置，背包剩余容量还有空间 返回价值
        if (index == weights.length) {
            return 0;
        }
        // 不选择当前位置 剩余容量和选择价值都不变处理后续位置
        int unSelectValue = process2(index + 1, weights, values, laveBag);
        int selectValue = 0;
        // 表示 背包可以容纳当前物品时
        if (laveBag >= weights[index]) {
            // 选择当前位置 剩余容量=前剩余容量-当前物品重量  选择物品总价值=前总价值+当前物品价值 处理后续位置
            selectValue = values[index] + process2(index + 1, weights, values, laveBag - weights[index]);
        }
        // 选择和不选择种找出最大价值
        return Math.max(unSelectValue, selectValue);
    }
    /**
     * 递归处理背包选择物品价值
     * -1表示当前无解 0表示有解
     * @param index      当前来到的位置 0-index-1已做完处理
     * @param weights    重量数组 不变
     * @param values     价值数组 不变
     * @param laveBag    剩余容量
     * @return
     */
    public static int process3(int index, int[] weights, int[] values, int laveBag) {
        // 如果背包剩余容量小于0 表示此时无解 返回-1
        if (laveBag < 0) {
            return -1;
        }
        // 当前位置已经来到最后位置，背包剩余容量还有空间 返回0 表示有解
        if (index == weights.length) {
            return 0;
        }
        // 不选择当前位置 剩余容量和选择价值都不变处理后续位置
        int unSelectValue = process3(index + 1, weights, values, laveBag);
        // 选择当前位置 剩余容量=前剩余容量-当前物品重量 处理后续位置
        int selectValue = process3(index + 1, weights, values, laveBag - weights[index]);;
        if (selectValue != -1) {
            // 选择物品总价值=前总价值+当前物品价值
            selectValue = values[index] + selectValue;
        }
        // 选择和不选择种找出最大价值
        return Math.max(unSelectValue, selectValue);
    }
    
    /**
     * 递归处理背包选择物品价值  记忆化搜索
     * @param index      当前来到的位置 0-index-1已做完处理
     * @param weights    重量数组 不变
     * @param values     价值数组 不变
     * @param laveBag    剩余容量
     * @param value      已经选择物品价值
     * @param dp         物品价值数组
     * @return
     */
    public static int processCache(int index, int[] weights, int[] values, int laveBag, int value, int[][] dp) {
        // 如果背包剩余容量小于0 表示此时无解 返回价值0
        if (laveBag < 0) {
            return 0;
        }
        if (dp[index][laveBag] != -1) {
            return dp[index][laveBag];
        }
        // 当前位置已经来到最后位置，背包剩余容量还有空间 返回价值
        if (index == weights.length) {
            return value;
        }
        // 不选择当前位置 剩余容量和选择价值都不变处理后续位置
        int unSelectValue = processCache(index + 1, weights, values, laveBag, value, dp);
        // 选择当前位置 剩余容量=前剩余容量-当前物品重量  选择物品总价值=前总价值+当前物品价值 处理后续位置
        int selectValue = processCache(index + 1, weights, values, laveBag - weights[index], value + values[index], dp);
        // 选择和不选择种找出最大价值
        dp[index][laveBag] = Math.max(unSelectValue, selectValue);
        return dp[index][laveBag];
    }
    
    /**
     * 递归处理背包选择物品价值 记忆化搜索
     * -1表示当前无解 0表示有解
     * @param index      当前来到的位置 0-index-1已做完处理
     * @param weights    重量数组 不变
     * @param values     价值数组 不变
     * @param laveBag    剩余容量
     * @return
     */
    public static int process3Cache(int index, int[] weights, int[] values, int laveBag, int[][] dp) {
        // 如果背包剩余容量小于0 表示此时无解 返回-1
        if (laveBag < 0) {
            return -1;
        }
        if (dp[index][laveBag] != -1) {
            return dp[index][laveBag];
        }
        // 当前位置已经来到最后位置，背包剩余容量还有空间 返回0 表示有解
        if (index == weights.length) {
            dp[index][laveBag] = 0;
            return dp[index][laveBag];
        }
        // 不选择当前位置 剩余容量和选择价值都不变处理后续位置
        int unSelectValue = process3Cache(index + 1, weights, values, laveBag, dp);
        // 选择当前位置 剩余容量=前剩余容量-当前物品重量 处理后续位置
        int selectValue = process3Cache(index + 1, weights, values, laveBag - weights[index], dp);;
        if (selectValue != -1) {
            // 选择物品总价值=前总价值+当前物品价值
            selectValue = values[index] + selectValue;
        }
        // 选择和不选择种找出最大价值
        dp[index][laveBag] = Math.max(unSelectValue, selectValue);
        return dp[index][laveBag];
    }
    
    /**
     * 动态规划
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int obtainMaxValueDp(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || values.length == 0 || bag <= 0) {
            return 0;
        }
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int i = weights.length - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[0][bag];
    }
    public static void main(String[] args) {
        int[] weights = {2, 1, 4, 3};
        int[] values = {8, 5, 12, 15};
        int bag = 6;
        int maxValue = obtainMaxValue(weights, values, bag);
        System.out.println(maxValue);
        System.out.println(obtainMaxValue2(weights, values, bag));
        System.out.println(obtainMaxValue3(weights, values, bag));
        System.out.println(obtainMaxValueCache(weights, values, bag));
        System.out.println(obtainMaxValue3Cache(weights, values, bag));
        System.out.println(obtainMaxValueDp(weights, values, bag));
    }
}
