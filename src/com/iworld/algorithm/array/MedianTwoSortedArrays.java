package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4. Median of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * @date 2022/6/22 14:43
 */
public class MedianTwoSortedArrays {
    /**
     * 合并说
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        boolean isEven = n % 2 == 0;
        int mind = n >> 1;
        int[] newArr = new int[mind + 1];
        int l = 0;
        int r = 0;
        int i = 0;
        while (l < nums1.length && r < nums2.length) {
            newArr[i] = nums1[l] <= nums2[r] ? nums1[l++] : nums2[r++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (newArr[i] + newArr[i - 1]) / 2;
                }
                return newArr[i];
            }
            i ++;
        }
        while (l < nums1.length) {
            newArr[i] = nums1[l++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (newArr[i] + newArr[i - 1]) / 2;
                }
                return newArr[i];
            }
            i ++;
        }
        while (r < nums2.length) {
            newArr[i] = nums2[r++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (newArr[i] + newArr[i - 1]) / 2;
                }
                return newArr[i];
            }
            i ++;
        }
        return 0;
    }
    
    /**
     *
     * 从两个数组上分别取出两个切点cut1，cut2
     * 切点左侧l1 l2切点右侧r1 r2
     * cut1+cut2=n1+n2
     *  L1 = A1[(C1-1)/2]; R1 = A1[C1/2];
     *  L2 = A2[(C2-1)/2]; R2 = A2[C2/2];
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 < n2) {
            // 保证nums2长度小于nums1
            return findMedianSortedArrays2(nums2, nums1);
        }
        int lo = 0, hi = n2 * 2;
        while (lo <= hi) {
            // Try Cut 2
            int mid2 = (lo + hi) / 2;
            // Calculate Cut 1 accordingly
            int mid1 = n1 + n2 - mid2;
            // Get L1, R1, L2, R2 respectively
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = (mid1 == n1 * 2) ? Integer.MAX_VALUE : nums1[(mid1) / 2];
            double R2 = (mid2 == n2 * 2) ? Integer.MAX_VALUE : nums2[(mid2) / 2];
        
            if (L1 > R2) {
                // A1's lower half is too big; need to move C1 left (C2 right)
                lo = mid2 + 1;
            }
            else if (L2 > R1) {
                // A2's lower half too big; need to move C2 left.
                hi = mid2 - 1;
            } else {
                // Otherwise, that's the right cut.
                return (Math.max(L1, L2) + Math.max(R1, R2)) / 2;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {19, 12, 11, 10, 9, 8, 7};
        int[] nums2 = {1};
        MedianTwoSortedArrays medianTwoSortedArrays = new MedianTwoSortedArrays();
        System.out.println(medianTwoSortedArrays.findMedianSortedArrays(nums1, nums2));
        System.out.println(medianTwoSortedArrays.findMedianSortedArrays2(nums1, nums2));
    }
}
