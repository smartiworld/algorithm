package com.iworld.algorithm.array;

import java.util.HashMap;

/**
 * @author gq.cai
 * @version 1.0
 * @description 两数之和
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 * https://leetcode.com/problems/two-sum/
 * @date 2022/6/21 14:32
 */
public class TwoSum {
    
    public int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] + nums[j - i] == target) {
                    return new int[]{j - i, j};
                }
            }
        }
        return null;
    }
    public int[] twoSum2(int[] nums, int target) {
        // key = 数组中数字 value = 当前数字在数组中的下标
        HashMap<Integer, Integer> indexCache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (indexCache.containsKey(remain)) {
                return new int[]{indexCache.get(remain), i};
            }
            indexCache.put(nums[i], i);
        }
        return null;
    }
    
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] obj = twoSum.twoSum(new int[]{3, 2, 3}, 6);
        for (int i : obj) {
            System.out.print(i + ",");
        }
    }
}
