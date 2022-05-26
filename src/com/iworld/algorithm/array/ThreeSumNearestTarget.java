package com.iworld.algorithm.array;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 16.最接近目标值的三数之和
 *
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，
 * 使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 *
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * 提示：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 *
 * 链接：https://leetcode.cn/problems/3sum-closest
 * @date 2022/5/24 23:44
 */
public class ThreeSumNearestTarget {
    
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int preSum = Integer.MIN_VALUE;
        boolean isInit = false;
        // 数组最小值大于当前数 并且最小值是大于等于0的
        // 此时前3和为最接近和
        if (nums[0] >= target && nums[0] >= 0) {
            return nums[0] + nums[1] + nums[2];
        }
        int n = nums.length;
        // 数组最大值小于当前数 并且最大值是小于等于0的
        // 此时后3和为最接近和
        if (nums[n - 1] <= 0 && nums[n - 1] <= target) {
            return nums[n - 1] + nums[n - 1] + nums[n - 3];
        }
        
        for(int i = 0; i < n - 2; i++) {
            // 如果当前值大于目标值 并且当前值是大于等于0的 此时后续不需要再进行处理
            if (nums[i] >= target && nums[i] >= 0) {
                int threeSum = nums[i] + nums[i + 1] + nums[i + 2];
                // 如果之前已经有了结果
                if (isInit) {
                    // 当前三数和和前三数和对比
                    return Math.abs(threeSum - target) > Math.abs(target - preSum) ? preSum : threeSum;
                } else {
                    // 如果之前没有计算三数和 直接返回当前三数和
                    return threeSum;
                }
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int threeSum = nums[i] + nums[l] + nums[r];
                // 等于目标值 直接返回
                if (threeSum == target) {
                    return target;
                } else if (threeSum > target) {
                    // 当前三数和大于目标值 如果之前未处理三数和 或者当前三数和更接近目标值 直接赋值
                    if (!isInit || threeSum < preSum) {
                        preSum = threeSum;
                    } else if (preSum < target) {
                        // 如果上次计算三叔和 小于目标值  判断谁离得近使用谁
                        preSum = target - preSum > threeSum - target ? threeSum : preSum;
                    }
                    r --;
                } else {
                    // 当前三数和小于目标值 如果之前未处理三数和 或者当前三数和更接近目标值 直接赋值
                    if (!isInit || threeSum > preSum) {
                        preSum = threeSum;
                    } else if (preSum > target) {
                        // 如果上次计算三叔和 大于目标值  判断谁离得近使用谁
                        preSum = preSum - target > target - threeSum ? threeSum : preSum;
                    }
                    l ++;
                }
                isInit = true;
            }
        }
        return preSum;
    }
    // 会少走步骤需要三层循环
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int preSum = Integer.MIN_VALUE;
        boolean isInit = false;
        if (nums[0] >= target && nums[0] >= 0) {
            return nums[0] + nums[1] + nums[2];
        }
        int n = nums.length;
        if (nums[n -1] <= 0 && nums[n - 1] <= target) {
            return nums[n - 1] + nums[n - 1] + nums[n - 3];
        }
        
        for(int i = 0; i < n - 2; i++) {
            if (nums[i] >= target && nums[i] >= 0) {
                int threeSum = nums[i] + nums[i + 1] + nums[i + 2];
                if (isInit) {
                    return Math.abs(threeSum - target) > Math.abs(target - preSum) ? preSum : threeSum;
                } else {
                    return threeSum;
                }
                
            }
            int l = i + 1;
            while (l < n - 1) {
                int r = l + 1;
                while (r < n) {
                    int threeSum = nums[i] + nums[l] + nums[r];
                    if (threeSum == target) {
                        return target;
                    } else if (threeSum > target) {
                        if (!isInit) {
                            return threeSum;
                        } else if (preSum < target) {
                            preSum = target - preSum > threeSum - target ? threeSum : preSum;
                            if (l == i + 1 && r == i + 2) {
                                return preSum;
                            }
                        } else if (preSum > threeSum) {
                            preSum = threeSum;
                        }
                        break;
                    } else {
                        if (!isInit || threeSum > preSum) {
                            preSum = threeSum;
                        } else if (preSum > target) {
                            preSum = preSum - target > target - threeSum ? threeSum : Math.min(preSum, threeSum);
                        }
                    }
                    r ++;
                    isInit = true;
                }
                if (preSum > target) {
                    break;
                }
                l ++;
            }
        }
        return preSum;
    }
    
    public static void main(String[] args) {
        int[] nums = {-55,-24,-18,-11,-7,-3,4,5,6,9,11,23,33};
        int target = 0;
        ThreeSumNearestTarget threeSumNearestTarget = new ThreeSumNearestTarget();
        System.out.println(threeSumNearestTarget.threeSumClosest(nums, target));
        System.out.println(threeSumNearestTarget.threeSumClosest2(nums, target));
    }
}