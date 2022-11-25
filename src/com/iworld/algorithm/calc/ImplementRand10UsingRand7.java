package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 470. Implement Rand10() Using Rand7()
 * Medium
 * 951
 * 321
 * Given the API rand7() that generates a uniform random integer in the range [1, 7],
 * write a function rand10() that generates a uniform random integer in the range [1, 10].
 * You can only call the API rand7(), and you shouldn't call any other API.
 * Please do not use a language's built-in random API.
 *
 * Each test case will have one internal argument n,
 * the number of times that your implemented function rand10() will be called while testing.
 * Note that this is not an argument passed to rand10().
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: [2]
 * Example 2:
 *
 * Input: n = 2
 * Output: [2,8]
 * Example 3:
 *
 * Input: n = 3
 * Output: [3,8,10]
 *
 * Constraints:
 *
 * 1 <= n <= 105
 *
 * Follow up:
 *
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 * https://leetcode.com/problems/implement-rand10-using-rand7/
 * @date 2022/11/24 23:31
 */
public class ImplementRand10UsingRand7 {
    
    public int rand10() {
        int min = 1;
        int max = 10;
        int size = max - min;
        int num = 1;
        // 多少位二进制1可以覆盖size
        while ((1 << num) - 1 < size) {
            num++;
        }
        int tMin = 1;
        int tMax = 7;
        int ans;
        do {
            ans = 0;
            for (int i = 0; i < num; i++) {
                int rand01 = rand01(tMin, tMax);
                ans = (ans << 1) | rand01;
            }
        } while (ans > 9);
        return ans + 1;
    }
    
    /**
     * 给定一个等概率区间min to max  等概率的返回0和1
     * 如果奇数 取到中间值 重新取 小半区认为是0 大半区认为是1
     * @param min
     * @param max
     * @return
     */
    private int rand01(int min, int max) {
        int size = max - min + 1;
        // 是否为奇数
        boolean odd = (size & 1) == 1;
        int ans = 0;
        int mid = min + ((max - min) >> 1);
        do {
            ans = rand7();
        } while (odd && ans == mid);
        return ans < mid ? 0 : 1;
    }
    
    public int rand7() {
        return 1 + (int) (Math.random() * 7);
    }
    
    public static void main(String[] args) {
        ImplementRand10UsingRand7 implementRand10UsingRand7 = new ImplementRand10UsingRand7();
        for (int i = 0; i < 100; i++) {
            System.out.println(implementRand10UsingRand7.rand10());
        }
    }
}
