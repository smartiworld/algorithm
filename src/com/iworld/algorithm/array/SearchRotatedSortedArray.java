package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 33. Search in Rotated Sorted Array  Medium
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * @date 2022/7/2 16:40
 */
public class SearchRotatedSortedArray {
    
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        return process(nums, 0, nums.length - 1, target);
    }
    
    private int process(int[] nums, int left, int right, int target) {
        if (left == right) {
            return target == nums[left] ? left : -1;
        }
        int mid = left + ((right - left) >> 1);
        int index = process(nums, left, mid, target);
        if (index == -1) {
            index = process(nums, mid + 1, right, target);
        }
        return index;
    }
    
    public int search2(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        return process2(nums, 0, nums.length - 1, target);
    }
    
    private int process2(int[] nums, int left, int right, int target) {
        if (left == right) {
            return target == nums[left] ? left : -1;
        }
        int mid = left + ((right - left) >> 1);
        if (nums[mid] == target) {
            return mid;
        }
        // 左边是有序的
        if (nums[mid] >= nums[left]) {
            // 并且在左边范围
            if (nums[mid] > target && nums[left] <= target) {
                return process2(nums, left, mid - 1, target);
            } else {
                return process2(nums, mid + 1, right, target);
            }
        } else {
            // 右边界有序 并且在右边界范围
            if (nums[mid] < target && nums[right] >= target) {
                return process2(nums, mid + 1, right, target);
            } else {
                return process2(nums, left, mid - 1, target);
            }
        }
    }
    
    public static void main(String[] args) {
        SearchRotatedSortedArray searchOrderArray = new SearchRotatedSortedArray();
        int[] nums = {1, 3};
        int target = 0;
        System.out.println(searchOrderArray.search(nums, target));
        System.out.println(searchOrderArray.search2(nums, target));
    }
}
