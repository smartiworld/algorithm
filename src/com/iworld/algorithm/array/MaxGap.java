package com.iworld.algorithm.array;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.3.1 最大间隙
 * 给定一个无序数组arr，返回如果排序之后，相邻数之间的最大差值
 *
 * {3,1,7,9}，如果排序后{1,3,7,9}，相邻数之间的最大差值来自3和7，返回4
 *
 * 要求：不能真的进行排序，并且要求在时间复杂度O(N)内解决
 * @date 2023/3/20 20:49
 */
public class MaxGap {
    
    /**
     * 基数桶的方式
     * 1.使用辅助数组 数组长度为原数组长度+1 （目的是保证同一个桶内不需要关注中间值 只需要记住最大值最小值 辅助数组肯定有一个桶没有数据 这样就能保证 空桶两边差值是大于同一个桶内差值的）
     * 2.找出数组最大最小值 分别放入第一个桶和最后一个桶 保证前面有桶 空桶一定放在中间
     * 3.遍历数组 计算每一个位置数字应该放入桶的位置，如果存在则比较最大最小 没有值 直接放入
     * 4.最后统计差值 如果当前桶放过数字 用当前桶最小减前一个桶的最大值
     * @param nums
     * @return
     */
    public int maxGap(int[] nums) {
        try {
            if (nums == null || nums.length < 2) {
                return 0;
            }
            int len = nums.length;
            if (len == 2) {
                return Math.abs(nums[0] - nums[1]);
            }
            // 记录每个位置最大值
            int[] maxs = new int[len + 1];
            // 记录每个位置最小值
            int[] mins = new int[len + 1];
            int[] haves = new int[len + 1];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            if (min == max) {
                return 0;
            }
            for (int i = 0; i < len; i++) {
                int index = getIndex(max, min, nums[i], len);
                try {
                    if (haves[index] == 0) {
                        mins[index] = nums[i];
                        maxs[index] = nums[i];
                        haves[index] = 1;
                    } else {
                        mins[index] = Math.min(mins[index], nums[i]);
                        maxs[index] = Math.max(maxs[index], nums[i]);
                    }
                } catch (Exception e) {
                    System.out.println("index===" + index + "===max===" + max + "===min===" + min + "===i===" + i + "===len===" + len + "===nums[i]===" + nums[i]);
                    throw e;
                }
            }
            int pre = 0;
            int ans = Integer.MIN_VALUE;
            for (int i = 1; i <= len; i++) {
                if (haves[i] == 1) {
                    ans = Math.max(mins[i] - maxs[pre], ans);
                    pre = i;
                }
            }
            return ans;
        } catch (Exception e) {
            System.out.println("{");
            for (int num : nums) {
                System.out.print(num + ",");
            }
            System.out.println("}");
            throw e;
        }
        
    }
    
    private int getIndex(int max, int min, int num, int len) {
        return ((num - min) * len / (max - min));
    }
    
    public static void main(String[] args) {
        int[] nums = {3,1,7,9, -5};
        MaxGap maxGap = new MaxGap();
        //System.out.println(maxGap.maxGap(nums));
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        while (succeed) {
            for (int i = 0; i < testTime; i++) {
                int[] arr1 = generateRandomArray(maxSize, maxValue);
                int[] arr2 = copyArray(arr1);
                if (maxGap.maxGap(arr1) != comparator(arr2)) {
                    printArray(arr1);
                    succeed = false;
                    break;
                }
            }
            System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        }
        System.out.println("end");
    }
    
    private static void printArray(int[] arr) {
        System.out.println("{");
        for (int num : arr) {
            System.out.print(num + ",");
        }
        System.out.println("}");
    }
    
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    
    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    
    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }
    
    public static int maxGap1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        // 不止一种数，min~max一定有range,  len个数据，准备len+1个桶
        boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
        int[] maxs = new int[len + 1];  // maxs[i] i号桶收集的所有数字的最大值
        int[] mins = new int[len + 1];  // mins[i] i号桶收集的所有数字的最小值
        
        int bid = 0; // 桶号
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0]; // 上一个非空桶的最大值
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }
    
    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}
