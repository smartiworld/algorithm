package com.iworld.algorithm.util;

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
}
