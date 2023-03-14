package com.iworld.algorithm.array;

import java.util.TreeSet;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.1.2
 * 给定一个数组arr，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子数组累加和
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class01/Code02_MaxSubArraySumLessOrEqualK.java
 * @date 2023/3/14 13:28
 */
public class MaxSubArraySumLessOrEqualK {
    
    
    public int getMaxLessOrEqualK(int[] arr, int k) {
        // 记录i之前的，前缀和，按照有序表组织
        TreeSet<Integer> set = new TreeSet<Integer>();
        // 一个数也没有的时候，就已经有一个前缀和是0了
        set.add(0);
        int n = arr.length;
        // 前缀和
        int sum = 0;
        // 子数组以当前位置结尾
        int max = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            // 查找之前0-每个结束位置是否有满足大于sum-k的值 如果存在，此时以i结尾就存在小于等于k的子数组
            // 既存在0-X位置有大于sum-k 0-i的和为sum 0-i减去0-X 即为X-i的子数组 sum-(>=sum-k)<=K
            Integer ceiling = set.ceiling(sum - k);
            if (ceiling != null) {
                max = Math.max(max, sum - ceiling);
            }
            // 当前的前缀和加入到set中去
            set.add(sum);
        }
        return max;
    }
}
