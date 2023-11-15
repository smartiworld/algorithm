package com.iworld.algorithm.array.window;

/**
 * @author gq.cai
 * @version 1.0
 * @description 209. Minimum Size Subarray Sum
 *
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 *  whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 * @date 2023/11/14 20:06
 */
public class UseMinSubArraySumAim {
    
    public static int minSubArray(int[] arr, int target) {
        int len = arr.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while (right < len) {
            sum += arr[right++];
            if (sum >= target) {
                while (left <= right) {
                    if (sum < target) {
                        break;
                    }
                    ans = Math.min(ans, right - left);
                    sum -= arr[left++];
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1,1,1};
        int target = 7;
        System.out.println(minSubArray(arr, target));
    }
}
