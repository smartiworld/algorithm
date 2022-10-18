package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 334. Increasing Triplet Subsequence
 * Medium
 * 5913
 * 266
 * Given an integer array nums, return true
 * if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
 * If no such indices exists, return false.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * @date 2022/10/17 16:21
 */
public class IncreasingTripletSubsequence {
    
    public boolean increasingTriplet(int[] nums) {
        int m = nums.length;
        // 有序数组
        int[] end = new int[m];
        end[0] = nums[0];
        // end 数组放元素右边界
        int eIndex = 0;
        int max = 1;
        for (int i = 1; i < m; i++) {
            int l = 0;
            int r = eIndex;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                // 相等的时候不扩张end
                if (end[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            // 当放入nums[i] 大于end最后位置元素时 l是大于eIndex的
            eIndex = Math.max(l, eIndex);
            end[l] = nums[i];
            max = Math.max(max, l + 1);
            if (max >= 3) {
                return true;
            }
        }
        return false;
    }
    
    public int increasingTriplet3(int[] nums) {
        int m = nums.length;
        // 有序数组
        int[] end = new int[m];
        end[0] = nums[0];
        // end 数组放元素右边界
        int eIndex = 0;
        int max = 1;
        for (int i = 1; i < m; i++) {
            int l = 0;
            int r = eIndex;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (end[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            // 当放入nums[i] 大于end最后位置元素时 l是大于eIndex的
            eIndex = Math.max(l, eIndex);
            end[l] = nums[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }
    
    
    /**
     * 暴力
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        int m = nums.length;
        int[] dp = new int[m];
        dp[0] = 1;
        for (int i = 1; i < m; i++) {
            int max = 1;
            for (int j = i; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            if (dp[i] >= 3) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        IncreasingTripletSubsequence increasingTripletSubsequence = new IncreasingTripletSubsequence();
        int[] nums = {20,20,20,20,20,20};
        System.out.println(increasingTripletSubsequence.increasingTriplet(nums));
        System.out.println(increasingTripletSubsequence.increasingTriplet2(nums));
        System.out.println(increasingTripletSubsequence.increasingTriplet3(nums));
    }
}
