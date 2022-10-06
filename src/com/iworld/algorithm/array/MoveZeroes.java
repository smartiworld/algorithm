package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 283. Move Zeroes
 * Easy
 * 11263
 * 280
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 * Follow up: Could you minimize the total number of operations done?
 * https://leetcode.com/problems/move-zeroes/
 * @date 2022/10/5 20:42
 */
public class MoveZeroes {
    
    public void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, zeroIndex++, i);
            }
        }
    }
    
    public void moveZeroes2(int[] nums) {
        int to = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, to++, i);
            }
        }
    }
    
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        MoveZeroes moveZeroes = new MoveZeroes();
        //moveZeroes.moveZeroes(nums);
        moveZeroes.moveZeroes2(nums);
    }
}
