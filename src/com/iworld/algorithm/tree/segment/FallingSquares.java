package com.iworld.algorithm.tree.segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * @author gq.cai
 * @version 1.0
 * @description 699 Falling Squares   Hard
 * There are several squares being dropped onto the X-axis of a 2D plane.
 *
 * You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi] represents the ith
 * square with a side length of sideLengthi that is dropped with its left edge aligned with X-coordinate lefti.
 *
 * Each square is dropped one at a time from a height above any landed squares.
 * It then falls downward (negative Y direction) until it either lands on the top side of another square or on the X-axis.
 * A square brushing the left/right side of another square does not count as landing on it.
 * Once it lands, it freezes in place and cannot be moved.
 *
 * After each square is dropped, you must record the height of the current tallest stack of squares.
 *
 * Return an integer array ans where ans[i] represents the height described above after dropping the ith square.
 * Example 1:
 *
 *
 * Input: positions = [[1,2],[2,3],[6,1]]
 * Output: [2,5,5]
 * Explanation:
 * After the first drop, the tallest stack is square 1 with a height of 2.
 * After the second drop, the tallest stack is squares 1 and 2 with a height of 5.
 * After the third drop, the tallest stack is still squares 1 and 2 with a height of 5.
 * Thus, we return an answer of [2, 5, 5].
 * Example 2:
 *
 * Input: positions = [[100,100],[200,100]]
 * Output: [100,100]
 * Explanation:
 * After the first drop, the tallest stack is square 1 with a height of 100.
 * After the second drop, the tallest stack is either square 1 or square 2, both with heights of 100.
 * Thus, we return an answer of [100, 100].
 * Note that square 2 only brushes the right side of square 1, which does not count as landing on it.
 *
 * Constraints:
 *
 * 1 <= positions.length <= 1000
 * 1 <= lefti <= 108
 * 1 <= sideLengthi <= 106
 * https://leetcode.com/problems/falling-squares/
 * @date 2022/7/26 11:06
 */
public class FallingSquares {
    
    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> indexMap = indexMap(positions);
        int size = indexMap.size();
        List<Integer> ans = new ArrayList<>();
        MaxSegmentTree1 segmentTree = new MaxSegmentTree1(size);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < positions.length; i++) {
            int left = indexMap.get(positions[i][0]);
            int right = indexMap.get(positions[i][0] + positions[i][1] - 1);
            int curMax = segmentTree.query(left, right, 1, size, 1) + positions[i][1];
            max = Math.max(max, curMax);
            ans.add(max);
            segmentTree.update(left, right, 1, size, curMax, 1);
        }
        return ans;
    }
    
    /**
     * 空间压缩
     * @param positions
     * @return
     */
    private HashMap<Integer, Integer> indexMap(int[][] positions) {
        TreeSet<Integer> orderIndex = new TreeSet<>();
        for (int i = 0; i < positions.length; i++) {
            orderIndex.add(positions[i][0]);
            orderIndex.add(positions[i][0] + positions[i][1] - 1);
        }
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int count = 1;
        for (Integer index : orderIndex) {
            indexMap.put(index, count++);
        }
        return indexMap;
    }
    
    public static void main(String[] args) {
        int[][] positions = {{1,2},{2,3},{6,1}};
        FallingSquares fallingSquares = new FallingSquares();
        System.out.println(fallingSquares.fallingSquares(positions));
    }
    
    public class MaxSegmentTree1 {
        
        private int[] max;
        private int[] change;
        private boolean[] update;
        
        public MaxSegmentTree1(int size) {
            int n = size + 1;
            max = new int[n << 2];
            change = new int[n << 2];
            update = new boolean[n << 2];
        }
        
        public void update(int left, int right, int l, int r, int value, int index) {
            if (left <= l && r <= right) {
                change[index] = value;
                update[index] = true;
                max[index] = value;
                return ;
            }
            pushValueDown(index);
            int mid = l + ((r - l) >> 1);
            if (left <= mid) {
                update(left, right, l, mid, value, index << 1);
            }
            if (mid < right) {
                update(left, right, mid + 1, r, value, index << 1 | 1);
            }
            pushMaxParent(index);
        }
        
        public int query(int left, int right, int l, int r, int index) {
            if (left <= l && r <= right) {
                return max[index];
            }
            int mid = l + ((r - l) >> 1);
            int leftAns = 0;
            int rightAns = 0;
            pushValueDown(index);
            if (left <= mid) {
                leftAns = query(left, right, l, mid, index << 1);
            }
            if (mid < right) {
                rightAns = query(left, right, mid + 1, r, index << 1 | 1);
            }
            return Math.max(leftAns, rightAns);
        }
        
        private void pushValueDown(int parent) {
            if (update[parent]) {
                update[parent << 1] = true;
                update[parent << 1 | 1] = true;
                change[parent << 1] = change[parent];
                change[parent << 1 | 1] = change[parent];
                max[parent << 1] = change[parent];
                max[parent << 1 | 1] = change[parent];
                update[parent] = false;
            }
        }
        
        private void pushMaxParent(int index) {
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }
    }
    
}
