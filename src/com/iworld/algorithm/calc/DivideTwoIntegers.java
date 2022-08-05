package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 29. Divide Two Integers   Medium
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 *
 *
 * Constraints:
 *
 * -231 <= dividend, divisor <= 231 - 1
 * divisor != 0
 * https://leetcode.com/problems/divide-two-integers/
 * 不适用运算符对两个数加减乘除
 * @date 2022/8/3 13:17
 */
public class DivideTwoIntegers {
    
    public int divide(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 被除数是系统最小
        if (dividend == Integer.MIN_VALUE) {
            // 如果除数是-1 返回系统最大
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            // 被除数是负数 需要加1 以防转正数溢出 计算结果是少除一次的
            int res = div(add(dividend, 1), divisor);
            // 计算剩余未除的值minus(dividend, multi(res, divisor)
            int minus = minus(dividend, multi(res, divisor));
            // 将剩余未除的值再次除 除数 div(minus(dividend, multi(res, divisor)), divisor)
            int div = div(minus, divisor);
            // 将两次结果相加
            return add(res, div);
        }
        // dividend不是系统最小，divisor也不是系统最小
        return div(dividend, divisor);
    }
    
    public int div(int a, int b) {
        // 如果负数  就转换为正数
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        // x右移到
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {
            // 直到最左大于y的位置
            if ((x >> i) >= y) {
                res |= (1 << i);
                // 将x减去移动位置的y 剩余循环处理直到移位结束
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }
    
    /**
     * -n
     * @param n
     * @return
     */
    public int negNum(int n) {
        return add(~n, 1);
    }
    
    public static boolean isNeg(int n) {
        return n < 0;
    }
    
    public int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            // 表示b的最后一位是1 如果是0不需要处理
            if ((b & 1) != 0) {
                // 用上次结果加上便宜一位后的a
                res = add(res, a);
            }
            // a向左偏移一位 表示下次与b高一位相乘需要进位
            a <<= 1;
            // b右移一位 表示当前位置已经处理完毕 需要处理前一位和a相乘
            b >>>= 1;
        }
        return res;
    }
    
    /**
     * 两数分别异或 两数无符号相加
     * 想法计算出两个数进位信息 进位信息不为0 继续将结果和进位信息再次异或
     * a b两数进位信息位运算  (a & b) << 1
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        // 两数和
        int sum = a;
        // 两数二进制各位置进位数
        int nextNum = b;
        // 如果两个数不再有进位数据 可以用异或表示两数相加
        while (nextNum != 0) {
            int tmp = sum;
            // 两数无符号位相加
            sum = tmp ^ nextNum;
            // 两数相加进位数
            nextNum = (tmp & nextNum) << 1;
        }
        return sum;
    }
    
    public int add2(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }
    
    /**
     * 两数相减 可以转换a+(-b)
     * -b 使用位运算表示 b二进制位取反 然后加1
     * @param a
     * @param b
     * @return
     */
    public int minus(int a, int b) {
        return add(a, add(~b, 1));
    }
    
    public int minus2(int a, int b) {
        // 两数和
        int sum = a;
        // 两数二进制各位置进位数
        int nextNum = b;
        // 如果两个数不再有进位数据 可以用异或表示两数相加
        while (nextNum != 0) {
            // 两数无符号位相加
            sum = sum ^ nextNum;
            // 两数相加进位数
            nextNum = (sum & nextNum) << 1;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        DivideTwoIntegers divideTwoIntegers = new DivideTwoIntegers();
        int a = 20;
        int b = -10;
        System.out.println(divideTwoIntegers.add(a, b));
        System.out.println(divideTwoIntegers.add2(a, b));
        System.out.println(divideTwoIntegers.minus(a, b));
        System.out.println(divideTwoIntegers.minus2(a, b));
        System.out.println(divideTwoIntegers.divide(a, b));
    }
}
