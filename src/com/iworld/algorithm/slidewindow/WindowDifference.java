package com.iworld.algorithm.slidewindow;

import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 从左到右的模型 滑动窗口  窗口内最大最小差值
 * 给定一个数组arr 和一个整数num  某一个子数组sub 如果想达标 必须满足sub中最大数值-sub中最小值<=num
 * 返回arr中达标子数组的数量
 * @date 2022/6/6 20:08
 */
public class WindowDifference {
    
    /**
     * 满足条件潜台词 如果一个数组的最大值-数组最小值满足条件 则当前数组的子数组一定满足条件
     * 子数组最大值一定小于等于数组上最大值，子数组最小值一定大于等于数组最小值，所以子数组最大值-子数组最小值 < 数组最大值-数组最小值
     * 如果一个数组不满足条件则以此数组为子数组的数组 一定不满足条件
     * @param arr
     * @param num
     * @return
     */
    public static int getSubArrCount(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // 窗口内最大值在头部
        LinkedList<Integer> max = new LinkedList<>();
        // 窗口内最小值在头部
        LinkedList<Integer> min = new LinkedList<>();
        int count = 0;
        // 记录最左侧位置
        int l = 0;
        // 记录最右侧不符合的位置
        int r = 0;
        // 左侧l位置  然后r一直滑动 直到r不满足 然后结算l到r-1范围符合要求的子数组
        // 结算后 l向下走一位置 然后r继续向下走 直到不满足条件 然后结算
        while (l < arr.length) {
            while (r < arr.length) {
                while (!min.isEmpty() && arr[min.peekLast()] >= arr[r]) {
                    min.pollLast();
                }
                min.addLast(r);
                while (!max.isEmpty() && arr[max.peekLast()] <= arr[r]) {
                    max.pollLast();
                }
                max.addLast(r);
                // 当数组中最大值-最小值不满足要求的时候 右边界不在推进
                if (arr[max.getFirst()] - arr[min.getFirst()] > num) {
                    break;
                }
                r ++;
            }
            // 计算符合要求子数组 统计以l为开头的到r范围的所有满足条件的子数组
            count += r - l;
            // l位置过期 最小值头部如果是l位置 弹出不再使用 l右移
            if (min.getFirst() == l) {
                min.pollFirst();
            }
            // 最大值头部如果是l位置 弹出不再使用 l右移
            if (max.getFirst() == l) {
                max.pollFirst();
            }
            l ++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int num = 3;
        int count = getSubArrCount(arr, num);
        System.out.println(count);
    }
}
