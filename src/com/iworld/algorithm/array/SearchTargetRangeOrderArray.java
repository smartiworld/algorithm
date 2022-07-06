package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 34. Find First and Last Position of Element in Sorted Array   Medium
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @date 2022/7/2 22:07
 */
public class SearchTargetRangeOrderArray {
    
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                int left = mid;
                int right = mid;
                while (left - 1 >= 0 && nums[left] == nums[left - 1]) {
                    left --;
                }
                while (right + 1 <nums.length && nums[right] == nums[right + 1]) {
                    right ++;
                }
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }
    
    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int leftIndex = findLeftIndex(nums, target);
        if (leftIndex >= nums.length || nums[leftIndex] != target) {
            return new int[]{-1, -1};
        }
        // 查找目标值下一个元素最左位置
        int rightIndex = findLeftIndex(nums, target + 1);
        if (rightIndex > nums.length || nums[rightIndex - 1] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{leftIndex, rightIndex - 1};
    }
    
    /**
     * 查找数组中target元素的最左位置
     * @param nums    排序数组
     * @param target  目标值
     * @return
     */
    private int findLeftIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
        SearchTargetRangeOrderArray searchTargetRangeOrderArray = new SearchTargetRangeOrderArray();
        int[] nums = {1};
        int target = 1;
        System.out.println(searchTargetRangeOrderArray.searchRange2(nums, target));
    }
    
}
