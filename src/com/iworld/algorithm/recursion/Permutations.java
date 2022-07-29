package com.iworld.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 46.Permutations     Medium
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 * https://leetcode.com/problems/permutations/
 * @date 2022/7/28 18:30
 */
public class Permutations {
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process(nums, 0, result);
        return result;
    }
    
    private void process(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> everResult = new ArrayList<>();
            for (int num : nums) {
                everResult.add(num);
            }
            result.add(everResult);
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            process(nums, index + 1, result);
            swap(nums, index, i);
        }
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2, 3};
        System.out.println(permutations.permute(nums));
    }
}
