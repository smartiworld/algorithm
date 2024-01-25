package com.iworld.algorithm.dp.lefttoright;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.6.3 跳最少步数
 * 给出一组正整数arr，你从第0个数向最后一个数，
 * 每个数的值表示你从这个位置可以向右跳跃的最大长度
 * 计算如何以最少的跳跃次数跳到最后一个数。
 * 正整数
 * @see com.iworld.algorithm.dp.JumpGameII
 * @date 2024/1/23 16:51
 */
public class MinStepToRight {

    public static int minStep(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        int ans = process(nums, 0, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private static int process(int[] nums, int index, int step) {
        if (index == nums.length - 1) {
            return step;
        }
        if (index + nums[index] >= nums.length - 1) {
            return step + 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            min = Math.min(min, process(nums, index + i, step++));
        }
        return min;
    }
    
    public static int minStepDp(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        int[] dp = new int[len];
        // 数组正整数 倒数第二个位置数字肯定能到最后 至少使用一步
        dp[len - 2] = 1;
        for (int i = len - 3; i >= 0; i--) {
            if (i + nums[i] >= len - 1) {
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= nums[i]; j++) {
                    min = Math.min(min, dp[i + j]);
                }
                dp[i] = min == Integer.MAX_VALUE ? min : min + 1;
            }
        }
        return dp[0];
    }
    
    public static int minStepDpOpt(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        // 下位置可以来到的最右位置
        int next = nums[0];
        // 前位置可以来到的最右位置
        int cur = 0;
        int step = 0;
        for (int i = 1; i < len; i++) {
            if (next >= len - 1) {
                return step + 1;
            }
            // 前面能到达最右侧不能达到当前位置 需要走步数来追上
            if (cur < i) {
                step++;
                cur = next;
            }
            // cur 来到当前i位置 i位置可以扩张的范围来到i + nums[i]
            next = Math.max(next, i + nums[i]);
        }
        return step;
    }
    
}
