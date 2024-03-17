package com.iworld.algorithm.strings;

import com.iworld.algorithm.util.ArrayUtil;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.7.1 返回一个数组上需要排序最短子数组长度
 * 给定一个无序数组arr，如果只能再一个子数组上排序
 *
 * 返回如果让arr整体有序，需要排序的最短子数组长度
 * @date 2023/11/2 21:47
 */
public class MinSortSubArrayLength {
    
    public static int getMinSortSubLength(int[] arr) {
        int len = arr.length;
        // 从左向右找最右不符合的位置 当前值记录i左侧最大值
        int max = arr[0];
        int rightIndex = -1;
        for (int i = 1; i < len; i++) {
            // 只要当前位置小于左边最大位置 说明当前位置不满足排序要求 是需要排序的 直到右边都满足大于左边最大位置
            if (arr[i] < max) {
                rightIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        int leftIndex = -1;
        int min = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            // 从右向左找最小 如果当前位置大于了右边的最小值 表示当前位置是需要排序的  直到左边值都小于右边最小值时
            if (arr[i] > min) {
                leftIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        return rightIndex - leftIndex + 1;
    }
    
    public static int a(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }
    
    public static void main(String[] args) {
        int len = 20;
        int max = 30;
        
        int count = 10;
        for (int i = 0; i < count; i++) {
            int[] arr = ArrayUtil.generateIntArray(len, max);
            int minSortSubLength = getMinSortSubLength(arr);
            ArrayUtil.printArray(arr);
            System.out.println(minSortSubLength);
            System.out.println();
        }
    }
}
