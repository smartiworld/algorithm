package com.iworld.algorithm;

/**
 * 跳跃数组
 *  1340给你一个整数数组 arr 和一个整数 d 。每一步你可以从下标 i 跳到：
 *
 * i + x ，其中 i + x < arr.length 且 0 < x <= d 。
 * i - x ，其中 i - x >= 0 且 0 < x <= d 。
 * 除此以外，你从下标 i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，其中下标 k 是所有 i 到 j 之间的数字（更正式的，min(i, j) < k < max(i, j)）。
 *
 * 你可以选择数组的任意下标开始跳跃。请你返回你 最多 可以访问多少个下标。
 *
 * 请注意，任何时刻你都不能跳到数组的外面。
 *
 * @Autor iworld
 * @Date 2020-05-11 13:48
 */
public class SkipArray {

    int[] arr = new int[]{6,4,14,6,8,13,9,7,10,6,12};

    int x = 2;

    int y = 1;
    public static void main(String[] args) {
        int z=2;

        SkipArray t=new SkipArray();
        System.out.println(t.x+t.y+z);
    }

    public int maxJump(int[] arrys, int d){
        int maxIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            int current = arrys[i];
            int currentIndex = 0;
            int currentJump = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if(arrys[j] < current && j -i <= d){
                    currentIndex += 1;
                }
            }
            for (int k = i - 1; 0 < k && i - k <= d; k++){

            }
        }
        return 1;
    }
}
