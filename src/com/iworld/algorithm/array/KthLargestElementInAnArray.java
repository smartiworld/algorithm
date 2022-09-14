package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 215. Kth Largest Element in an Array
 * Medium
 * 11802
 * 597
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * You must solve it in O(n) time complexity.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -104 <= nums[i] <= 10^4
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * @date 2022/9/13 19:34
 */
public class KthLargestElementInAnArray {
    /**
     * time limit  bfprt
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        return findKthSmallElement(nums, 0, len - 1, len - k);
    }
    
    private int findKthSmallElement(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int median = medianOfMedian(nums, left, right);
        int[] partition = partition(nums, left, right, median);
        if (k >= partition[0] && k <= partition[1]) {
            return nums[partition[0]];
        } else if (k < partition[0]) {
            return findKthSmallElement(nums, left, partition[0] - 1, k);
        } else {
            return findKthSmallElement(nums, partition[1] + 1, right, k);
        }
    }
    
    private int medianOfMedian(int[] nums, int l, int r) {
        int len = r - l + 1;
        int hLen = len / 5 + (len % 5 != 0 ? 1 : 0);
        int[] help = new int[hLen];
        for (int i = 0; i < hLen; i++) {
            int groupStart = l + i * 5;
            int groupEnd = Math.min(groupStart + 4, r);
            help[i] = getSortMidNum(nums, groupStart, groupEnd);
        }
        return findKthSmallElement(help, 0, hLen - 1, hLen >> 1);
    }
    
    private int getSortMidNum(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j > start; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return nums[start + ((end - start) >> 1)];
    }
    
    private int[] partition(int[] nums, int l, int r, int base) {
        int less = l;
        int big = r;
        int index = l;
        while (index <= big) {
            if (nums[index] < base) {
                swap(nums, less++, index++);
            } else if (nums[index] == base) {
                index++;
            } else {
                swap(nums, index, big--);
            }
        }
        return new int[]{less, big};
    }
    
    public int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        int bk = len - k;
        while (l <= r) {
            int less = l;
            int big = r;
            int index = l;
            int rand = nums[l + (int)(Math.random() * (r - l))];
            while (index <= big) {
                if (nums[index] < rand) {
                    swap(nums, index++, less++);
                } else if (nums[index] == rand) {
                    index++;
                } else {
                    swap(nums, index, big--);
                }
            }
            if (less <= bk && bk <= big) {
                return nums[less];
            } else if (bk < less) {
                r = less - 1;
            } else {
                l = big + 1;
            }
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
        int[] nums = {3,2,1,5,6,4};
        int k = 5;
        KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, k));
        System.out.println(kthLargestElementInAnArray.findKthLargest2(nums, k));
    }
    
}
