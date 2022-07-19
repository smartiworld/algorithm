package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * 一个长度N的数组 可以组成N平方数值对
 * 例如 arr={3,1,2}
 * 生成数值对 (3,3),(3,1),(3,2),(1,3),(1,1)(1,2),(2,3),(2,1),(2,2)
 * 任意两个数可以组成数值对，包含自己 排序规则 先按照第一个元素  然后按照第二个元素
 * (1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3)
 * 给定一个数组arr 和一个整数k 返回生成数字对 第k小的数值对
 * @date 2022/7/19 14:50
 */
public class FindIndexInDoubleArray {
    
    /**
     * 1.默认排序数组 每个位置可以组的数字对为数组长度
     * 所以首位落在位置在(k-1)/n
     * 2.查找小于第一步值的个数a和等于第一步值的个数b
     * 3.使用公式(k-a*n - 1)/b
     * @param nums
     * @return
     */
    public int[] findIndex(int[] nums, int k) {
        int n = nums.length;
        if (k > n * n) {
            return null;
        }
        int firstNum = getSortIndexNum(nums, (k - 1) / n);
        int a = 0;
        int b = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < firstNum) {
                a++;
            } else if (nums[i] == firstNum) {
                b++;
            }
        }
        int secondNum = getSortIndexNum(nums, (k - (a * n) - 1) / b);
        return new int[]{firstNum, secondNum};
    }
    
    private int getSortIndexNum(int[] nums, int index) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int l = left;
            int b = right - 1;
            int num = nums[left + (int)(Math.random() * (right - left))];
            int i = left;
            while (i <= b) {
                if (nums[i] < num) {
                    swap(nums, l++, i++);
                } else if (nums[i] > num) {
                    swap(nums, i, b--);
                } else {
                    i++;
                }
            }
            if (l <= index && index <= b) {
                break;
            } else if (index < l) {
                right = l - 1;
            } else {
                left = b + 1;
            }
        }
        return nums[index];
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    public static void main(String[] args) {
        int[] nums = {3,1,2};
        FindIndexInDoubleArray findIndexInDoubleArray = new FindIndexInDoubleArray();
        int[] sortIndexNum = findIndexInDoubleArray.findIndex(nums, 9);
        System.out.println(sortIndexNum[0] + "," + sortIndexNum[1]);
    }
}
