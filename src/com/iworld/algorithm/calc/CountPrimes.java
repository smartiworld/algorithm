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
        // 保存1~n-1范围 true表示当前位置为非素数 如果已经为true表示已经处理为非素数 不需要再处理
        boolean[] cache = new boolean[n];
        // 1~n 刨除所有偶数后剩余最大素数个数 2，4，6 8 。。。刨除掉
        int count = n >> 1;
        // i遍历所有奇数位置
        for (int i = 3; i * i < n; i += 2) {
            // 当前数如果是非素数 跳过处理 放cache时count已经--
            if (cache[i]) {
                continue;
            }
            // j = j + 2 * i跳过偶数个i 如果i为3 j=9 下一次j=15 下一次21
            // j = j + 2 * i ,j=i*i,j=i*(i+2) i已经为奇数，所以每次需要i+2结果也为奇数 i*i后奇数
            // 当前走过的数全国为非素数 如果缓存中记录当前数为false 修改为true 然后素数数量--
            for (int j = i * i; j < n; j = j + 2 * i) {
                // 如果缓存中记录当前数为false 修改为true 然后素数数量--
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
        // 从2开始 小于n
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
        int n = 19;
        System.out.println(countPrimes.countPrimes(n));
        System.out.println(countPrimes.countPrimes2(n));
    }
}
