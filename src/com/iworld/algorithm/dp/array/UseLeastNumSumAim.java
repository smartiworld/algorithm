package com.iworld.algorithm.dp.array;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2023/11/14 16:24
 */
public class UseLeastNumSumAim {
    
    /**
     * 数组中找出可以拼接出等于目标值 最小元素个数
     * @param arr
     * @param aim
     * @return
     */
    public static int useLeastNumEqualDp(int[] arr, int aim) {
        int len = arr.length;
        // 行表示0~i范围内 可以拼出列上数字使用最少数量
        int[][] dp = new int[len][aim + 1];
        if (arr[0] < aim) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= aim; j++) {
                if (j == arr[i]) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]] > 0) {
                        if (dp[i][j] == 0) {
                            dp[i][j] = dp[i - 1][j - arr[i]] + 1;
                        } else {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - arr[i]] + 1);
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (dp[i][aim] > 0) {
                ans = Math.min(ans, dp[i][aim]);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    
    /**
     * 数组中找出可以拼接出大于等于目标值
     * @param arr
     * @param aim
     * @return
     */
    public static int useLeastNumGreatEqualSort(int[] arr, int aim) {
        Arrays.sort(arr);
        int len = arr.length;
        int num = 1;
        int sum = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (sum >= aim) {
                return num;
            }
            sum += arr[i];
            num++;
        }
        return 0;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1,1,1};
        int aim = 11;
        System.out.println(useLeastNumEqualDp(arr, aim));
        int[] arr2 = {12,28,83,4,25,26,25,2,25,25,25,12};
        int target = 213;
        System.out.println(useLeastNumGreatEqualSort(arr2, target));
    }
}
