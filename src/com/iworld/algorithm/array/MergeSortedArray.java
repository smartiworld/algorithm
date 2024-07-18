package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 88. Merge Sorted Array   Easy
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * Example 1:
 *
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * Example 2:
 *
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * Example 3:
 *
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 *
 *
 * Constraints:
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10 9 <= nums1[i], nums2[j] <= 10 9
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 * @date 2022/8/18 16:03
 */
public class MergeSortedArray {
    
    /**
     * 从nums1尾部开始填充 比较m和n所在位置大值放在当前nums1 r位置然后
     * 继续较大值下标和r下标向前走 n结束和r到初始位置表示要结束
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r = nums1.length - 1;
        int mm = m - 1;
        int nn = n - 1;
        while (r >= 0) {
            if (nn == -1) {
                break;
            } else if (mm == -1) {
                nums1[r--] = nums2[nn--];
            } else {
                nums1[r--] = nums1[mm] > nums2[nn] ? nums1[mm--] : nums2[nn--];
            }
         }
    }
    
}
