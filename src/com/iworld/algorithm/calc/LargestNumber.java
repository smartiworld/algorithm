package com.iworld.algorithm.calc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author gq.cai
 * @version 1.0
 * @description 179. Largest Number   Medium
 *
 * Given a list of non-negative integers nums,
 * arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 * https://leetcode.com/problems/largest-number/
 * @date 2022/8/29 15:42
 */
public class LargestNumber {
    
    public String largestNumber(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return String.valueOf(nums[0]);
        }
        String[] sNums = new String[n];
        for (int i = 0; i < n; i++) {
            sNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(sNums, new DictionaryComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(sNums[i]);
        }
        String ans = sb.toString();
        int index = 0;
        for (int i = 0; i < ans.length(); i++) {
            if ('0' != ans.charAt(i)) {
                break;
            }
            index++;
        }
        return index == ans.length() ? "0" : ans.substring(index);
    }
    
    static class DictionaryComparator implements Comparator<String> {
        
        @Override
        public int compare(String s1, String s2) {
            return (s2 + s1).compareTo(s1 + s2);
        }
    }
}
