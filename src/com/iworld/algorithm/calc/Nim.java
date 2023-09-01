package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description  Nim博弈问题
 *
 * 给定一个非负数组，每一个值代表该位置上有几个铜板。a和b玩游戏，a先手，b后手，
 * 轮到某个人的时候，只能在一个位置上拿任意数量的铜板，但是不能不拿。谁最先把铜 板拿完谁赢。假设a和b都极度聪明，请返回获胜者的名字
 * @date 2023/9/1 18:54
 */
public class Nim {
    
    /**
     * 取硬币时 谁遇到0个硬币即为输 则对方获胜
     * 异或和为0时即为输 当前取硬币时异或和不为0 取数完后一定需要将剩余数异或和为0
     * @param coins
     */
    public void whoWin(int[] coins) {
        int eor = 0;
        for (int i = 0; i < coins.length; i++) {
            eor ^= coins[i];
        }
        if (eor == 0) {
            System.out.println("先手");
        } else {
            System.out.println("后手");
        }
    }
}
