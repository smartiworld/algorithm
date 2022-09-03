package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 169. Majority Element
 * Easy
 * 11624
 * 375
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 * https://leetcode.com/problems/majority-element/
 * @date 2022/9/3 19:12
 */
public class MajorityElement {
    /**
     * majority element出现的数量一定是大于n/2
     * 一个变量记录当前数出现的次数 一个记录当前数
     * majority element 总数量一定其他数数量 最后一定会剩到ans变量
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        // 当前数出现的次数
        int count = 1;
        // 当前出现次数记录的数
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                ans = nums[i];
                count++;
            } else if (nums[i] == ans) {
                count++;
            } else {
                count--;
            }
        }
        return ans;
    }
}
