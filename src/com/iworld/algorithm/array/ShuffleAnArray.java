package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 384. Shuffle an Array
 * Medium
 * 977
 * 774
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 *
 * Example 1:
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 *                        // Any permutation of [1,2,3] must be equally likely to be returned.
 *                        // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50
 * -10^6 <= nums[i] <= 10^6
 * All the elements of nums are unique.
 * At most 104 calls in total will be made to reset and shuffle.
 * https://leetcode.com/problems/shuffle-an-array/
 * @date 2022/10/19 18:38
 */
public class ShuffleAnArray {
    
    class Solution {
        private int[] origin;
        private int[] shuffle;
        public Solution(int[] nums) {
            origin = nums;
            shuffle = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                shuffle[i] = nums[i];
            }
        }
        
        public int[] reset() {
            return origin;
        }
        
        public int[] shuffle() {
            for (int i = shuffle.length - 1; i >= 0; i--) {
                int randomIndex = (int) (Math.random() * (i + 1));
                int tmp = shuffle[randomIndex];
                shuffle[randomIndex] = shuffle[i];
                shuffle[i] = tmp;
            }
            return shuffle;
        }
    }
}
