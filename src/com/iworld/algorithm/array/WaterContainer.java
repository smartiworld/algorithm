package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 *
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * @date 2022/5/28 17:24
 */
public class WaterContainer {
    
    /**
     * 决定左右边界 缩小长度 长度变小的前提下 需要找到大于前高度的位置
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int r = height.length - 1;
        int maxArea = 0;
        int l = 0;
        while (l < r) {
            // 当前计算面积的最小高度
            int minHeight = Math.min(height[l], height[r]);
            int area = minHeight * (r - l);
            maxArea = Math.max(maxArea, area);
            // 如果左边界小于当前最小高度 左边界移动
            while (height[l] <= minHeight && l < r) {
                l ++;
            }
            while (minHeight >= height[r] && l < r) {
                r --;
            }
        }
        return maxArea;
    }
    
    public static void main(String[] args) {
        int[] height = {2,3,4,5,18,17,6};
        WaterContainer waterContainer = new WaterContainer();
        System.out.println(waterContainer.maxArea(height));
    }
}
