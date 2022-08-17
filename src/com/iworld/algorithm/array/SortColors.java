package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 75. Sort Colors  Medium
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 * https://leetcode.com/problems/sort-colors/
 * @date 2022/8/17 15:42
 */
public class SortColors {
    
    public void sortColors(int[] nums) {
        int baseNum = 1;
        int l = 0;
        int b = nums.length - 1;
        int i = 0;
        while (i <= b) {
            if (nums[i] < baseNum) {
                swap(nums, i++, l++);
            } else if (nums[i] > baseNum) {
                swap(nums, i, b--);
            } else {
                i++;
            }
        }
    }
    
    private void swap(int[] nums, int l, int r) {
        if (l == r) {
            return ;
        }
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
