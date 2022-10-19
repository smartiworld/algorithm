package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 350. Intersection of Two Arrays II
 * Easy
 * 5467
 * 788
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * @date 2022/10/19 13:11
 */
public class IntersectionOfTwoArraysII {
    
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        int[] numCounts = new int[1001];
        for (int num : nums1) {
            numCounts[num]++;
        }
        for (int num : nums2) {
            if (numCounts[num] > 0) {
                result.add(num);
                numCounts[num]--;
            }
        }
        int[] ans = new int[result.size()];
        int index = 0;
        for (Integer num : result) {
            ans[index++] = num;
        }
        return ans;
    }
}
