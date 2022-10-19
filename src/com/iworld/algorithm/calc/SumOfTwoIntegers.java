package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 371. Sum of Two Integers
 * Medium
 * 3084
 * 4373
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 * Constraints:
 *
 * -1000 <= a, b <= 1000
 * https://leetcode.com/problems/sum-of-two-integers/
 * @date 2022/10/19 14:37
 */
public class SumOfTwoIntegers {
    
    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = a ^ b;
            int and = a & b;
            b = and << 1;
            a = tmp;
        }
        return a;
    }
    
    public static void main(String[] args) {
        int a = 10;
        int b = -3;
        SumOfTwoIntegers sumOfTwoIntegers = new SumOfTwoIntegers();
        System.out.println(sumOfTwoIntegers.getSum(a, b));
    }
}
