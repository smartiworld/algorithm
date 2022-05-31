package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 29.两数相除
 * 给定两个整数，被除数dividend和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 示例1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * 提示：
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31− 1。
 *
 * 链接：https://leetcode.cn/problems/divide-two-integers
 * @date 2022/5/31 16:58
 */
public class TwoNumDivide {
    
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        if (dividend == 0 || divisor == 0) {
            return 0;
        }
        int negative = -1;
        if((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)){
            negative = 1;
        }
        // 都变成正数
        long a = dividend > 0 ? dividend : -(long)dividend;
        // 都变成正数
        long b = divisor > 0 ? divisor : -(long)divisor;
        long res = process(a, b);
        if(negative > 0) {
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        }
        return (int)-res;
    }
    
    /**
     * 50/4
     * （（4+4）+8）+16 =
     * 暴力解使用减法 每次减去除数 结果+1
     * 当前解使用除数翻倍 结果也是翻倍
     * 如果翻倍的值大于被除数 当前结果不会翻倍
     * 使用原被除数-翻倍后的除数值  递归调用  除数不变
     * @param a   被除数  每次减2N*b
     * @param b   除数  不变
     * @return
     */
    private long process(long a, long b){
        // 被除数如果小于除数返回0
        if(a < b) {
            return 0;
        }
        long count = 1;
        long tb = b;
        while((tb + tb) <= a){
            count = count + count;
            tb = tb + tb;
        }
        return count + process(a - tb , b);
    }
    
    public static void main(String[] args) {
        TwoNumDivide twoNumDivide = new TwoNumDivide();
        System.out.println(twoNumDivide.divide(-2147483648, 2));
    }
    
}
