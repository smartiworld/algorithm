package com.iworld.algorithm.dp.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.8.4 通过花费最少的钱
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 * @date 2023/10/20 21:48
 */
public class MonsterMoneyProblem {
    
    /**
     * 时间复杂度n*(sum(ability))
     * 适合金钱总和小的场景
     * @param battles
     * @param costMoney
     * @return
     */
    public static int costMinMoney(int[] battles, int[] costMoney) {
        return process(battles, costMoney, 0, 0);
    }
    
    /**
     * 当前来到index位置 当前能力覆盖怪兽战力 则可以直接跳过也可以花钱增加怪兽战力，如果当前能力小于怪兽战力，必须需要花钱，并且增加当前战力
     * @param battles     对应能力
     * @param costMoney   对应能力花费的金钱
     * @param ability    当前拥有的能力
     * @param index      当前来到的位置
     * @return
     */
    private static int process(int[] battles, int[] costMoney, int ability, int index) {
        // 来到最后一个怪兽 如果能力覆盖怪兽战力 不需要花钱 否则花费最后怪兽消耗金钱
        if (index == battles.length - 1) {
            return ability < battles[index] ? costMoney[index] : 0;
        }
        if (ability < battles[index]) {
            return costMoney[index] + process(battles, costMoney, ability + battles[index], index + 1);
        } else {
            return Math.min(costMoney[index] + process(battles, costMoney, ability + battles[index], index + 1), process(battles, costMoney, ability, index + 1));
        }
    }
    
    /**
     * 时间复杂度n*(sum(ability))
     * 能力和小的场景
     * @param battles
     * @param costMoney
     * @return
     */
    public static int costMinMoneyDp(int[] battles, int[] costMoney) {
        int row = battles.length;
        int totalBattle = 0;
        for (int i = 0; i < row; i++) {
            totalBattle += battles[i];
        }
        // 行来到i位置怪兽 列拥有的能力 值为拥有当前能力通过当前怪兽需要花费最小的钱
        int[][] dp = new int[row][totalBattle + 1];
        // 最后一列拥有最大能力 不需要花钱 默认都是0
        for (int j = 0; j < row; j++) {
            dp[row - 1][j] = j < battles[row - 1] ? costMoney[row - 1] : 0;
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = totalBattle - 1; j >= 0; j--) {
                if (j + battles[i] > totalBattle) {
                    continue;
                }
                if (j < battles[i]) {
                    dp[i][j] = costMoney[i] + dp[i + 1][j + battles[i]];
                } else {
                    dp[i][j] = Math.min(costMoney[i] + dp[i + 1][j + battles[i]], dp[i + 1][j]);
                }
            }
        }
        return process(battles, costMoney, 0, 0);
    }
    
    /**
     * 时间复杂度n*(sum(costMoney))
     * 花费金钱少的场景
     * @param battles
     * @param costMoney
     * @return
     */
    public static int costMinMoneyDp2(int[] battles, int[] costMoney) {
        int row = battles.length;
        int totalCostMoney = 0;
        for (int i = 0; i < row; i++) {
            totalCostMoney += costMoney[i];
        }
        // 行0~i位置怪兽 列严格花费的金钱 能达到最大的能力
        int[][] dp = new int[row][totalCostMoney + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j <= totalCostMoney; j++) {
                dp[i][j] = -1;
            }
        }
        // 0~0范围只能花费costMoney[0]  到达能力只能到达battles[0] 其他列均不可到达
        dp[0][costMoney[0]] = battles[0];
        int result = totalCostMoney;
        for (int i = 1; i < row; i++) {
            for (int j = 0; j <= totalCostMoney; j++) {
                // 不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                // 使用j金钱 在0~i-1怪兽获得能力已经大于当前i位置怪兽能力，所以在i号怪兽不需要花费金钱 也就不会获得其能力
                if (dp[i - 1][j] >= battles[i]) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                // 获取i号怪兽能力 需要花费i号的钱 在0~i刚好花费j i位置需要花费当前怪兽钱，所以0~i-1位置花费j-money[i]
                if (j - costMoney[i] >= 0 && dp[i - 1][j - costMoney[i]] > -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - costMoney[i]] + battles[i]);
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= battles[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        for (int j = 0; j <= totalCostMoney; j++) {
            if (dp[row - 1][j] > -1) {
                result = j;
                break;
            }
        }
        return result;
    }
    
    public static long func3(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int j = 0; j <= sum; j++) {
            if (dp[d.length - 1][j] != -1) {
                ans = j;
                break;
            }
        }
        return ans;
    }
    
    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }
    
    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = costMinMoney(d, p);
            long ans2 = costMinMoneyDp(d, p);
            long ans3 = costMinMoneyDp2(d, p);
            long ans4 = func3(d, p);
            if (ans4 != ans2) {
                System.out.println("ans2 oops!");
            }
            if (ans4 != ans3) {
                System.out.println("ans3 oops!");
            }
        }
        
    }
}
