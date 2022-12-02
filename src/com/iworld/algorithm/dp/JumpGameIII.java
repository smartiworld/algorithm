package com.iworld.algorithm.dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 1306. Jump Game III
 * Medium
 * 3.3K
 * 80
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i],
 * check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 104
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 * https://leetcode.com/problems/jump-game-iii/
 * @date 2022/11/30 21:28
 */
public class JumpGameIII {
    
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        Set<Integer> indexSet = new HashSet<>();
        indexSet.add(start);
        return process(arr, start, indexSet);
    }
    
    private boolean process(int[] arr, int index, Set<Integer> indexSet) {
        if (arr[index] == 0) {
            return true;
        }
        boolean ans = false;
        int nextIndex = index + arr[index];
        if (nextIndex < arr.length && !indexSet.contains(nextIndex)) {
            indexSet.add(nextIndex);
            ans = process(arr, nextIndex, indexSet);
        }
        int preIndex = index - arr[index];
        if (preIndex >= 0 && !indexSet.contains(preIndex)) {
            indexSet.add(preIndex);
            ans = ans || process(arr, preIndex, indexSet);
        }
        return ans;
    }
    
    public boolean canReachBfs(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        Queue<Integer> indexQueue = new LinkedList<>();
        // 记录走的路径index
        Set<Integer> indexSet = new HashSet<>();
        indexQueue.add(start);
        indexSet.add(start);
        while (!indexQueue.isEmpty()) {
            Integer index = indexQueue.poll();
            int nextIndex = index + arr[index];
            if (nextIndex < arr.length && arr[nextIndex] == 0) {
                return true;
            }
            
            int preIndex = index - arr[index];
            if (preIndex >= 0 && arr[preIndex] == 0) {
                return true;
            }
            if (nextIndex < arr.length && !indexSet.contains(nextIndex)) {
                indexSet.add(nextIndex);
                indexQueue.offer(nextIndex);
            }
            if (preIndex >= 0 && !indexSet.contains(preIndex)) {
                indexSet.add(preIndex);
                indexQueue.offer(preIndex);
            }
        }
        return false;
    }
    
    public boolean canReachBfs2(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        Queue<Integer> indexQueue = new LinkedList<>();
        // 记录走的路径index
        Set<Integer> indexSet = new HashSet<>();
        indexQueue.add(start);
        indexSet.add(start);
        while (!indexQueue.isEmpty()) {
            Integer index = indexQueue.poll();
            if (arr[index] == 0) {
                return true;
            }
            int preIndex = index - arr[index];
            if (preIndex > 0 && !indexSet.contains(preIndex)) {
                indexSet.add(preIndex);
                indexQueue.offer(preIndex);
            }
            int nextIndex = index + arr[index];
            if (nextIndex < arr.length && !indexSet.contains(nextIndex)) {
                indexSet.add(nextIndex);
                indexQueue.offer(nextIndex);
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] arr = {3,0,2,1,2};
        JumpGameIII jumpGameIII = new JumpGameIII();
        int start = 2;
        System.out.println(jumpGameIII.canReach(arr, start));
    }
}
