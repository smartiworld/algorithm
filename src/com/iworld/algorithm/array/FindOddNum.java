package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/7/16 17:32
 */
public class FindOddNum {
    
    /**
     * 数组中找出奇数个数的数字
     * 数组中有一个奇数个数数字 其他数字都出现偶数次
     * a^0=a a^a=0
     * 异或运算无顺序要求 偶数个数异或运算为0 奇数个数异或结果为奇数个数的数
     * @param arr
     * @return
     */
    public static Integer findOneOddNumber(int[] arr) {
        if(arr.length < 3) {
            return null;
        }
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor = eor ^ arr[i];
        }
        return eor;
    }
    
    /**
     * 一个数组中有两个出现奇数次的数 且两个数不相等 数组中查找出这两个奇数次的数
     * 二进制找出最右侧为1的 a & (~a + 1)
     * 整个数组进行异或运算 得出结果为两个奇数次数字异或 ，找出结果值最右侧为1的位置，此时肯定一数当前位置为1  另一个数当前位置为0
     * 然后数组与当前找出为1的位置与运算不为0的数 得出结果为奇数次数当前位置为1的  然后与上次eor结果异或运算得出另一个奇数次数数字
     * @param arr
     * @return
     */
    public static int[] findTwoOddNumber(int[] arr) {
        int[] result = new int[2];
        // 两根奇数异或结果
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor = eor ^ arr[i];
        }
        //eor=两个奇数异或
        //找出最右侧是1的位置 也就是一个数最右侧是1另一个最右侧是0 两个数最右侧1位置数肯定不是相同的
        int rightOne = eor & (~eor + 1);
        // 单个奇数与其他偶数异或结果
        int eor1 = 0;
        // 遍历数组找出最右侧为1位置的数
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & rightOne) != 0) {
                eor1 = eor1 ^ arr[i];
            }
        }
        result[0] = eor ^ eor1;
        result[1] = eor1;
        return result;
    }
    
    /**
     * 计算一个整数1的个数
     * @param a
     * @return
     */
    public static int countOneTimes(int a) {
        int count = 0;
        while (a > 0) {
            int rightOne = a & (~a + 1);
            count ++;
            a = a ^ rightOne;
        }
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 2, 9, 9, 2, 2, 5, 5};
        int[] oneOddNumber = findTwoOddNumber(arr);
        for (int i : oneOddNumber) {
            System.out.println(i + ",");
        }
        int a = 15;
        int i = countOneTimes(a);
        System.out.println(i);
    }
    
    
}
