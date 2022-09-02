package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 152. Maximum Product Subarray
 * Medium
 * 13414
 * 400
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
 * and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * https://leetcode.com/problems/maximum-product-subarray/
 * @date 2022/9/2 15:08
 */
public class MaximumProductSubarray {
    
    public int maxProduct(int[] nums) {
        int preMax = nums[0];
        int preMin = nums[0];
        int ans = preMax;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmpMax = Math.max(nums[i], nums[i] * preMin);
                preMin = Math.min(nums[i], nums[i] * preMax);
                preMax= tmpMax;
            } else {
                preMax = Math.max(nums[i], nums[i] * preMax);
                preMin = Math.min(nums[i], nums[i] * preMin);
            }
            ans = Math.max(preMax, ans);
        }
        return ans;
    }
    
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        max[0] = nums[0];
        int[] min = new int[n];
        min[0] = nums[0];
        int ans = max[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                max[i] = Math.max(nums[i], nums[i] * min[i - 1]);
                min[i] = Math.min(nums[i], nums[i] * max[i - 1]);
            } else {
                max[i] = Math.max(nums[i], nums[i] * max[i - 1]);
                min[i] = Math.min(nums[i], nums[i] * min[i - 1]);
            }
            ans = Math.max(max[i], ans);
        }
        return ans;
    }
    
    public int maxProductSubSequence(int[] nums) {
        int preMax = nums[0];
        int preMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                preMax = Math.max(preMax, nums[i] * preMin);
                preMin = Math.min(preMin, nums[i] * preMax);
            } else {
                preMax = nums[i] * preMax;
            }
        }
        return preMax;
    }
    
    public static void main(String[] args) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        int[] nums = {-4,-3,-2};
        System.out.println(maximumProductSubarray.maxProduct(nums));
    }
}
