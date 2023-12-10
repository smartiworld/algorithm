package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.2.3 数组左右子数组最大值最大差值
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的 作为右部分。
 *
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的， 左部分最大值减去右部分最大值的绝对值。
 * @date 2023/12/8 19:18
 */
public class MaxAbsoluteInArrayLeftRight {
    /**
     * 左右部分 最大值的最大差值
     * 左右边界找出小值
     * 遍历数组找到数组整个最大值
     * 将最大值分配到最小值对面
     * 单调性 数组扩大 最大值可能会变大
     * @param nums
     * @return
     */
    public static int maxAbsolute(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int leftNum = nums[0];
        int rightNum = nums[len - 1];
        int min = Math.min(leftNum, rightNum);
        int max = leftNum;
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
        }
        return max - min;
    }
}
