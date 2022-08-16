package com.iworld.algorithm.array.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 56. Merge Intervals   Medium
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10 4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10 4
 * https://leetcode.com/problems/merge-intervals/
 * @date 2022/8/15 16:57
 */
public class MergeIntervals {
    
    public int[][] merge(int[][] intervals) {
        List<Range> ranges = new ArrayList<>();
        for (int[] interval : intervals) {
            ranges.add(new Range(interval[0], interval[1]));
        }
        Collections.sort(ranges, new RangeStartComparator());
        int allStart = ranges.get(0).start;
        int allEnd = ranges.get(0).end;
        List<Range> result = new ArrayList<>();
        for (int i = 1; i < ranges.size(); i++) {
            int start = ranges.get(i).start;
            if (start > allEnd) {
                Range addRange = new Range(allStart, allEnd);
                allStart = start;
                result.add(addRange);
            }
            allEnd = Math.max(allEnd, ranges.get(i).end);
        }
        Range addRange = new Range(allStart, allEnd);
        result.add(addRange);
        int[][] ans = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            ans[i][0] = result.get(i).start;
            ans[i][1] = result.get(i).end;
        }
        return ans;
    }
    
    static class RangeStartComparator implements Comparator<Range> {
        
        @Override
        public int compare(Range r1, Range r2) {
            return r1.start - r2.start;
        }
    }
    
    static class Range {
        int start;
        int end;
        
        Range(int s, int e) {
            start = s;
            end = e;
        }
    }
    
    public static void main(String[] args) {
        //int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = {{1,4},{4,5}};
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] ans = mergeIntervals.merge(intervals);
        System.out.print("[");
        for (int[] interval : ans) {
            System.out.print("[" + interval[0] + "," + interval[1] + "]" + ",");
        }
        System.out.print("]");
    }
}
