package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 108. Convert Sorted Array to Binary Search Tree   Easy
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the
 * two subtrees of every node never differs by more than one.
 *
 * Example 1:
 *
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 *
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10 4
 * -10 4 <= nums[i] <= 10 4
 * nums is sorted in a strictly increasing order.
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * @date 2022/8/22 13:25
 */
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }
    
    private TreeNode process(int[] nums, int s, int e) {
        if (s > e) {
            return null;
        }
        int mid = s + ((e - s) >> 1);
        TreeNode head = new TreeNode(nums[mid]);
        if (s == e) {
            return head;
        }
        head.left = process(nums, s, mid - 1);
        head.right = process(nums, mid + 1, e);
        return head;
    }
}
