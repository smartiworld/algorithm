package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 11. Container With Most Water
 * Medium
 * 23.7K
 * 1.3K
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Constraints:
 *
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 * @date 2023/4/17 15:56
 */
public class ContainerWithMostWater {
    
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int maxArea = 0;
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(minHeight * (right - left), maxArea);
            // 谁小移动哪边
            while (height[left] <= minHeight && left < right) {
                left++;
            }
            while (height[right] <= minHeight && right > left) {
                right--;
            }
        }
        return maxArea;
    }
    
    public static void main(String[] args) {
        int[] height = {1,1};
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        System.out.println(containerWithMostWater.maxArea(height));
    }
}
