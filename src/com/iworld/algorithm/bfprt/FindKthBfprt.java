package com.iworld.algorithm.bfprt;

/**
 * @author gq.cai
 * @version 1.0
 * @description 寻找未排序数组从大/小第多少位置元素
 * bfprt 是精挑细选一个数做基数 根据此基数进行partition 快排是随机一个数
 * 1.将数组中每五个划分成一个小数组
 * 2.对五个数的小数组进行排序 挑选出中位数（偶数个数 挑上中位数） 组成新的数组
 * 3.再从新的数组中挑选出中位数
 * @date 2022/7/24 13:14
 */
public class FindKthBfprt {
    
    public static int minKth(int[] nums, int k) {
        // 数组下标从0开始 所以在数组中第k的数 在数组下标则为k-1
        return bfprt(nums, 0, nums.length - 1, k - 1);
    }
    
    /**
     * 给定数组范围内找出排序好的第index位置元素
     * @param nums
     * @param left
     * @param right
     * @param index
     * @return
     */
    public static int bfprt(int[] nums, int left, int right, int index) {
        if (left == right) {
            return nums[left];
        }
        int medianOfMedians = medianOfMedians(nums, left, right);
        int[] partition = partition(nums, left, right, medianOfMedians);
        if (index >= partition[0] && index <= partition[1]) {
            return nums[index];
        } else if (index < partition[0]) {
            return bfprt(nums, left, partition[0] - 1, index);
        } else {
            return bfprt(nums, partition[1] + 1, right, index);
        }
    }
    
    /**
     * 1.数组 每五个划分一组
     * 2.每个小组内部排序
     * 3.每个小组中位数挑选出来（偶数为上中位数） 组成新的数组
     * 4.从新数组中挑选出中位数
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int medianOfMedians(int[] nums, int left, int right) {
        int size = right - left + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        // 1.数组 每五个划分一组  创建一个放中位数的数组
        int[] help = new int[size / 5 + offset];
        for (int i = 0; i < help.length; i++) {
            int groupFirst = left + i * 5;
            int groupEnd = Math.min(right, groupFirst + 4);
            // 2.每个小组内部排序
            // 3.每个小组中位数挑选出来（偶数为上中位数） 组成新的数组
            help[i] = getGroupMedian(nums, groupFirst, groupEnd);
        }
        // 4.从新数组中挑选出中位数 新数组中位数则为len/2
        return bfprt(help, 0, help.length - 1, help.length >> 1);
    }
    
    private static int getGroupMedian(int[] nums, int left, int right) {
        insertSort(nums, left, right);
        return nums[left + ((right - left) >> 1)];
    }
    
    private static void insertSort(int[] nums, int left, int right) {
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j > left; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }
    
    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
    
    private static int[] partition(int[] nums, int left, int right, int baseNum) {
        int l = left;
        int b = right;
        int index = left;
        while (index <= b) {
            if (nums[index] < baseNum) {
                swap(nums, l++, index++);
            } else if (nums[index] > baseNum) {
                swap(nums, index, b--);
            } else {
                index++;
            }
        }
        return new int[]{l, b};
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 8, 9, 1, 10, 9, 5, 9, 2, 17};
        int k = 10;
        System.out.println(minKth(nums, k));
        insertSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }
}
