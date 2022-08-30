package com.iworld.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 128. Longest Consecutive Sequence  Medium
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -10^9 <= nums[i] <= 10^9
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * @date 2022/8/29 19:23
 */
public class LongestConsecutiveSequence {
    
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!counter.containsKey(nums[i])) {
                counter.put(nums[i], 1);
                int preLen = counter.get(nums[i] - 1) == null ? 0 : counter.get(nums[i] - 1);
                int postLen = counter.get(nums[i] + 1) == null ? 0 : counter.get(nums[i] + 1);
                int length = postLen + preLen + 1;
                counter.put(nums[i] - preLen, length);
                counter.put(nums[i] + postLen, length);
                max = Math.max(length, max);
            }
        }
        return max;
    }
    
    // LSD radix sort for ints (sedgewick and wayne)
    public static void sort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        for (int d = 0; d < 4; d++) {
            int[] count = new int[257];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> 8 * d) & 255;
                count[c + 1]++;
            }
            for (int r = 0; r < 256; r++) {
                count[r + 1] += count[r];
            }
            if (d == 3) {
                int shift2 = count[128];
                int shift1 = count[256] - shift2;
                for (int r = 0; r < 128; r++) {
                    count[r] += shift1;
                }
                for (int r = 128; r < 256; r++) {
                    count[r] -= shift2;
                }
            }
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> 8*d) & 255;
                aux[count[c]++] = a[i];
            }
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
    public int longestConsecutive2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        sort(nums);
        int n = 1, m = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i]-nums[i-1] == 1) {
                n++;
            } else if (nums[i]-nums[i-1] != 0) {
                n = 1;
            }
            if (n > m) {
                m = n;
            }
        }
        return m;
    }
    
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutive(nums));
    }
}
