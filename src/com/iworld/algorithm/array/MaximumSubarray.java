package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 53. Maximum Subarray   Medium
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * https://leetcode.com/problems/maximum-subarray/
 * @date 2022/8/12 16:04
 */
public class MaximumSubarray {
    /**
     * 子数组最大累加和 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // 子数组必须以i结尾 最大累加和
        // 只包含i位置nums[i] 不只包含需要计算前面所有为nums[i] + dp[i - 1]
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 计算出当前位置得最大值 包含0~1位置nums[i] + dp[i - 1]， 不包含nums[i]
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            // 选出数组中最大累加和
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
    public int maxSubArrayOpt(int[] nums) {
        int max = nums[0];
        // 省空间 使用一个变量记录i-1 最大值
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(nums[i], nums[i] + pre);
            max = Math.max(max, pre);
        }
        return max;
    }
    
    public int maxSubArrayOpt2(int[] nums) {
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
    
    /**
     * 数组区间求最大和 不需要一定以i位置结尾
     * 必须不能连续
     * 子数组计算以i结尾计算
     * 子序列是范围更优 不严格要求以i结尾
     * @param nums
     * @return
     */
    public static int maxSumRange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 0-i区间所得最大 i到i+1位置只能大于等于 不会小于 所以结果就在最后位置
        // dp[i]记录i结尾所得最组合
        // 1.只包含i位置数nums[i]
        // 2.不包含i位置数那就是0~i-1范围 dp[i - 1]
        // 3.不只包含i位置数nums[i]+dp[i-2] 不能相邻
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 只包含当前位置和不需要当前位置
            int tmp = Math.max(nums[i], dp[i - 1]);
            // 不只包含i位置dp[i - 2] + nums[i]
            dp[i] = Math.max(tmp, dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
    
    public static void main(String[] args) {
        int[] nums = {2,1,-3,4,-1,2,1,-5,4};
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        System.out.println(maximumSubarray.maxSubArray(nums));
        System.out.println(maximumSubarray.maxSubArrayOpt(nums));
        System.out.println(maximumSubarray.maxSubArrayOpt2(nums));
    }
}
