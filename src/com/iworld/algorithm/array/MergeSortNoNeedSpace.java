package com.iworld.algorithm.array;

import java.util.Random;

/**
 * @author gq.cai
 * @version 1.0
 * @description 归并排序 范围内分左边数组和右边数组  然后将左右两边数组排序放回新的数组中 递归调用
 * @date 2021/7/3 18:44
 */
public class MergeSortNoNeedSpace {
    
    public static void main(String[] args) {
        //int[] arr = generateIntArray(100);
        //3,43,63,81,85,112,135,146,139,159
        //3,43,63,81,85,112,135,146,139,159,
        //3,43,63,81,85,112,135,146,139,159,
        //int [] arr = {112, 135, 159, 146, 3, 43, 63, 81, 139, 85};
        //int [] arr = {-12, -29, -37, 56, 59, 1, -71, -24, 31, 40, -3, -73, 9, 79, -21, -26 };
        //MergeSort.mergeSort1(arr);
//        mergeSort(arr);
//        for (int i : arr) {
//            System.out.print(i + ",");
//        }
        int[][] arrss = new int[999][];
        Random r = new Random();
        for (int i = 0; i < arrss.length; i++) {
            arrss[i] = generateIntSortArray(r.nextInt(99) + 1, 50);
        }
        int[] ints = getCustomArr(arrss, 10);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
    
    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if(arr ==null || arr.length < 2) {
            return ;
        }
        split(arr, 0, arr.length -1);
        //merge(arr, left, mid, right);
    }
    
    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     */
    public static void split(int[] arr, int left, int right) {
        if(left == right) {
            return ;
        }
        int mid = left + ((right - left) >> 1);
        split(arr, left, mid);
        split(arr, mid + 1, right);
        //merge(arr, left, mid, right);
        mergeNeedSpace(arr, left, mid, right);
    }
    
    /**
     * 归并排序合并 不申请空间 时间长
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        int rightStart = mid + 1;
        while(left < right) {
            if(arr[left] > arr[rightStart]) {
                //交换位置
                change(arr, left, rightStart);
                int rightFirst = rightStart;
                int rightSecond = rightStart + 1;
                while (rightSecond <= right) {
                    if(arr[rightFirst] <= arr[rightSecond]) {
                        break;
                    }
                    change(arr, rightFirst, rightSecond);
                    rightFirst ++;
                    rightSecond ++;
                }
            }
            if(left >= mid) {
                rightStart ++;
            }
            if(rightStart > right) {
                break;
            }
            left ++;
        }
    }
    
    /**
     * 交换位置
     * @param arr
     * @param left
     * @param right
     */
    public static void change(int[] arr, int left, int right) {
        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }
    
    /**
     * 归并排序合并 不申请空间
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void mergeNeedSpace(int[] arr, int left, int mid, int right) {
        int[] copyArr = new int[right - left + 1];
        int i = 0;
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
             copyArr[i++] = arr[l] <= arr[r] ? arr[l++] : arr[r++];
        }
        while (l <= mid) {
            copyArr[i++] = arr[l++];
        }
        while (r <= right) {
            copyArr[i++] = arr[r++];
        }
        for (i = 0; i < copyArr.length; i++) {
            arr[left + i] = copyArr[i];
        }
    }
    
    public static int[] generateIntArray(int length) {
        int max = 200;
        Random r = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(max);
        }
        return arr;
    }
    
    public static int[] generateIntSortArray(int length, int max) {
        Random r = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            int anInt = r.nextInt(max);
            if (i > 0 && anInt < arr[i - 1]) {
                anInt = arr[i - 1] + anInt;
            }
            arr[i] = anInt;
        }
        return arr;
    }
    
    /**
     * 获取多个排序数组 中最大长度个元素
     * 1.将数组中相邻数组合并为一个数组，每次遍历过后 整个数组长度变为原来一半
     * 2.直到所有合并成为一个数组时候
     * @param arrss
     * @param length
     * @return
     */
    public static int[] getCustomArr(int[][] arrss, int length) {
        int[] ints = moreMergeSort(arrss);
        int[] result = new int[length];
        System.arraycopy(ints, ints.length - length, result, 0, length);
        return result;
    }
    /**
     * 多个有序数组 合并排序
     * @param arrss
     */
    public static int[] moreMergeSort(int[][] arrss) {
        // 控制总遍历次数 直到将二维数组元素合并到一个数组中
        int k = arrss.length;
        int[][] useArras = arrss;
        while (k > 1) {
            int[][] resultArray = new int[(k + 1)/2][];
            int j = 0;
            // 遍历二维数组，每两个排序数组合并到一个数组中
            for (int i = 0; i < useArras.length; ) {
                int[] result = null;
                if (i == useArras.length -1) {
                    result = useArras[i];
                } else {
                    result = mergeTwoArray(useArras[i], useArras[i + 1]);
                }
                resultArray[j ++] = result;
                i = i + 2;
            }
            useArras = resultArray;
            k = useArras.length;
        }
        // 返回第一个位置数组
        return useArras[0];
    }
    
    /**
     * 将两个有序数组合并成一个数组
     * @return
     */
    public static int[] mergeTwoArray(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length + arr2.length];
        int arr1Index = 0;
        int arr2Index = 0;
        int resultIndex = 0;
        while (arr1Index < arr1.length && arr2Index < arr2.length) {
            if (arr1[arr1Index] < arr2[arr2Index]) {
                result[resultIndex ++] = arr1[arr1Index ++];
            } else {
                result[resultIndex ++] = arr2[arr2Index ++];
            }
        }
        int endInex = 0;
        int[] endArra = new int[0];
        if (arr1Index < arr1.length) {
            endInex = arr1Index;
            endArra = arr1;
        }
        if (arr2Index < arr2.length) {
            endInex = arr2Index;
            endArra = arr2;
        }
        while (resultIndex < result.length) {
            result[resultIndex ++] = endArra[endInex ++];
        }
        return result;
    }
    
}
