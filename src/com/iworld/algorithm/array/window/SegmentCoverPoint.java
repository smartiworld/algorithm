package com.iworld.algorithm.array.window;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个有序数组 从左向右依次表示x轴上的点的位置
 * 给定一个正整数k表示右一根绳子长度为k，最多可以覆盖多少个点
 * @date 2022/11/8 16:57
 */
public class SegmentCoverPoint {
    
    /**
     * 条件满足扩张右 一直扩张 知道不满足条件的时候 左再走
     * @param nums
     * @param k
     * @return
     */
    public static int maxCover(int[] nums, int k) {
        int ans = 0;
        int len = nums.length;
        for (int i = 0, left = 0; left < len; left++) {
            while (i < len && nums[i] - nums[left] <= k) {
                ans = Math.max(ans, i - left + 1);
                i++;
            }
        }
        return ans;
    }
    
    public static int maxPoint(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int n = arr.length;
        int max = 0;
        while (left < n) {
            while (right < n && arr[right] - arr[left] <= k) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }
    
    public static void main(String[] args) {
        int len = 10;
        int max = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint(arr, L);
            int ans2 = maxCover(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans3) {
                System.out.println("oops1!");
                break;
            }
            if (ans2 != ans3) {
                System.out.println("oops2!");
                for (int num : arr) {
                    System.out.print(num + ",");
                }
                System.out.println();
                System.out.println(L);
                System.out.println(ans2 + "===" + ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
//    public static void main(String[] args) {
//        int[] nums = {17,22,34,48,57,64,86,95,95,96};
//        int k = 6;
//        System.out.println(maxCover(nums, k));
//    }
    
    
    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }
    
    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }
}
