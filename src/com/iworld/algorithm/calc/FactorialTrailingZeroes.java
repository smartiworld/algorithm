package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 172. Factorial Trailing Zeroes
 * Medium
 * 2261
 * 1739
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 *
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Example 3:
 *
 * Input: n = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 104
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 * @date 2022/9/27 20:14
 */
public class FactorialTrailingZeroes {
    
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
    
    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n > 0) {
            if (n % 5 == 0) {
                ans++;
            }
            n--;
        }
        return ans;
    }
    
    public static void main(String[] args) {
        FactorialTrailingZeroes factorialTrailingZeroes = new FactorialTrailingZeroes();
        for (int n = 5; n <= 20; n++) {
            long ans = 1;
            int k = n;
            while (k > 0) {
                ans *= k;
                k--;
            }
            System.out.println(n + "=============" + ans);
            System.out.println(factorialTrailingZeroes.trailingZeroes(n));
        }
        
        for (int i = 1; i < 105; i++) {
            printFactorial(i);
        }
        System.out.println(Integer.MAX_VALUE);
    }
    
    public static void printFactorial(int n) {
        long result = 1;
        for (int i = n; i >= 1; i--) {
            result *= i;
        }
        System.out.println(n + "======" + result);
    }
}
