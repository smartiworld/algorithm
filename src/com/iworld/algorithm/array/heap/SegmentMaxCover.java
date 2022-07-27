package com.iworld.algorithm.array.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 线段最大覆盖数量
 * 给定一个二维数组 每个小数组代表当前线段 小数组第一个元素表示线段的其实位置 第二个元素表示线段的结束位置
 * 计算出线段数组 最大重合数量
 * @date 2022/7/27 11:49
 */
public class SegmentMaxCover {

    public int maxCover(int[][] segments) {
        Lin[] lins = new Lin[segments.length];
        for (int i = 0; i < segments.length; i++) {
            lins[i] = new Lin(segments[i][0], segments[i][1]);
        }
        Arrays.sort(lins, new LinStartComparator());
        int max = 0;
        // end 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < segments.length; i++) {
            while (!heap.isEmpty() && lins[i].start >= heap.peek()) {
                heap.poll();
            }
            heap.add(lins[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }
    
    private static class Lin {
        private int start;
        private int end;
        
        public Lin(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    private static class LinStartComparator implements Comparator<Lin> {
        
        @Override
        public int compare(Lin l1, Lin l2) {
            return l1.start - l2.start;
        }
    }
    
    public static void main(String[] args) {
        SegmentMaxCover segmentMaxCover = new SegmentMaxCover();
        int[][] segments = {{1, 6}, {2, 5}, {1, 3}, {4, 9}, {5, 8}};
        System.out.println(segmentMaxCover.maxCover(segments));
    }
}
