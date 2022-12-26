package com.iworld.algorithm.array.lr;

/**
 * @author gq.cai
 * @version 1.0
 * @description 42. Trapping Rain Water
 * Hard
 * 24.3K
 * 338
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 * https://leetcode.com/problems/trapping-rain-water/
 * @date 2022/12/26 11:16
 */
public class TrappingRainWater {
    
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 1;
        int right = height.length - 2;
        int sum = 0;
        while (left <= right) {
            // 哪边小计算哪一边
            if (leftMax < rightMax) {
                // 计算当前位置最大盛水 利用数字单调性
                if (leftMax > height[left]) {
                    // 只有当前位置小于左边最大值时会可以盛水
                    sum += leftMax - height[left];
                } else {
                    // 只有在当前位置大于等于左边最大值的时候才会更新左边最大值 但此时不回盛水水
                    leftMax = height[left];
                }
                left ++;
            } else {
                // 计算当前位置最大盛水 利用数字单调性
                if (rightMax > height[right]) {
                    sum += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right --;
            }
        }
        return sum;
    }
}
