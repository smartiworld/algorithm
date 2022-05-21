package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 961.在长度2N的数组中查找重复N次的数字
 * 给你一个整数数组 nums ，该数组具有以下属性：
 *
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,3]
 * 输出：3
 * 示例 2：
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 * 示例 3：
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 * 提示：
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 104
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 * 链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
 * @date 2022/5/21 22:19
 */
public class FindHalfNum {
    
    /**
     * 利用快排分区
     * @param nums
     * @return
     */
    public int repeatedNTimes(int[] nums) {
        if (nums == null || (nums.length % 2 > 0)) {
            return -1;
        }
        return process(nums, 0, nums.length - 1);
    }
    
    private int process(int[] nums, int l, int r) {
        if (l >= r ) {
            return -1;
        }
        int s = l;
        int b = r;
        int compareNum = nums[l + (int)(Math.random() * (r - l))];
        int curIndex = l;
        while (curIndex <= b) {
            if (nums[curIndex] < compareNum) {
                swap(nums, s ++, curIndex ++);
            } else if (nums[curIndex] > compareNum) {
                swap(nums, curIndex, b --);
            } else {
                curIndex ++;
            }
        }
        if (b - s + 1 == nums.length >> 1)  {
            return nums[b];
        }
        if (r - b < s - l) {
            return process(nums, l, s - 1);
        } else {
           return process(nums, b + 1, r);
        }
    }
    
    public int repeatedNTimes2(int[] nums) {
        if (nums == null || (nums.length % 2 > 0)) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int s = l;
            int b = r;
            int compareNum = nums[l + (int)(Math.random() * (r - l))];
            int curIndex = l;
            while (curIndex <= b) {
                if (nums[curIndex] < compareNum) {
                    swap(nums, s ++, curIndex ++);
                } else if (nums[curIndex] > compareNum) {
                    swap(nums, curIndex, b --);
                } else {
                    curIndex ++;
                }
            }
            if (b - s + 1 == nums.length >> 1) {
                return nums[b];
            }
            if (r - b > s - l) {
                l = b + 1;
            } else {
                r = s - 1;
            }
        }
        return -1;
    }
    
    public int repeatedNTimes3(int[] nums) {
        int c = nums.length >> 1;
        int[] n = new int[10000];
        for (int num : nums) {
            n[num]++;
            if (n[num] == c){
                return num;
            }
        }
        return -1;
    }
    
    /**
     * 题意说有n+1个不同元素 表示除当前元素重复外其他元素不会重复
     * @param nums
     * @return
     */
    public int repeatedNTimes4NoSame(int[] nums) {
        List<Integer> bucket = new ArrayList<>();
        for (int num : nums) {
            if (bucket.contains(num)) {
                return num;
            }
            bucket.add(num);
        }
        
        return -1;
    }
    
    private void swap(int[] nums, int l, int r) {
        if (l == r) {
            return ;
        }
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    public static void main(String[] args) {
        int[] nums = {6,5,1,5,2,5,3,5,4,5,9,5};
        FindHalfNum findHalfNum = new FindHalfNum();
        System.out.println(findHalfNum.repeatedNTimes(nums));
        System.out.println(findHalfNum.repeatedNTimes2(nums));
        System.out.println(findHalfNum.repeatedNTimes4NoSame(nums));
    }
}
