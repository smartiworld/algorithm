package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 50. Pow(x, n)  Medium
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * Constraints:
 *
 * -100.0 < x < 100.0
 * -2 31次方 <= n <= 2 31次方-1
 * -104 <= xn <= 104
 * https://leetcode.com/problems/powx-n/
 * @date 2022/8/12 13:26
 */
public class PowXN {
    
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0D;
        }
        if (n == 0) {
            return 1D;
        }
        double ans = 1D;
        double s = x;
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        while (pow != 0) {
            if ((pow & 1) == 1) {
                ans = ans * s;
            }
            pow >>= 1;
            s = s * s;
        }
        if (n == Integer.MIN_VALUE) {
            ans = ans * x;
        }
        return n < 0 ? (1 / ans) : ans;
    }
    
    public static void main(String[] args) {
        double x = -1.00000;
        int n = Integer.MIN_VALUE;
        PowXN powXN = new PowXN();
        System.out.println(powXN.myPow(x, n));
    }
}
