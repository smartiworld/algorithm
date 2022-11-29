package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 55. Jump Game   Medium
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what.
 * Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10 4
 * 0 <= nums[i] <= 10 5
 * https://leetcode.com/problems/jump-game/
 * @date 2022/8/12 17:41
 */
public class JumpGame {
    
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        return process(nums, 0);
    }
    
    private boolean process(int[] nums, int index) {
        if (index >= nums.length - 1 || index + nums[index] >= nums.length - 1) {
            return true;
        }
        for (int i = 1; i <= nums[index]; i++) {
            if (process(nums, index + i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean canJumpDp(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= nums.length) {
                dp[i] = true;
            } else {
                for (int j = 1; j <= nums[i]; j++) {
                    if (dp[i + j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];
    }
    
    public static boolean canJump2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        // 前面可以走的最大位置
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果当前最大距离已经超过最后位置 直接返回
			if (max >= nums.length - 1) {
				return true;
			}
			// 如果前面0~i-1所有最大位置走不到当前位置 就不可能走到最后
            if (i > max) {
                return false;
            }
            // 选择0~i-1和当前位置能走最大距离 选出最大可走距离
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
    
    public static boolean canJump22(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max >= nums.length - 1) {
                return true;
            }
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[] nums = { 5828,
                5827,
                5826,
                5825,
                5824,
                5823,
                5822,
                5821,
                5695,
                5694,
                5693,
                5692,
                5632,
                5631,
                5630,
                5629,
                5628,
                5627,
                5626,
                5625,
                5624,
                5623,
                5622,
                5621,
                5620,
                5619,
                5618,
                5617,
                5616,
                5615,
                5614,
                5613,
                5612,
                5611,
                5610,
                5609,
                5608,
                5607,
                5606,
                5605,
                5148,
                5147,
                5146,
                5145,
                5144,
                5143,
                5142,
                5141,
                3991,
                3990,
                3989,
                3988,
                217,
                216,
                215,
                214,
                213,
                106,
                105,
                104,
                103,
                102,
                101,
                100,
                99,
                98,
                97,
                96,
                95,
                94,
                93,
                92,
                91,
                90,
                89,
                88,
                87,
                86,
                85,
                84,
                83,
                82,
                81,
                80,
                79,
                78,
                77,
                76,
                75,
                74,
                73,
                72,
                71,
                70,
                69,
                68,
                67,
                66,
                65,
                64,
                63,
                62,
                61,
                60,
                59,
                58,
                57,
                56,
                55,
                54,
                53,
                52,
                51,
                50,
                49,
                48,
                47,
                46,
                45,
                44,
                43,
                42,
                41,
                40,
                39,
                38,
                37,
                36,
                35,
                34,
                33,
                32,
                31,
                30,
                29,
                28,
                27,
                26,
                25,
                24,
                23,
                22,
                21,
                20,
                19,
                18,
                17,
                16,
                15,
                14,
                13,
                12,
                11,
                10,
                9,
                8,
                7,
                6,
                5,
                4,
                3,
                2,
                1,
                0,
                0};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(nums));
        System.out.println(jumpGame.canJumpDp(nums));
    }
}
