package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 归并排序 求数组小和  当左边数小于右边数的时候产生小和
 * @date 2021/7/10 17:36
 */
public class SmallSum {
    
    /**
     * 求数组小和
     * 利用归并排序每次合并比较 当左边数小于右边数的时候产生小和 右边数到right位置数都会大于当前左边数
     * 一并计算出 (right - r + 1)
     * @param arr
     */
    public static int mergeAndSum(int[] arr, int left, int mid, int right) {
        int[] copyArr = new int[right - left + 1];
        int sum = 0;
        int i = 0;
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            //只有每次merge 才对比当前数，计算右边比当前数大 只能在右边 归并集中
            //合并完后不会再产生小和计算 在下一次合并后对比右边数值
            //(right - r + 1) 右边已经排序好 如果r位置数大于l位置数 则产生小和 r-right的数 一定比当前数大 一并计算出
            //然后排序
            sum += arr[l] < arr[r] ? (right - r + 1) * arr[l] : 0;
            copyArr[i++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
        }
        while (l <= mid) {
            copyArr[i++] = arr[l++];
        }
        while (r <= mid) {
            copyArr[i++] = arr[r++];
        }
        for (i = 0; i < copyArr.length; i++) {
            arr[left + i] = copyArr[i];
        }
        return sum;
    }
    
    public static int split(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return split(arr, left, mid) + split(arr, mid + 1, right) + mergeAndSum(arr, left, mid, right);
    }
    
    public static int smallSum(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        return split(arr, 0, arr.length);
    }
}
