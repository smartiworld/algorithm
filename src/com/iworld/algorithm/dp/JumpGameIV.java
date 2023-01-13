package com.iworld.algorithm.dp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 1345. Jump Game IV
 * Hard
 * 2.1K
 * 83
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 *
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9.
 * Note that index 9 is the last index of the array.
 * Example 2:
 *
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You do not need to jump.
 * Example 3:
 *
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 * https://leetcode.com/problems/jump-game-iv/
 * @date 2022/12/1 14:34
 */
public class JumpGameIV {
    
    public int minJumps(int[] arr) {
        // value_ index
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> indexs = valueIndexMap.get(arr[i]);
            if (indexs == null) {
                indexs = new ArrayList<>();
            }
            indexs.add(i);
            valueIndexMap.put(arr[i], indexs);
        }
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int ans = process(arr, 0, valueIndexMap, set);
        return ans;
    }
    
    /**
     *
     * @param arr
     * @param index           当前来到的位置
     * @param valueIndexMap   value indexs map
     * @param set             走过的路径
     * @return
     */
    private int process(int[] arr, int index, Map<Integer, List<Integer>> valueIndexMap, Set<Integer> set) {
        if (index == arr.length - 1) {
            return 0;
        }
        int ans = arr.length - 1;
        int preIndex = index - 1;
        if (preIndex >= 0 && !set.contains(preIndex)) {
            set.add(preIndex);
            ans = process(arr, preIndex, valueIndexMap, set);
            set.remove(preIndex);
        }
        int nextIndex = index + 1;
        if (nextIndex < arr.length && !set.contains(nextIndex)) {
            set.add(nextIndex);
            ans = Math.min(ans, process(arr, nextIndex, valueIndexMap, set));
            set.remove(nextIndex);
        }
        for (Integer randIndex : valueIndexMap.get(arr[index])) {
            if (!set.contains(randIndex)) {
                set.add(randIndex);
                ans = Math.min(ans, process(arr, randIndex, valueIndexMap, set));
                set.remove(randIndex);
            }
        }
        return ans == arr.length - 1 ? ans : ans + 1;
    }
    
    public int minJumpsBfs(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        // value_ index
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> indexs = valueIndexMap.get(arr[i]);
            if (indexs == null) {
                indexs = new ArrayList<>();
            }
            indexs.add(i);
            valueIndexMap.put(arr[i], indexs);
        }
        Queue<Integer> indexQueue = new LinkedList<>();
        Map<Integer, Integer> indexLevel = new HashMap<>();
        indexQueue.offer(0);
        indexLevel.put(0, 0);
        while (!indexQueue.isEmpty()) {
            Integer index = indexQueue.poll();
            Integer level = indexLevel.get(index);
            if (index == n - 1) {
                return level;
            }
            int nextLevel = level + 1;
            int preIndex = index - 1;
            if (preIndex >= 0 && !indexLevel.containsKey(preIndex)) {
                indexLevel.put(preIndex, nextLevel);
                indexQueue.offer(preIndex);
            }
            int nextIndex = index + 1;
            if (nextIndex < n && !indexLevel.containsKey(nextIndex)) {
                indexLevel.put(nextIndex, nextLevel);
                indexQueue.offer(nextIndex);
            }
            List<Integer> randIndex = valueIndexMap.get(arr[index]);
            if (randIndex != null) {
                for (int i = 0; i < randIndex.size(); i++) {
                    int rand = randIndex.get(i);
                    if (!indexLevel.containsKey(rand)) {
                        indexLevel.put(rand, nextLevel);
                        indexQueue.offer(rand);
                    }
                }
            }
        }
        return -1;
    }
    
    public int minJumpsBfsOpt(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        // value_ index
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> indexs = valueIndexMap.get(arr[i]);
            if (indexs == null) {
                indexs = new ArrayList<>();
            }
            int j = i;
            // 连续相同值 只记录头和尾的位置
            while (j + 1 < n && arr[j + 1] == arr[i]) {
                j++;
            }
            indexs.add(i);
            if (j != i) {
                indexs.add(j);
            }
            i = j;
            valueIndexMap.put(arr[i], indexs);
        }
        Queue<Integer> indexQueue = new LinkedList<>();
        Set<Integer> indexLevel = new HashSet<>();
        indexQueue.offer(0);
        indexLevel.add(0);
        int step = 0;
        while (!indexQueue.isEmpty()) {
            int size = indexQueue.size();
            while (size > 0) {
                Integer index = indexQueue.poll();
                if (index == n - 1) {
                    return step;
                }
                int preIndex = index - 1;
                if (preIndex >= 0 && !indexLevel.contains(preIndex)) {
                    indexLevel.add(preIndex);
                    indexQueue.offer(preIndex);
                }
                int nextIndex = index + 1;
                if (nextIndex < n && !indexLevel.contains(nextIndex)) {
                    indexLevel.add(nextIndex);
                    indexQueue.offer(nextIndex);
                }
                for (int randIndex : valueIndexMap.get(arr[index])) {
                    if (!indexLevel.contains(randIndex)) {
                        indexLevel.add(randIndex);
                        indexQueue.offer(randIndex);
                    }
                }
                size--;
            }
            step++;
        }
        return -1;
    }
    
    public int minJumpsBfsOpt2(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }
        List<Integer> curs = new LinkedList<>();
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        int step = 0;
        while (!curs.isEmpty()) {
            List<Integer> nex = new LinkedList<>();
            for (int node : curs) {
                if (node == n - 1) {
                    return step;
                }
                for (int child : graph.get(arr[node])) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }
                // clear the list to prevent redundant search
                graph.get(arr[node]).clear();
                // check neighbors
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nex.add(node - 1);
                }
            }
            curs = nex;
            step++;
        }
        return -1;
    }
    
    public int minJumpsDpBest(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return 0;
        }
        if (arr[0] == arr[len - 1] || len == 2) {
            return 1;
        }
        int[] dp = new int[len];
        // len - 1一定可以到达最后
        Arrays.fill(dp, len - 1);
        dp[len - 1] = 0;
        Map<Integer, List<Integer>> valToIndices = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int val = arr[i];
            valToIndices.putIfAbsent(val, new ArrayList<>());
            int j = i;
            // 连续相同值 只记录头和尾的位置
            while (j + 1 < len && arr[j + 1] == val) {
                j++;
            }
            valToIndices.get(val).add(i);
            if (j != i) {
                valToIndices.get(val).add(j);
            }
            i = j;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(len - 1);
        while (!queue.isEmpty()) {
            int idx = queue.removeFirst();
            if (idx - 1 >= 0) {
                // dp[idx] + 1当前位置走一步到最后位置小于于dp[idx - 1]前面元素到结尾步数
                if (dp[idx] + 1 < dp[idx - 1]) {
                    dp[idx - 1] = dp[idx] + 1;
                    queue.addLast(idx - 1);
                    if (idx - 1 == 0) {
                        return dp[0];
                    }
                }
            }
            if (idx + 1 < len) {
                // 当前位置dp[idx] + 1加一步可以来到下元素位置  如果小于后面 更新后面步数
                if (dp[idx] + 1 < dp[idx + 1]) {
                    dp[idx + 1] = dp[idx] + 1;
                    queue.addLast(idx + 1);
                }
            }
            if (valToIndices.containsKey(arr[idx])) {
                for (int i : valToIndices.get(arr[idx])) {
                    if (dp[idx] + 1 < dp[i]) {
                        dp[i] = dp[idx] + 1;
                        if (i == 0) {
                            return dp[0];
                        }
                        queue.addLast(i);
                    }
                }
                valToIndices.remove(arr[idx]);
            }
        }
        return dp[0];
    }
    
    public static void main(String[] args) {
        int[] arr = {11,22,7,7,7,7,7,7,7,22,13};
        JumpGameIV jumpGameIV = new JumpGameIV();
        System.out.println(jumpGameIV.minJumps(arr));
        System.out.println(jumpGameIV.minJumpsBfs(arr));
        System.out.println(jumpGameIV.minJumpsBfsOpt(arr));
        System.out.println(jumpGameIV.minJumpsDpBest(arr));
    }
}
