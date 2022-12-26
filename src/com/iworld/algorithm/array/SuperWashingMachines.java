package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * 517. Super Washing Machines
 * Hard
 * 627
 * 198
 * Companies
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 *
 * For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
 *
 * Given an integer array machines representing the number of dresses in each washing machine from left to right on the line, return the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: machines = [1,0,5]
 * Output: 3
 * Explanation:
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 * 3rd move:    2     1 <-- 3    =>    2     2     2
 * Example 2:
 *
 * Input: machines = [0,3,0]
 * Output: 2
 * Explanation:
 * 1st move:    0 <-- 3     0    =>    1     2     0
 * 2nd move:    1     2 --> 0    =>    1     1     1
 * Example 3:
 *
 * Input: machines = [0,2,0]
 * Output: -1
 * Explanation:
 * It's impossible to make all three washing machines have the same number of dresses.
 *
 *
 * Constraints:
 *
 * n == machines.length
 * 1 <= n <= 10^4
 * 0 <= machines[i] <= 10^5
 * https://leetcode.com/problems/super-washing-machines/
 * 打包机问题
 * n个打包机排列一排 每个打包机数量不同，需要工人将每个打包机上的物品进行移动，每个打包机上数量相等才可以打包
 * 物品太大 每次只能搬一个物品进行移动，计算在移动轮数的最小情况下 使每个机器上物品相等 如果不能使每个机器物品相等则返回-1
 * 例 [1,0,5] 表示3个机器 数组位置数字代表每个机器上物品数量 第一轮 1 1 4 第二轮 2 1 3 第三轮 2 2 2 返回3
 * https://github.com/algorithmzuo/trainingcamp003/blob/master/src/class02/Code02_PackingMachine.java
 * @date 2022/12/26 15:25
 */
public class SuperWashingMachines {
    /**
     * 1.计算总物品数量
     * 2.计算每个机器需要处理的数量
     * 3.来到当前i位置时
     * 右边需要右边数量*每个机器需要处理的数量
     * 左边需要左边数量*每个机器需要处理的数量
     * 3.1当左边实际数量小于需要数量并且右边实际数量小于需要数量 此时i两边是需要物品的 此时需要轮数就是左边需要加上右边需要
     * 3.2左边多或者右边多或者两边都多的时候 就需要将最多的数量分发完就可以完成 此时为max(|左边差|,|右边差|）
     * 4.更新左边实际数量和右边实际数量
     * @param machines
     * @return
     */
    public int findMinMoves(int[] machines) {
        if (machines == null || machines.length == 0) {
            return 0;
        }
        int total = 0;
        int len = machines.length;
        for (int i = 0; i < len; i++) {
            total += machines[i];
        }
        if (total % len != 0) {
            return -1;
        }
        int everyCount = total / len;
        int leftTotal = 0;
        int rightTotal = total;
        int ans = -1;
        for (int i = 0; i < len; i++) {
            rightTotal -= machines[i];
            int leftNeed = everyCount * i;
            int rightNeed = everyCount * (len - i - 1);
            int leftDiff = leftTotal - leftNeed;
            int rightDiff = rightTotal - rightNeed;
            if (leftDiff < 0 && rightDiff < 0) {
                ans = Math.max(ans, Math.abs(leftDiff) + Math.abs(rightDiff));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftDiff), Math.abs(rightDiff)));
            }
            leftTotal += machines[i];
        }
        return ans;
    }
}
