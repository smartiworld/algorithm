package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 组合钱数  动态规划
 * 一个数组表示一组货币面值，每个面值的数量可以使用无限多个 给定一钱数 使用数组面值组合到给定钱数有多少种方案
 * @date 2022/5/14 23:54
 */
public class DPCombinationMoney {
    
    public static int combinationMoneyCount(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        return process(moneys, totalMoney, 0);
    }
    
    /**
     * 两种case 使用当前货币和不使用当前面值货币
     * @param moneys      面值数组
     * @param lastMoney  剩余金额
     * @param index      来到面值位置
     * @return
     */
    private static int process(int[] moneys, int lastMoney, int index) {
        // 当前已经组合完毕 并且还有货币
        if (index <= moneys.length && lastMoney == 0) {
            return 1;
        }
        if (index >= moneys.length || lastMoney < 0) {
            return 0;
        }
        // 使用当前面值 接着使用当前面值 剩余金额=lastMoney - moneys[index]
        int use = process(moneys, lastMoney - moneys[index], index);
        // 不使用当前面值 来到下一位置
        int noUse = process(moneys, lastMoney, index + 1);
        return use + noUse;
    }
    
    public static int combinationMoneyCount2(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        return process2(moneys, totalMoney, 0);
    }
    
    /**
     *
     * @param moneys      面值数组
     * @param lastMoney  剩余金额
     * @param index      来到面值位置
     * @return
     */
    private static int process2(int[] moneys, int lastMoney, int index) {
        // 下面for循环条件控制了不会提前到达金额
        if (index == moneys.length) {
            return lastMoney == 0 ? 1 : 0;
        }
        int result = 0;
        //当前index位置面值使用的数量 使用0然后向后推 使用1张 使用2张 依次处理
        for (int i = 0; i * moneys[index] <= lastMoney; i++) {
            result += process2(moneys, lastMoney - (i * moneys[index]), index + 1);
        }
        return result;
    }
    
    public static int combinationMoneyCountCache(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        Integer[][] dp = new Integer[moneys.length + 1][totalMoney + 1];
        return processCache(moneys, totalMoney, 0, dp);
    }
    
    /**
     * 记忆化搜索
     * 两种case 使用当前货币和不使用当前面值货币
     * @param moneys      面值数组
     * @param lastMoney  剩余金额
     * @param index      来到面值位置
     * @return
     */
    private static int processCache(int[] moneys, int lastMoney, int index, Integer[][] dp) {
        if (dp[index][lastMoney] != null) {
            return dp[index][lastMoney];
        }
        // 当前已经组合完毕 并且还有货币
        if (index <= moneys.length && lastMoney == 0) {
            dp[index][lastMoney] = 1;
            return dp[index][lastMoney];
        }
        if (index >= moneys.length) {
            dp[index][lastMoney] = 0;
            return dp[index][lastMoney];
        }
        int use = 0;
        if (lastMoney - moneys[index] >= 0) {
            // 使用当前面值 接着使用当前面值 剩余金额=lastMoney - moneys[index]
            use = processCache(moneys, lastMoney - moneys[index], index, dp);
        }
        // 不使用当前面值 来到下一位置
        int noUse = processCache(moneys, lastMoney, index + 1, dp);
        dp[index][lastMoney] = use + noUse;
        return dp[index][lastMoney];
    }
    
    public static int combinationMoneyCount2Cache(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        Integer[][] dp = new Integer[moneys.length + 1][totalMoney + 1];
        return process2Cache(moneys, totalMoney, 0, dp);
    }
    
    /**
     *
     * @param moneys      面值数组
     * @param lastMoney  剩余金额
     * @param index      来到面值位置
     * @return
     */
    private static int process2Cache(int[] moneys, int lastMoney, int index, Integer[][] dp) {
        if (dp[index][lastMoney] != null) {
            return dp[index][lastMoney];
        }
        // 下面for循环条件控制了不会提前到达金额
        if (index == moneys.length) {
            dp[index][lastMoney] = lastMoney == 0 ? 1 : 0;
            return dp[index][lastMoney];
        }
        int result = 0;
        //当前index位置面值使用的数量 使用0然后向后推 使用1张 使用2张 依次处理
        for (int i = 0; i * moneys[index] <= lastMoney; i++) {
            result += process2Cache(moneys, lastMoney - (i * moneys[index]), index + 1, dp);
        }
        dp[index][lastMoney] = result;
        return dp[index][lastMoney];
    }
    
    public static int combinationMoneyCountDp(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        int[][] dp = new int[moneys.length + 1][totalMoney + 1];
        // 因[i][j]一定依赖[i + 1][j] 所以不用填每个总之为0和i的位置 只需填最后一个位置即可
        // 但是需要将列 j从0开始遍历
        for (int i = 0; i <= moneys.length; i++) {
            dp[i][0] = 1;
        }
        // [i][j]依赖[i + 1][j]  如果当前j总面值大于等于当前i位置面值
        // [i][j]依赖[i + 1][j]+[i][j - 当前i位置面值];
        for (int i = moneys.length - 1; i >= 0; i--) {
            for (int j = 1; j <= totalMoney; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j >= moneys[i]) {
                    dp[i][j] = dp[i][j] + dp[i][j - moneys[i]];
                }
            }
        }
        return dp[0][totalMoney];
    }
    
    public static int combinationMoneyCountDp1(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        int[][] dp = new int[moneys.length + 1][totalMoney + 1];
        // 因[i][j]一定依赖[i + 1][j] 所以不用填每个总之为0和i的位置 只需填最后一个位置即可
        // 但是需要将列 j从0开始遍历
        //for (int i = 0; i <= moneys.length; i++) {
        //    dp[i][0] = 1;
        //}
        dp[moneys.length][0] = 1;
        // [i][j]依赖[i + 1][j]  如果当前j总面值大于等于当前i位置面值
        // [i][j]依赖[i + 1][j]+[i][j - 当前i位置面值];
        for (int i = moneys.length - 1; i >= 0; i--) {
            for (int j = 0; j <= totalMoney; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j >= moneys[i]) {
                    dp[i][j] = dp[i][j] + dp[i][j - moneys[i]];
                }
            }
        }
        return dp[0][totalMoney];
    }
    
    /**
     * 非精简dp  后有枚举行为
     * @param moneys
     * @param totalMoney
     * @return
     */
    public static int combinationMoneyCount2Dp(int[] moneys, int totalMoney) {
        if (moneys == null || moneys.length <=0 || totalMoney <= 0) {
            return 0;
        }
        int[][] dp = new int[moneys.length + 1][totalMoney + 1];
        dp[moneys.length][0] = 1;
        for (int i = moneys.length - 1; i >= 0; i--) {
            for (int j = 0; j <= totalMoney; j++) {
                // 非精简
                for (int k = 0; k * moneys[i] <= j; k++) {
                    dp[i][j] += dp[i + 1][j - (k * moneys[i])];
                }
            }
        }
        return dp[0][totalMoney];
    }
    
    public static void main(String[] args) {
        int[] money = {2, 3, 5, 1};
        int totalMoney = 30;
        System.out.println(combinationMoneyCount(money, totalMoney));
        System.out.println(combinationMoneyCount2(money, totalMoney));
        System.out.println(combinationMoneyCountCache(money, totalMoney));
        System.out.println(combinationMoneyCount2Cache(money, totalMoney));
        System.out.println(combinationMoneyCountDp(money, totalMoney));
        System.out.println(combinationMoneyCount2Dp(money, totalMoney));
        System.out.println(combinationMoneyCountDp1(money, totalMoney));
    }
}
