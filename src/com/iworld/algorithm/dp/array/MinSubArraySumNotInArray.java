package com.iworld.algorithm.dp.array;

import com.iworld.algorithm.util.ArrayUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.7.2 累加和不能到达最小数字
 * 给定一个正数数组 arr，其中所有的值都为整数，以下是最小不可组成和的概念:
 * 把 arr 每个子集内的所有元素加起来会出现很多值，其中最小的记为 min，最大的记为max 在区间[min,max]上，如果有数不可以被arr某一个子集相加得到，
 * 那么其中最小的那个数是arr 的最小不可组成和 在区间[min,max]上，如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最 小不可组成和
 * 请写函数返回正数数组 arr 的最小不可组成和。
 * 【举例】
 * arr=[3,2,5]。子集{2}相加产生 2 为 min，子集{3,2,5}相加产生 10 为 max。在区间[2,10] 上，4、 6 和 9 不能被任何子集相加得到，
 * 其中 4 是 arr 的最小不可组成和。 arr=[1,2,4]。子集{1}相加产生 1 为 min，子集{1,2,4}相加产生 7 为 max。在区间[1,7]上， 任何 数都可以被子集相加得到，所以 8 是 arr 的最小不可组成和。
 * 【进阶】
 * 如果已知正数数组 arr 中肯定有 1 这个数，是否能更快地得到最小不可组成和?
 * @date 2023/11/4 17:11
 */
public class MinSubArraySumNotInArray {
    
    public static int minSum(int[] arr) {
        Set<Integer> allSum = new HashSet<>();
        getAllSubSum(arr, allSum, 0, 0);
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max += arr[i];
        }
        for (int i = min; i < max; i++) {
            if (!allSum.contains(i)) {
                return i;
            }
        }
        return max + 1;
    }
    
    private static void getAllSubSum(int[] arr, Set<Integer> allSum, int index, int sum) {
        if (index == arr.length) {
            allSum.add(sum);
            return ;
        }
        getAllSubSum(arr, allSum, index + 1, sum);
        getAllSubSum(arr, allSum, index + 1, sum + arr[index]);
    }
    
    public static int minSumDp(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, arr[i]);
            max += arr[i];
        }
        int col = max - min + 1;
        // 记录0~i范围内 能拼出j和的状态 当前位置已经拼接出当前范围
        // 列长度为col 实际范围为min~max 从0开始计算0~max-min
        boolean[][] dp = new boolean[len][col];
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < len; i++) {
            for (int j = min; j <= max; j++) {
                dp[i][j - min] = dp[i - 1][j - min] || (arr[i] == j) || (j - arr[i] - min >= 0 && dp[i - 1][j - arr[i] - min]);
            }
        }
        for (int j = min + 1; j <= max; j++) {
            if (!dp[len - 1][j - min]) {
                return j;
            }
        }
        return max + 1;
    }
    
    public static int minSumDpUseAllCache(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, arr[i]);
            max += arr[i];
        }
        // 记录0~i范围内 能拼出j和的状态 当前位置已经拼接出当前范围
        // 列长度为col 实际范围为min~max 从0开始计算0~max-min
        boolean[][] dp = new boolean[len][max + 1];
        dp[0][arr[0]] = true;
        for (int i = 1; i < len; i++) {
            for (int j = min; j <= max; j++) {
                dp[i][j] = dp[i - 1][j] || (arr[i] == j) || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        for (int j = min + 1; j <= max; j++) {
            if (!dp[len - 1][j]) {
                return j;
            }
        }
        return max + 1;
    }
    
    public static void main(String[] args) {
        int len = 27;
        int max = 30;
        for (int i = 0; i < 100; i++) {
            int[] arr = ArrayUtil.generateIntArray(len, max);
            ArrayUtil.printArray(arr);
            int i1 = minSum(arr);
            int i2 = minSumDp(arr);
            if (i1 != i2) {
                System.out.println(i1 + "===" + i2);
            }
        }
        int[] arr = {29,18,14,20,19,21,4,24,20,24,2,17,26,10,23,4,18,6,4,26,11,27,2,17,3,10,18};
        int i1 = minSum(arr);
        int i2 = minSumDp(arr);
        int i3 = minSumDpUseAllCache(arr);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
    }
}
