package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 45. Jump Game II
 * Medium
 * 10.2K
 * 354
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1].
 * The test cases are generated such that you can reach nums[n - 1].
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * https://leetcode.com/problems/jump-game-ii/
 * @date 2022/11/27 16:46
 */
public class JumpGameII {
    
    public int jump(int[] nums) {
        int ans = process(nums, 0, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int process(int[] nums, int i, int step) {
        if (i >= nums.length - 1) {
            return step;
        }
        if (i + nums[i] >= nums.length - 1) {
            return step + 1;
        }
        if (i < nums.length - 1 && i + nums[i] < i + 1) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int num = 1; num <= nums[i]; num++) {
            int ans = process(nums, i + num, step + 1);
            min = Math.min(min, ans);
        }
        return min;
    }
    
    public int jumpDp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= n - 1) {
                dp[i] = 1;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int num = 1; num <= nums[i]; num++) {
                min = Math.min(min, dp[i + num]);
            }
            dp[i] = min == Integer.MAX_VALUE ? min : min + 1;
        }
        return dp[0];
    }
    
    public int jumpDpOpt(int[] nums) {
        int n = nums.length;
        // step+1可以来到的最右位置
        int next = nums[0];
        // step步数可以来到的最右位置
        int cur = 0;
        // 当前最少跳几步到i
        int step = 0;
        for (int i = 1; i < n; i++) {
            // 如果前面的已经可以走到结尾 结束step+1
            if (next >= n - 1) {
                return step + 1;
            }
            // 当前step步数所能到最右 不能覆盖的时候 结算 step+1 cur来到step+1可以到达的最大地方
            if (cur < i) {
               step++;
               cur = next;
            }
            next = Math.max(next, nums[i] + i);
        }
        return step;
    }
    
    public static void main(String[] args) {
        JumpGameII jumpGameII = new JumpGameII();
        int[] nums = {2,3,0,1,4};
        System.out.println(jumpGameII.jump(nums));
        System.out.println(jumpGameII.jumpDp(nums));
    }
}
