package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 69. Sqrt(x)   Easy
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 * Example 1:
 *
 * Input: x = 4
 * Output: 2
 * Example 2:
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 * Constraints:
 *
 * 0 <= x <= 2 31 - 1
 * @date 2022/8/16 17:44
 */
public class SqrtX {
    
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long start = 1;
        long end = x;
        long ans = -1;
        while (start <= end) {
            long mid = start + ((end - start) >> 1);
            long pow = mid * mid;
            if (pow <= x) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return (int)ans;
    }
    
    public static void main(String[] args) {
        SqrtX sqrtX = new SqrtX();
        int x = 3;
        System.out.print(sqrtX.mySqrt(x));
    }
}
