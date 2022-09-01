package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 204. Count Primes
 * Medium
 * 5479
 * 1082
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 * Constraints:
 *
 * 0 <= n <= 5 * 10^6
 * https://leetcode.com/problems/count-primes/
 * @date 2022/9/1 15:23
 */
public class CountPrimes {
    
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] cache = new boolean[n];
        int count = n >> 1;
        for (int i = 3; i * i < n; i += 2) {
            if (cache[i]) {
                continue;
            }
            for (int j = i * i; j < n;j = j + 2 * i) {
                if (!cache[j]) {
                    cache[j] = true;
                    count--;
                }
            }
        }
        return count;
    }
    
    public int countPrimes2(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        CountPrimes countPrimes = new CountPrimes();
        int n = 10000;
        System.out.println(countPrimes.countPrimes(n));
    }
}
