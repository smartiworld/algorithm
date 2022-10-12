package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 326. Power of Three
 * Easy
 * 2127
 * 203
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 * Explanation: 27 = 33
 * Example 2:
 *
 * Input: n = 0
 * Output: false
 * Explanation: There is no x where 3x = 0.
 * Example 3:
 *
 * Input: n = -1
 * Output: false
 * Explanation: There is no x where 3x = (-1).
 *
 * Constraints:
 *
 * -2^31 <= n <= 2^31 - 1
 *
 * Follow up: Could you solve it without loops/recursion?
 * @date 2022/10/12 20:56
 */
public class PowerOfThree {
    
    /**
     * 如果一个数字是3的某次幂，那么这个数一定只含有3这个质数因子
     * 1162261467是int型范围内，最大的3的幂，它是3的19次方
     * 这个1162261467只含有3这个质数因子，如果n也是只含有3这个质数因子，那么
     * 1162261467 % n == 0
     * 反之如果1162261467 % n != 0 说明n一定含有其他因子
     * @param n
     * @return
     */
    public static boolean isPowerOfThree(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }
    
    public static boolean isPowerOfThree2(int n) {
        if(n == 1) {
            return true;
        }
        if(n > 0 && n % 3 == 0) {
            return isPowerOfThree(n/3);
        }
        return false;
    }
    
}
