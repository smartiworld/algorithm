package com.iworld.algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 464.我能赢吗
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和
 * 达到或超过  100 的玩家，即为胜者。
 *
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 *
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 *
 * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），
 * 若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。
 *
 * 示例 1：
 *
 * 输入：maxChoosableInteger = 10, desiredTotal = 11
 * 输出：false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 * 示例 2:
 *
 * 输入：maxChoosableInteger = 10, desiredTotal = 0
 * 输出：true
 * 示例 3:
 *
 * 输入：maxChoosableInteger = 10, desiredTotal = 1
 * 输出：true
 * 提示:
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 *
 * 链接：https://leetcode.cn/problems/can-i-win
 * @date 2022/5/22 21:36
 */
public class ICanWin {
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 尝试暴力的记忆化深搜(看题解)
        if ((maxChoosableInteger * (1 + maxChoosableInteger) >> 1) < desiredTotal) {
            return false;
        }
        return processCache(desiredTotal, 0, maxChoosableInteger, new HashMap<>());
    }
    
    /**
     * 递归处理取的总和
     * 使用位运算标记当前已经使用了哪些位置的数 使用的数记位数 1左移动位数 表示当前位置已经被使用
     * 使用的不会再使用
     * @param rest                  剩余的总和
     * @param usedNum               已经使用的位置
     * @param maxChoosableInteger   最大数
     * @return
     */
    private boolean process(int rest, int usedNum, int maxChoosableInteger) {
        boolean isWin = false;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            // 判断当前位置的数 是否已经使用
            if ((usedNum & (1 << (i - 1))) == 0) {
                // 剩余总和-当前未使用的数字 如果小于等于0表示当前已经满足
                if (rest - i <= 0) {
                    isWin = true;
                    break;
                }
                // 当前位置标记为已经使用  后学递归时 不再使用  剩余总和-当前位置数
                int tmpUsed = usedNum | (1 << (i - 1));
                boolean otherStatus = process(rest - i, tmpUsed, maxChoosableInteger);
                // 如果后面没有满足
                if (!otherStatus) {
                    isWin = true;
                    break;
                }
            }
        }
        return isWin;
    }
    
    /**
     * 递归处理取的总和
     * 使用位运算标记当前已经使用了哪些位置的数 使用的数记位数 1左移动位数 表示当前位置已经被使用
     * 使用的不会再使用
     * @param rest                  剩余的总和
     * @param usedNum               已经使用的位置
     * @param maxChoosableInteger   最大数
     * @return
     */
    private boolean processCache(int rest, int usedNum, int maxChoosableInteger, Map<Integer, Boolean> dp) {
        if (!dp.containsKey(usedNum)) {
            boolean isWin = false;
            for (int i = 1; i <= maxChoosableInteger; i++) {
                // 判断当前位置的数 是否已经使用
                if ((usedNum & (1 << (i - 1))) == 0) {
                    // 剩余总和-当前未使用的数字 如果小于等于0表示当前已经满足
                    if (rest - i <= 0) {
                        isWin = true;
                        break;
                    }
                    // 当前位置标记为已经使用  后学递归时 不再使用  剩余总和-当前位置数
                    int tmpUsed = usedNum | (1 << (i - 1));
                    boolean otherStatus = processCache(rest - i, tmpUsed, maxChoosableInteger, dp);
                    // 如果后面没有满足
                    if (!otherStatus) {
                        isWin = true;
                        break;
                    }
                }
            }
            dp.put(usedNum, isWin);
        }
        return dp.get(usedNum);
    }
    
    
    public boolean canIWinOpt(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if((maxChoosableInteger + 1) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        Boolean[] dp = new Boolean[1 << maxChoosableInteger];
        return processOpt(desiredTotal, (1 << maxChoosableInteger) - 1, dp);
    }
    boolean processOpt(int desiredTotal, int state, Boolean[] dp){
        if(desiredTotal <= 0) {
            return false;
        }
        if(dp[state] != null){
            return dp[state];
        }
        // lowBit 最低位为1的位置 i -= lowBit, lowBit = i & (-i) 遍历条件 将当前位置从i中剥离 
        for(int i = state, lowBit = i & (-i); i != 0; i -= lowBit, lowBit = i & (-i)){
            // Integer.bitCount(lowBit - 1) 将当前位转换为实际数字
            if(!processOpt(desiredTotal - Integer.bitCount(lowBit - 1) - 1, state ^ lowBit, dp)){
                dp[state] = true;
                return true;
            }
            
        }
        dp[state] = false;
        return false;
    }
    public static void main(String[] args) {
        int maxChoosableInteger = 5;
        int desiredTotal = 10;
        ICanWin iCanWin = new ICanWin();
        //System.out.println(iCanWin.canIWin(maxChoosableInteger, desiredTotal));
        System.out.println(iCanWin.process(desiredTotal, 0, maxChoosableInteger));
        System.out.println(iCanWin.canIWin(maxChoosableInteger, desiredTotal));
        System.out.println(iCanWin.canIWinOpt(maxChoosableInteger, desiredTotal));
    }
}
