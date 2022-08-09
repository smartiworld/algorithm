package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 41.First Missing Positive   Hard
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * https://leetcode.com/problems/first-missing-positive/
 * @date 2022/8/8 14:25
 */
public class FirstMissingPositive {
    /**
     * 数组放的数为对应下标+1
     * 使用两个下标 l表示从小到大填充的位置 下标对应值-1
     * r表示当前数组最大 最小正数 就是当前数组从1到r
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int r = nums.length;
        int l = 0;
        while (l < r) {
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] <= l || nums[l] > r || nums[l] == nums[nums[l] - 1]) {
                // 大于r 不需要=r =r表示当前数组有对应位置存放  只有在没有位置存放的时候才会放--r的位置
                // nums[l] <= l || nums[l] > r || nums[l] == nums[nums[l] - 1] 没有地方存放
                // 小于等于当前下标的 到此下标表示小于等于此数已处理完毕
                swap(nums, l, --r);
            } else {
                // 如果等于最大值并且最大值所在位置没有等于当前值 就通过位置放
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }
    
    private void swap(int[] nums, int l, int r) {
        if (l == r) {
            return ;
        }
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int[] nums = {2, 1};
        System.out.println(firstMissingPositive.firstMissingPositive(nums));
    }
}
