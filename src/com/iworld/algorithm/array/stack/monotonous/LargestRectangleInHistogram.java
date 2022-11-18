package com.iworld.algorithm.array.stack.monotonous;

import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description  84.Largest Rectangle in Histogram   Hard
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * Example 1:
 *
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 *
 *
 * Input: heights = [2,4]
 * Output: 4
 * Constraints:
 *
 * 1 <= heights.length <= 10 5
 * 0 <= heights[i] <= 10 4
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * @date 2022/8/18 15:20
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 1) {
            return heights[0];
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int pop = stack.pop();
                int width = i - 1 - (stack.isEmpty() ? -1 : stack.peek());
                max = Math.max(max, width * heights[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int width = len - 1 - (stack.isEmpty() ? -1 : stack.peek());
            max = Math.max(max, width * heights[pop]);
        }
        return max;
    }
}
