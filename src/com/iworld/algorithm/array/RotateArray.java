package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 189. Rotate Array
 * Medium
 * 10980
 * 1394
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 105
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * https://leetcode.com/problems/rotate-array/
 * @date 2022/9/1 17:11
 */
public class RotateArray {
    
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        // 解决k>length
        k = k % length;
        revers(nums, 0, length - k - 1);
        revers(nums, length - k, length - 1);
        revers(nums, 0, length - 1);
    }
    
    private void revers(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = tmp;
        }
    }
    
    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (k == 0) {
            return;
        }
        int l = 0;
        int r = length - 1;
        int lpart = length - k;
        int rpart = k;
        int same = Math.min(lpart, rpart);
        int diff = lpart - rpart;
        exchange(nums, l, r, same);
        while (diff != 0) {
            if (diff > 0) {
                l += same;
                lpart = diff;
            } else {
                r -= same;
                rpart = -diff;
            }
            same = Math.min(lpart, rpart);
            diff = lpart - rpart;
            exchange(nums, l, r, same);
        }
    }
    
    public void exchange(int[] nums, int start, int end, int size) {
        int i = end - size + 1;
        while (size-- != 0) {
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
            start++;
            i++;
        }
    }
    
    public void rotateTime(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            int end = nums[n - 1];
            for (int j = n - 2;j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = end;
        }
    }
    
    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int[] nums = {-1};
        int k = 2;
        rotateArray.rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }
}
