package com.iworld.algorithm.recursion;

/**
 * @author gq.cai
 * @version 1.0
 * @description 组合钱数
 * 一个数组表示一组货币面值，每个面值的数量可以使用无限多个 给定一钱数 使用数组面值组合到给定钱数有多少种方案
 * @date 2022/5/14 23:54
 */
public class CombinationMoney {
    
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
    
    public static void main(String[] args) {
        int[] money = {2, 3, 5, 1};
        int totalMoney = 10;
        System.out.println(combinationMoneyCount(money, totalMoney));
        System.out.println(combinationMoneyCount2(money, totalMoney));
    }
}
