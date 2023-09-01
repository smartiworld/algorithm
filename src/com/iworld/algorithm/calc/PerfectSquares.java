package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 279. Perfect Squares
 * Medium
 * 7550
 * 321
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words,
 * it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * https://leetcode.com/problems/perfect-squares/
 * @date 2022/10/4 20:54
 */
public class PerfectSquares {
    
    public int numSquares(int n) {
        int res = n;
        // 从2的平方尝试
        int num = 2;
        while (num * num < n) {
            // 最少需要几个当前完全平方数
            int a = n / (num * num);
            // 剩余的数 不能被完全平方的
            int b = n % (num * num);
            // 不能被当前num完全平方的继续尝试从初始位置 平方  知道当前数小于尝试的完全平方
            res = Math.min(res, a + numSquares(b));
            num++;
        }
        return res;
    }
    
    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        int n = 6;
        System.out.println(perfectSquares.numSquares(n));
    }
}
