package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.7.3 需要补充几个数可以使数组arr和到达1~aim所有
 * 给定一个有序的正数数组arr和一个正数aim，如果可以自由选择arr中的数字，想累加得 到 1~aim 范围上所有的数，返回arr最少还缺几个数。
 * 【举例】
 * arr = {1,2,3,7}， aim = 15
 * 想累加得到 1~15 范围上所有的数，arr 还缺 14 这个数，所以返回1
 * arr = {1,5,7}， aim = 15
 * 想累加得到 1~15 范围上所有的数，arr 还缺 2 和 4，所以返回2
 * @date 2023/11/15 19:58
 */
public class NeedCountSumToAim {

    public static int needCount(int[] arr, int aim) {
        // 当前所到最大值
        int range = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            // 当前范围
            while (arr[i] - 1 > range) {
                range += range + 1;
                count ++;
                if (range >= aim) {
                    return count;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return count;
            }
        }
        while (aim > range) {
            range += range + 1;
            count ++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,5,7};
        int aim = 15;
        System.out.println(needCount(arr, aim));
    }
}
