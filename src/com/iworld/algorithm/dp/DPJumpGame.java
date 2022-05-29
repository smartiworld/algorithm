package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 45.跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 *
 * 链接：https://leetcode.cn/problems/jump-game-ii
 * @date 2022/5/29 15:45
 */
public class DPJumpGame {
    
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || (nums.length > 1 && nums[0] == 0)) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        return process(nums, 0);
    }
    
    private int process(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return 0;
        }
        if (nums[index] + index >= nums.length - 1) {
            return 1;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            int tmp =  process(nums, index + i);
            result = Math.min(tmp >= Integer.MAX_VALUE ? tmp : tmp + 1, result);
        }
        return result;
    }
    
    public int jump2(int[] nums) {
        if (nums == null || nums.length == 0 || (nums.length > 1 && nums[0] == 0)) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        return process2(nums, 0, 0);
    }
    
    private int process2(int[] nums, int index, int step) {
        if (index >= nums.length - 1) {
            return step;
        }
        if (nums[index] + index >= nums.length - 1) {
            return step + 1;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            int tmp =  process2(nums, index + i, step + 1);
            result = Math.min(tmp >= Integer.MAX_VALUE ? tmp : tmp + 1, result);
        }
        return result;
    }
    
    public int jumpDp(int[] nums) {
        if (nums == null || nums.length == 0 || (nums.length > 1 && nums[0] == 0)) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[nums.length - 2] = nums[nums.length - 2] >= 1 ? 1 : 2;
        for (int i = nums.length - 3; i >= 0; i--) {
            if (nums[i] + i >= nums.length - 1) {
               dp[i] = 1;
               continue;
            }
            int min = nums.length;
            for (int j = 1; j <= nums[i]; j++) {
                int tmp =  dp[i + j];
                if (tmp == 1) {
                    min = tmp;
                    break;
                }
                min = Math.min(tmp, min);
            }
            dp[i] = min + 1;
        }
        return dp[0];
    }
    
    /**
     * 贪心 在有限步数内 尽量走最大步数
     * @param nums
     * @return
     */
    public int jump3(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        return process(nums,0, 0);
    }
    
    /**
     * 0~index-1 位置已经处理  最合适步数为step
     * @param nums   步数数组
     * @param index 当前来到的数组位置
     * @param step   前面走了多少步
     * @return
     */
    public int process(int[] nums, int index, int step){
        if(nums[index] + index >= nums.length - 1){
            return step + 1;
        }
        int maxStep = 0;
        int curStep = 0;
        // 跳跃一步加跳跃到位置 一直找出到跳跃最大步数 可以走的最大步数
        // 当前位置走1~nums[index]步数
        for(int j = 1; j <= nums[index]; j++){
            // 走了j步后index+j位置可以走的最大步数
            int tempStep = j + nums[index + j];
            if(tempStep > maxStep){
                maxStep = tempStep;
                curStep = j;
            }
        }
        // 跳到最大步数位置
        index += curStep;
        step++;
        return process(nums, index, step);
    }
    
    public static void main(String[] args) {
        int[] nums = {5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1};
        DPJumpGame jumpGame = new DPJumpGame();
        System.out.println(jumpGame.jump(nums));
        System.out.println(jumpGame.jumpDp(nums));
        System.out.println(jumpGame.jump3(nums));
    }
}
