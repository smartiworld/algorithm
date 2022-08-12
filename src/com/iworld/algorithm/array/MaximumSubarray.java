package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 53. Maximum Subarray   Medium
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
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
 * 1 <= nums.length <= 10 5
 * -10 4 <= nums[i] <= 10 4
 * https://leetcode.com/problems/maximum-subarray/
 * @date 2022/8/12 16:04
 */
public class MaximumSubarray {
    
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
    public int maxSubArrayOpt(int[] nums) {
        int max = nums[0];
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
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 只包含当前位置和不需要当前位置
            int tmp = Math.max(nums[i], dp[i - 1]);
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
