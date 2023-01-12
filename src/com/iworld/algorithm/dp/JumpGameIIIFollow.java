package com.iworld.algorithm.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 在数组nums上给定一个start位置和一个end位置
 * 来到i的位置的时候可以向做跳i-nums[i]，向右跳i+nums[i]位置
 * 返回从start跳到end最小步数 跳不到返回-1
 * @date 2022/11/29 21:50
 */
public class JumpGameIIIFollow {
    
    public int jump(int[] nums, int start, int end) {
        Set<Integer> set = new HashSet<>();
        set.add(start);
        int ans = process(nums, end, start, 0, set);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int process(int[] nums, int end, int i, int step, Set<Integer> set) {
        if (i == end) {
            return step;
        }
        if (i + nums[i] == end || i - nums[i] == end) {
            return step + 1;
        }
        int min = -1;
        int nextIndex = i + nums[i];
        if (nextIndex < nums.length && !set.contains(nextIndex)) {
            set.add(nextIndex);
            min = process(nums, end, nextIndex, step + 1, set);
            set.remove(nextIndex);
        }
        int preIndex = i - nums[i];
        if (preIndex >= 0 && !set.contains(preIndex)) {
            set.add(preIndex);
            int tmp = process(nums, end, preIndex, step + 1, set);
            if (min == -1) {
                min = tmp;
            } else {
                min = Math.min(min, tmp);
            }
            set.remove(preIndex);
        }
        return min;
    }
    
    public int jump2(int[] nums, int start, int end) {
        Set<Integer> set = new HashSet<>();
        set.add(start);
        int ans = process2(nums, end, start, set);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int process2(int[] nums, int end, int i, Set<Integer> set) {
        if (i == end) {
            return 0;
        }
        int nextIndex = i + nums[i];
        int preIndex = i - nums[i];
        if (nextIndex == end || preIndex == end) {
            return 1;
        }
        int min = -1;
        if (nextIndex < nums.length && !set.contains(nextIndex)) {
            set.add(nextIndex);
            min = process2(nums, end, nextIndex, set);
            set.remove(nextIndex);
        }
        if (preIndex >= 0 && !set.contains(preIndex)) {
            set.add(preIndex);
            int tmp = process2(nums, end, preIndex, set);
            if (min == -1) {
                min = tmp;
            } else {
                min = Math.min(min, tmp);
            }
            set.remove(preIndex);
        }
        if (min != -1) {
            min++;
        }
        return min;
    }
    
    public int jumpOpt(int[] nums, int start, int end) {
        if (start == end) {
            return 0;
        }
        Queue<Integer> indexQueue = new LinkedList<Integer>();
        Map<Integer, Integer> indexLevel = new HashMap<>();
        indexQueue.offer(start);
        indexLevel.put(start, 0);
        
        while (!indexQueue.isEmpty()) {
            Integer index = indexQueue.poll();
            Integer level = indexLevel.get(index);
            int nextLevel = level + 1;
            int nextIndex = index + nums[index];
            int preIndex = index - nums[index];
            if (nextIndex == end || preIndex == end) {
                return nextLevel;
            }
            if (nextIndex < nums.length && !indexLevel.containsKey(nextIndex)) {
                indexQueue.offer(nextIndex);
                indexLevel.put(nextIndex, nextLevel);
            }
            if (preIndex >= 0 && !indexLevel.containsKey(preIndex)) {
                indexQueue.offer(preIndex);
                indexLevel.put(preIndex, nextLevel);
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        JumpGameIIIFollow jumpGameIIIFollow = new JumpGameIIIFollow();
        int[] nums = {4,2,3,0,3,1,2};
        int start = 5;
        int end = 3;
        System.out.println(jumpGameIIIFollow.jump(nums, start, end));
        System.out.println(jumpGameIIIFollow.jump2(nums, start, end));
        System.out.println(jumpGameIIIFollow.jumpOpt(nums, start, end));
    }
}
