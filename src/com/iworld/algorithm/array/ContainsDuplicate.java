package com.iworld.algorithm.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 217. Contains Duplicate
 * Easy
 * 6366
 * 1018
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * https://leetcode.com/problems/contains-duplicate/
 * @date 2022/9/2 9:41
 */
public class ContainsDuplicate {
    
    public boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return nums[0] == nums[1];
        }
        for (int i = n - 1; i >= 0; i--) {
            down(nums, i, n);
        }
        int heapSize = n;
        while (heapSize > 0) {
            swap(nums, 0, --heapSize);
            down(nums, 0, heapSize);
            if (heapSize < n - 1 && nums[heapSize] == nums[heapSize + 1]) {
                return true;
            }
        }
        return nums[0] == nums[1];
    }
    
    private void heapSort(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            down(nums, i, n);
        }
        int heapSize = nums.length;
        while (heapSize > 0) {
            swap(nums, 0, --heapSize);
            down(nums, 0, heapSize);
            
        }
    }
    
    private void down(int[] nums, int index, int heapSize) {
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int large = left + 1 < heapSize && nums[left + 1] > nums[left] ? left + 1 : left;
            large = nums[large] > nums[index] ? large : index;
            if (large == index) {
                break;
            }
            swap(nums, large, index);
            index = large;
            left = (index << 1) + 1;
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
    
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> map = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.contains(nums[i])) {
                return true;
            }
            map.add(nums[i]);
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,9,1,3,5,4,3,2,4,6};
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        containsDuplicate.heapSort(nums);
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }
}
