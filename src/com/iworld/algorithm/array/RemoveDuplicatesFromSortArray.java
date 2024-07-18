package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 26. Remove Duplicates from Sorted Array       Easy
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * <p>
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * <p>
 * int k = removeDuplicates(nums); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * @date 2022/6/30 14:45
 */
public class RemoveDuplicatesSortArray {
    
    /**
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        // 数组只有一个元素  或者元素都相同
        if (nums[0] == nums[nums.length - 1]) {
            return 1;
        }
        // 开始比较的位置
        int i = 1;
        // 新数组下标
        int k = 1;
        // 对比元素
        int compare = nums[0];
        while (i < nums.length) {
            // 每次对比不同时 记录元素并且将不同元素放入新下标位置
            if (nums[i] != compare) {
                nums[k++] = nums[i];
                compare = nums[i];
            }
            i++;
            
        }
        return k;
    }
    
    /**
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        // 新形成数组要存放的下标
        // p-1需要对比的数
        int p = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[p - 1]) {
                nums[p++] = nums[i];
            }
        }
        return p;
    }
}
