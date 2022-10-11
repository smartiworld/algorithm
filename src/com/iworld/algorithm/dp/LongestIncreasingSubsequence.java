package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 300. Longest Increasing Subsequence
 * Medium
 * 15296
 * 271
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or
 * no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * https://leetcode.com/problems/longest-increasing-subsequence/submissions/
 * @date 2022/10/11 19:58
 */
public class LongestIncreasingSubsequence {
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 1;
        dp[0] = 1;
        // 存数组下标 按顺序存放下标 存在下标值 一定是递增的
        int[] end = new int[n];
        end[0] = 0;
        int eIndex = 1;
        for (int i = 1; i < n; i++) {
            int l = 0;
            int r = eIndex - 1;
            int mid = l + ((r - l) >> 1);;
            while (l <= r) {
                // 找出end数组中的值 mid位置>=当前位置并且 当前位置大于mid-1位置 此时找出离i最近下标
                if (nums[end[mid]] >= nums[i] && (mid == 0 || nums[end[mid - 1]] < nums[i])) {
                    break;
                }
                // 没有找到符合条件的mid值 如果当前位置大于end中mid下标值 将二分查找l放到mid+1位置 否则放到mid-1位置
                if (nums[end[mid]] < nums[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
                mid = l + ((r - l) >> 1);
            }
            // 数组中没有找到大于等于当前值的位置 需要在end数组中开拓新的位置
            // 此时i位置值一定大于end[l - 1]位置值 此时 最长升序子序列为1 + 前一个dp[end[l - 1]]
            if (l == eIndex) {
                dp[i] = 1 + dp[end[l - 1]];
                end[eIndex++] = i;
            } else {
                // 此时找到大于等于当前位置mid 此时直接顶替当前下标 并且最大长度和当前相等
                dp[i] = dp[end[mid]];
                end[mid] = i;
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    
    /**
     * lengthOfLIS简化版本 少使用内存空间
     * @param nums
     * @return
     */
    public static int lengthOfLIS3(int[] nums) {
        int n = nums.length;
        // 存数组下标 按顺序存放下标 存在下标值 一定是递增的
        int[] end = new int[n];
        end[0] = nums[0];
        int eIndex = 0;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int l = 0;
            int r = eIndex;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                // 没有找到符合条件的mid值 如果当前位置大于end中mid下标值 将二分查找l放到mid+1位置 否则放到mid-1位置
                if (end[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            eIndex = Math.max(l, eIndex);
            end[l] = nums[i];
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }
    
    /**
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for (int j = i - 1; j >=0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, 1 + dp[j]);
                }
            }
            dp[i] = max;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
    }
}
