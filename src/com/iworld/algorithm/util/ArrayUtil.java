package com.iworld.algorithm.util;

import java.util.Arrays;
import java.util.Random;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/7/19 21:30
 */
public class ArrayUtil {
    
    public static int[] generateIntArray(int length, int max) {
        Random r = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(max);
        }
        return arr;
    }
    
    
    // for test
    public static int[] generateSortArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }
    
    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }
    
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print("{");
            }
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            } else {
                System.out.println("}");
            }
        }
    }
    
    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print("{");
            }
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            } else {
                System.out.println("}");
            }
        }
    }
}
