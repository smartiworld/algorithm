package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/1/3 19:32
 */
public class MaxSumSubArray {
    /**
     * 从左向右累加记录到cur变量 过程中使用max变量抓住大值 当cur小于0的时候将cur变量设置为0
     * 1.当前数组中所有值小于等于0 的时候 cur 每走一次设置为0一次 每次累加前值肯定为0
     *   实际转换为max为数组上获取所有位置最大值
     * 2.当数组中同时存在正负值得时候 假如x~i为最大和的最长记录
     *   在x~i区间任意0~k的区间上都存在累加和不大于0 j~i-1区间累加和不可能存在大于0
     *   i+1~y区间累加和也不会大于0
     * 所以就出现在在x~i区间cur一定是正值 max越来越大
     * @param nums
     * @return
     */
    public static int maxSum(int[] nums) {
        // 记录当前累加和 如果累加和小于0 重新设置为0
        int cur = 0;
        // 记录当前累加和最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, cur + nums[i]);
            cur = Math.max(cur + nums[i], 0);
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, -1, -3, 3, -1, 2, 1};
        System.out.println(maxSum(nums));
    }
}
