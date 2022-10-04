package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 238. Product of Array Except Self
 * Medium
 * 14787
 * 848
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 * https://leetcode.com/problems/product-of-array-except-self/
 * @date 2022/10/4 14:13
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int zeroCount = 0;
        int all = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }
            all *= nums[i];
        }
        if (zeroCount == 0) {
            for (int i = 0; i < n; i++) {
                nums[i] = all / nums[i];
            }
        }
        if (zeroCount == 1) {
            for (int i = 0; i < n; i++) {
                nums[i] = nums[i] == 0 ? all : 0;
            }
        }
        if (zeroCount > 1) {
            for (int i = 0; i < n; i++) {
                nums[i] = 0;
            }
        }
        return nums;
    }
    
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = nums[0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i];
        }
        int right = 1;
        for (int i = n - 1; i > 0; i--) {
            ans[i] = ans[i - 1] * right;
            right *= nums[i];
        }
        ans[0] = right;
        return ans;
    }
    
    public static void main(String[] args) {
        int a = -10;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(~10 + 1));
        System.out.println(Integer.toBinaryString(~-10 + 1));
        System.out.println(Integer.toBinaryString(10));
    }
}
