package com.iworld.algorithm.slidewindow;

import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 从左到右的模型 滑动窗口
 * 给定一个大小为W的窗口 一次走过arr 返回每次滑出W窗口的时候的最大值
 * 例 arr=[4,3,5,4,3,3,6,7] W=3
 * 结果[5,5,5,4,6,7]
 * 使用双端队列
 * @date 2022/6/6 14:14
 */
public class WindowMaxValue {
    
    /**
     * 使用双端队列 两个指针 指针都从左到右滑动 如果当前位置的值小于队列中的值直接添加 如果大于等于队列中值 从尾部弹出小于当前位置值的位置
     *
     * @param arr
     * @param w
     * @return
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || w > arr.length) {
            return null;
        }
        // 存放窗口内最大值下标 最大值放在头部
        LinkedList<Integer> windowIndex = new LinkedList<>();
        int r = 0;
        int i = 0;
        int[] res = new int[arr.length - w + 1];
        while (r < arr.length) {
            // 如果大于等于队列中值 从尾部弹出小于当前位置值的位置
            while (!windowIndex.isEmpty() && arr[windowIndex.peekLast()] <= arr[r]) {
                windowIndex.pollLast();
            }
            windowIndex.addLast(r);
            // 队列头部记录最左元素位置 如果窗口大小超过给定窗口 需弹出最前位置
            if (r - windowIndex.peekFirst() >= w) {
                windowIndex.pollFirst();
            }
            // 如果窗口已经形成 每次手机队列最前位置
            if (r - w + 1 >= 0) {
                res[i++] = arr[windowIndex.peekFirst()];
            }
            r++;
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int w = 3;
        int[] maxWindow = getMaxWindow(arr, w);
        for (int i : maxWindow) {
            System.out.println(i);
        }
    }
}
