package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 78.子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * 链接：https://leetcode.cn/problems/subsets
 * @date 2022/5/30 17:01
 */
public class SubSets {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process(nums, 0, result, new HashSet<>());
        return result;
    }
    
    private void process(int[] nums, int index, List<List<Integer>> result, HashSet<Integer> everResult) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(everResult));
            return ;
        }
        process(nums, index + 1, result, everResult);
        everResult.add(nums[index]);
        process(nums, index + 1, result, everResult);
        everResult.remove(nums[index]);
    }
    
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process2(nums, 0, result, new HashSet<>());
        return result;
    }
    
    private void process2(int[] nums, int index, List<List<Integer>> result, HashSet<Integer> everResult) {
        result.add(new ArrayList<>(everResult));
        for (int i = index; i < nums.length; i++) {
            everResult.add(nums[i]);
            process2(nums, i + 1, result, everResult);
            everResult.remove(nums[i]);
        }
    }
    
    
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        SubSets subSets = new SubSets();
        System.out.println(subSets.subsets(nums));
        System.out.println(subSets.subsets2(nums));
    }
}
