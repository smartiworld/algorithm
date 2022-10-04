package com.iworld.algorithm.slidewindow;

import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 239. Sliding Window Maximum
 * Hard
 * 12311
 * 396
 * You are given an array of integers nums,
 * there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * https://leetcode.com/problems/sliding-window-maximum/
 * @date 2022/10/4 17:00
 */
public class SlidingWindowMaximum {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            // 如果放入下标值大于队尾值 则从队尾弹出  保证队列头部放的是最新的最大值
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // 队尾放小于队尾下标
            queue.addLast(i);
            if (i - queue.peekFirst() >= k) {
                queue.pollFirst();
            }
            if (i - k + 1 >= 0) {
                ans[index++] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] ans = slidingWindowMaximum.maxSlidingWindow(nums, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
