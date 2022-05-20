package com.iworld.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 436.寻找右区间
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 *
 * 示例 1：
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * 示例 2：
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1,0,1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * 示例 3：
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1,2,-1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 *
 * 提示：
 *
 * 1 <= intervals.length <= 2 * 104
 * intervals[i].length == 2
 * -106 <= starti <= endi <= 106
 * 每个间隔的起点都 不相同
 *
 * 链接：https://leetcode.cn/problems/find-right-interval
 * @date 2022/5/20 17:25
 */
public class FindRightInterval {
    
    /**
     * 暴力解
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int k = -1;
            int minJ = Integer.MAX_VALUE;
            for (int j = 0; j < intervals.length; j++) {
                int[] intervalI = intervals[i];
                int[] intervalJ = intervals[j];
                if (intervalJ[0] == intervalI[1]) {
                    k = j;
                    break;
                } else if (intervalJ[0] > intervalI[1]) {
                    if (minJ > intervalJ[0]) {
                        k = j;
                        minJ = intervalJ[0];
                    }
                }
            }
            result[i] = k;
        }
        return result;
    }
    
    public int[] findRightIntervalSort(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[]{-1};
        }
        if (intervals.length == 1) {
            return intervals[0][1] >= intervals[0][0] ? new int[0] : new int[]{-1};
        }
        int[] result = new int[intervals.length];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            indexMap.put(intervals[i][0], i);
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < intervals.length; i++) {
            int k = -1;
            for (int j = i; j < intervals.length; j++) {
                int[] intervalI = intervals[i];
                int[] intervalJ = intervals[j];
                if (intervalJ[0] >= intervalI[1]) {
                    k = indexMap.get(intervalJ[0]);
                    break;
                }
            }
            result[indexMap.get(intervals[i][0])] = k;
        }
        return result;
    }
    
    public int[] findRightIntervalSortBinarySearch(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[]{-1};
        }
        if (intervals.length == 1) {
            return intervals[0][1] == intervals[0][0] ? new int[]{0} : new int[]{-1};
        }
        int[] result = new int[intervals.length];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            indexMap.put(intervals[i][0], i);
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < intervals.length; i++) {
            int k = -1;
            int l = i;
            int r = intervals.length - 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (intervals[m][0] >= intervals[i][1]) {
                    k = indexMap.get(intervals[m][0]);
                    if (intervals[m][0] == intervals[i][1]) {
                        break;
                    }
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            result[indexMap.get(intervals[i][0])] = k;
        }
        return result;
    }
    
    public static void main(String[] args) {
        //
        int[][] intervals = {{1,12},{2,9},{3,10},{13,14},{15,16},{16,17}};
        FindRightInterval findRightInterval = new FindRightInterval();
        int[] rightInterval = findRightInterval.findRightInterval(intervals);
        for (int j : rightInterval) {
            System.out.print(j + ",");
        }
        System.out.println();
        int[] rightIntervalSort = findRightInterval.findRightIntervalSortBinarySearch(intervals);
        for (int j : rightIntervalSort) {
            System.out.print(j + ",");
        }
    }
}
