package com.iworld.algorithm.dp.lefttoright;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.3.2 背包问题
 * 背包容量为w
 * 一共有n袋零食, 第i袋零食体积为v[i]
 * 总体积不超过背包容量的情况下，
 * 一共有多少种零食放法？(总体积为0也算一种放法)。 不放零食也算一种方法
 * @date 2023/12/12 18:08
 */
public class PackageWays {
    
    public static int ways(int[] products, int k) {
        return process(products, 0, k);
    }
    
    private static int process(int[] products, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index >= products.length) {
            return 1;
        }
        int unUse = process(products, index + 1, rest);
        int use = process(products, index + 1, rest - products[index]);
        return unUse + use;
    }
    
    public static int waysDp(int[] products, int k) {
        int len = products.length;
        int[][] dp = new int[len + 1][k + 1];
        for (int j = 0; j <= k; j++) {
            dp[len][j] = 1;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - products[i] >= 0) {
                    dp[i][j] += dp[i + 1][j - products[i]];
                }
            }
        }
        return dp[0][k];
    }
    
    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 9 };
        int w = 1;
        System.out.println(ways(arr, w));
        System.out.println(waysDp(arr, w));
    }
    
}
