package com.iworld.algorithm.dp.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 546. Remove Boxes
 * You are given several boxes with different colors represented by different positive numbers.
 *
 * You may experience several rounds to remove boxes until there is no box left.
 * Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 *
 * Return the maximum points you can get.
 *
 * Example 1:
 *
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Example 2:
 *
 * Input: boxes = [1,1,1]
 * Output: 9
 * Example 3:
 *
 * Input: boxes = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 * https://leetcode.com/problems/remove-boxes/
 * @date 2023/6/3 16:46
 */
public class RemoveBoxes {
    
    public int removeBoxes(int[] boxes) {
        return process(boxes, 0, boxes.length - 1, 0);
    }
    
    /**
     * 范围尝试 解决l~r范围 可以算出最大值
     * @param boxes
     * @param l      边界左边位置
     * @param r      边界右边位置
     * @param k      和当前i前面连续多少个相同
     * @return
     */
    private int process(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        // 计算从l开始有多少个相同连续数字
        int cur = l;
        while(cur + 1 <= r && boxes[cur + 1] == boxes[l]) {
            cur++;
        }
        int count = cur - l + 1 + k;
        // 当前连续结算 清空连续k=0 l~cur连续已经结算 接下来来到cur+1~r的区间
        int ans = count * count + process(boxes, cur + 1, r, 0);
        // 前面连续count不结算 尝试连接后面有相同部分 此时cur + 1位置肯定不连续
        // 在cur+2~r找出和l连续位置
        for (int i = cur + 2; i <= r; i++) {
            // 当前位置和l位置连续 并且前一个位置不和l位置连续
            if (boxes[i] == boxes[l] && boxes[l] != boxes[i - 1]) {
                // 计算中不连续范围process(boxes, cur+1, i-1, 0)  计算连续区间process(boxes, i, r, count) 两个区间相加
                ans = Math.max(ans, process(boxes, cur + 1, i - 1, 0) + process(boxes, i, r, count));
            }
        }
        return ans;
    }
    
    public int removeBoxesOpt(int[] boxes) {
        int row = boxes.length;
        int[][][] dp = new int[row][row][row + 1];
        return process(boxes, 0, row - 1, 0, dp);
    }
    
    /**
     * 范围尝试 解决l~r范围 可以算出最大值
     * @param boxes
     * @param l      边界左边位置
     * @param r      边界右边位置
     * @param k      和当前i前面连续多少个相同
     * @return
     */
    private int process(int[] boxes, int l, int r, int k, int[][][] dp) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] > 0) {
            return dp[l][r][k];
        }
        // 计算从l开始有多少个相同连续数字
        int cur = l;
        while(cur + 1 <= r && boxes[cur + 1] == boxes[l]) {
            cur++;
        }
        int count = cur - l + 1 + k;
        // 当前连续结算 清空连续k=0 l~cur连续已经结算 接下来来到cur+1~r的区间
        int ans = count * count + process(boxes, cur + 1, r, 0, dp);
        // 前面连续count不结算 尝试连接后面有相同部分 此时cur + 1位置肯定不连续
        // 在cur+2~r找出和l连续位置
        for (int i = cur + 2; i <= r; i++) {
            // 当前位置和l位置连续 并且前一个位置不和l位置连续
            if (boxes[i] == boxes[l] && boxes[l] != boxes[i - 1]) {
                // 计算中不连续范围process(boxes, cur+1, i-1, 0)  计算连续区间process(boxes, i, r, count) 两个区间相加
                ans = Math.max(ans, process(boxes, cur + 1, i - 1, 0, dp) + process(boxes, i, r, count, dp));
            }
        }
        dp[l][r][k] = ans;
        return ans;
    }
    
    public static void main(String[] args) {
        int[] boxes = {1,3,2,2,2,3,4,3,1};
        RemoveBoxes removeBoxes = new RemoveBoxes();
        System.out.println(removeBoxes.removeBoxes(boxes));
        System.out.println(removeBoxes.removeBoxesOpt(boxes));
    }
    
}
