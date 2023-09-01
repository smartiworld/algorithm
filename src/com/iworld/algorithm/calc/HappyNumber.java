package com.iworld.algorithm.calc;

import java.util.HashSet;

/**
 * @author gq.cai
 * @version 1.0
 * @description 202. Happy Number
 * Easy
 * 6614
 * 828
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 * https://leetcode.com/problems/happy-number/
 * @date 2022/9/6 15:59
 */
public class HappyNumber {
    
    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            int sum = 0;
            while (n != 0) {
                sum += Math.pow((n % 10), 2);
                n = n / 10;
            }
            n = sum;
        }
        return n == 1;
    }
    
    public boolean isHappy2(int n) {
        HashSet<Integer> cache = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            int num = n;
            while (num != 0) {
                sum += Math.pow((num % 10), 2);
                num = num / 10;
            }
            if (cache.contains(sum)) {
                break;
            }
            n = sum;
            cache.add(n);
        }
        return n == 1;
    }
}
