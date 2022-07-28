package com.iworld.algorithm.array.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author gq.cai
 * @version 1.0
 * @description 一个平面内最多多少个矩形重合
 * 给定一个二维数组 子数组中前前两个点表示一个顶点的x和y坐标  后两个点表示另一个顶点的x和y坐标
 * 返回给定矩形 最多重合数量
 * 1.
 * @date 2022/7/27 17:06
 */
public class RectangleMaxCover {
    /**
     * 将矩形问题 按照底边处理相同底边的矩形 无需处理其他矩形顶低于当前底边的矩形 然后转换成同水平线 线段重叠问题
     * @param rectangles
     * @return
     */
    public int rectangleMaxCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) {
            return 0;
        }
        if (rectangles.length == 1) {
            return 1;
        }
        int max = 0;
        Rectangle[] rectangleArray = convert(rectangles);
        // 将矩形先按照底升序排序
        Arrays.sort(rectangleArray, new BottomComparator());
        // 矩形有序表 按照左边界排序 方便线段重合处理
        TreeSet<Rectangle> leftRectangle = new TreeSet<>(new LeftComparator());
        for (int i = 0; i < rectangleArray.length;) {
            int bottom = rectangleArray[i].bottom;
            // 如果连续矩形的底边在同线上  同时加入有序表中
            while (i < rectangleArray.length && rectangleArray[i].bottom == bottom) {
                leftRectangle.add(rectangleArray[i]);
                i++;
            }
            // 删除掉其他高度没有超过当前层的矩形
            removeCurLevelBottom(leftRectangle, bottom);
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (Rectangle rectangle : leftRectangle) {
                while (!heap.isEmpty() && heap.peek() < rectangle.left) {
                    heap.poll();
                }
                heap.add(rectangle.right);
                max = Math.max(max, heap.size());
            }
        }
        return max;
    }
    
    private Rectangle[] convert(int[][] rectangles) {
        Rectangle[] rectangleArray = new Rectangle[rectangles.length];
        for (int i = 0; i < rectangles.length; i++) {
            Rectangle rectangle = new Rectangle(rectangles[i][1], rectangles[i][3], rectangles[i][0], rectangles[i][2]);
            rectangleArray[i] = rectangle;
        }
        return rectangleArray;
    }
    
    private void removeCurLevelBottom(TreeSet<Rectangle> rectangles, int bottom) {
        Iterator<Rectangle> iterator = rectangles.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().top <= bottom) {
                iterator.remove();
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] rectangles = {{1, 3, 3, 1}, {2, 6, 5, 3}, { 3, 8, 8, 5}};
        RectangleMaxCover rectangleMaxCover = new RectangleMaxCover();
        System.out.println(rectangleMaxCover.rectangleMaxCover(rectangles));
    }
    
    static class Rectangle {
        int top;
        int bottom;
        int left;
        int right;
        
        Rectangle(int top, int bottom, int left, int right) {
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
        }
    }
    
    static class BottomComparator implements Comparator<Rectangle> {
        
        @Override
        public int compare(Rectangle r1, Rectangle r2) {
            return r1.bottom - r2.bottom;
        }
    }
    
    static class LeftComparator implements Comparator<Rectangle> {
        
        @Override
        public int compare(Rectangle r1, Rectangle r2) {
            return r1.left - r2.left;
        }
    }
}
