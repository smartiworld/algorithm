package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.2.6 多数之和
 * 给定一个有序数组arr，给定一个正数aim
 *
 * 1）返回累加和为aim的，所有不同二元组
 *
 * 2）返回累加和为aim的，所有不同三元组
 * @date 2023/12/11 11:20
 */
public class SumToAimInArray {

    public static int[][] towSumToAim(int[] nums, int aim) {
        int len = nums.length;
        int[][] ans = new int[len][2];
        int left = 0;
        int right = len - 1;
        int i = 0;
        while (left < right) {
            if (nums[left] + nums[right] < aim) {
                while (left + 1 < right && nums[left + 1] == nums[left]) {
                    left ++;
                }
                left++;
            } else if (nums[left] + nums[right] > aim) {
                while (right - 1 > left && nums[right] == nums[right - 1]) {
                    right --;
                }
                right --;
            } else {
                int[] record = {nums[left], nums[right]};
                ans[i++] = record;
                left ++;
                right --;
            }
        }
        return ans;
    }
    
    public static int[][] thirdSumToAim(int[] nums, int aim) {
        int len = nums.length;
        int[][] ans = new int[len][3];
        int index = 0;
        for (int i = 0; i < len; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < aim) {
                    while (left + 1 < right && nums[left + 1] == nums[left]) {
                        left ++;
                    }
                    left++;
                } else if (nums[i] + nums[left] + nums[right] > aim) {
                    while (right - 1 > left && nums[right] == nums[right - 1]) {
                        right --;
                    }
                    right --;
                } else {
                    int[] record = {nums[i], nums[left], nums[right]};
                    ans[index++] = record;
                    while (left + 1 < right && nums[left + 1] == nums[left]) {
                        left ++;
                    }
                    while (right - 1 > left && nums[right] == nums[right - 1]) {
                        right --;
                    }
                    left ++;
                    right --;
                }
            }
            while (i + 1 < len && nums[i] == nums[i + 1]) {
                i ++;
            }
        }
        return ans;
    }
}
