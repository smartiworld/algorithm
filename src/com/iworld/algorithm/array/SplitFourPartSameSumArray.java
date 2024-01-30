package com.iworld.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.6.5 能否将数组切割为累加和相等的四份
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 * 例如:
 * arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
 * 三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
 * 累加和都是5
 * @date 2024/1/29 17:24
 */
public class SplitFourPartSameSumArray {
    
    public static boolean isCanSplitFourPart(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }
        int len = nums.length;
        int preSum = 0;
        int rest = 4;
        for (int i = 0; i <= len - 2 * (rest - 1); i++) {
            preSum += nums[i];
            boolean can = splitProcess(nums, i + 2, rest - 1, preSum);
            if (can) {
                return can;
            }
        }
        return false;
    }
    
    private static boolean splitProcess(int[] nums, int index, int rest, int sum) {
        if (rest == 0 && index >= nums.length) {
            return true;
        }
        int curSum = 0;
        for (int i = index; i <= nums.length - 2 * (rest - 1); i++) {
            curSum += nums[i];
            if (curSum > sum) {
                return false;
            }
            if (curSum == sum) {
                return splitProcess(nums, i + 2, rest - 1, sum);
            }
        }
        return false;
    }
    
    public static boolean isCanSplitFourPartOpt(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }
        // 记录累加和终止位置 key累加和 value结束位置
        Map<Integer, Integer> sumIndex = new HashMap<>();
        int preSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumIndex.put(preSum, i);
            preSum += nums[i];
        }
        int rest = 3;
        int firstPartSum = nums[0];
        for (int i = 1; i <= nums.length - (2 * rest); i++) {
            // 左边一刀加左右两边值
            int leftPart = firstPartSum + nums[i] + firstPartSum;
            if (sumIndex.containsKey(leftPart)) {
                // 左边 两刀加两边值
                int secondIndex = sumIndex.get(leftPart);
                leftPart += firstPartSum + nums[secondIndex];
                if (sumIndex.containsKey(leftPart)) {
                    int thirdIndex = sumIndex.get(leftPart);
                    if (preSum == leftPart + firstPartSum + nums[thirdIndex]) {
                        return true;
                    }
                }
            }
            firstPartSum += nums[i];
        }
        return false;
    }
    
    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        // key 某一个累加和， value出现的位置 key前缀和 value当前前缀和的下一位置 最后位置累加和不需要记录位置
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        // 第一刀左侧的累加和
        int lsum = arr[0];
        // s1是第一刀的位置
        for (int s1 = 1; s1 < arr.length - 5; s1++) {
            // 左侧一刀位置和第一刀切的位置 第二刀位置和 如果存在
            int checkSum = lsum * 2 + arr[s1];
            // 找到第二刀开始的位置
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum);
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)) {
                    int s3 = map.get(checkSum);
                    if (checkSum + arr[s3] + lsum == sum) {
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2};
        System.out.println(isCanSplitFourPart(nums));
        System.out.println(isCanSplitFourPartOpt(nums));
    }
}
