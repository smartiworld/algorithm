package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 18.四数之和
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * 提示：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * 链接：https://leetcode.cn/problems/4sum
 * @date 2022/5/21 23:55
 */
public class FourNumSum {
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        int length = nums.length;
        Arrays.sort(nums);
        if (nums[length - 1] < 0 && target > nums[length - 1]) {
            return result;
        }
        for(int i = 0; i < length - 3; i++) {
            // 当前位置大于零 并且当前位置大于目标值 后续无解
            if (nums[i] > 0 && nums[i] > target) {
                break;
            }
            // 目前位置最小的四数相加 大于目标值 所有无解
            if ((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 目前位置 所求最大值小于目标值 当前位置无解
            if ((long)nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            // 重复值解 跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for(int j = i + 1; j < length - 2; j++) {
                // 第二个位置和第一个位置和  大于目标值 无解
                if ((long)nums[i] + nums[j] > 0 && nums[i] + nums[j] > target) {
                    break;
                }
                // 第二个位置 所得最小值  大于目标值 无解
                if ((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 第二个位置 所得最大值  小于于目标值 当前位置无解
                if ((long)nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                // 跳过重复值
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1;
                int r = length - 1;
                while (l < r) {
                    long fourSum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (fourSum == (long) target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if (fourSum > target){
                        r--;
                    } else {
                        l++;
                    }
                }
            }
            
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {0,0,0,1000000000,1000000000,1000000000,1000000000};
        int target = 1000000000;
        FourNumSum fourNumSum = new FourNumSum();
        System.out.println(fourNumSum.fourSum(nums, target));
    }
}
