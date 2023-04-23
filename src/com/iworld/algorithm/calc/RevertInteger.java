package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description 7. Reverse Integer
 * Medium
 * 8166
 * 10529
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * Example 1:
 *
 * Input: x = 123
 * Output: 321
 * Example 2:
 *
 * Input: x = -123
 * Output: -321
 * Example 3:
 *
 * Input: x = 120
 * Output: 21
 *
 * Constraints:
 *
 * -231 <= x <= 2^31 - 1
 * https://leetcode.com/problems/reverse-integer/
 * @date 2022/5/29 23:25
 */
public class RevertInteger {
    
    /**
     * 1.获取数字长度
     * 2.输入目标数字是正数还是负数 确定反转后上限
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;
        int num = x;
        int numLength = 0;
        while (num != 0) {
            num = num / 10;
            numLength ++;
        }
        boolean isLess = true;
        int tmpNumLength = numLength;
        num = x;
        // 确定上限
        int limit = x < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        while (num != 0) {
            // 当前位置需要添加的数字
            int bit = num % 10;
            // 当目标数字长度等于10的时候并且 目标数字没有确定反转后大小小于int最大值时
            if (numLength == 10 && isLess) {
                // 计算当前位置可以设置最大上限
                int limitBit = limit / (int) Math.pow(10, tmpNumLength - 1);
                // 当前位置已经超过上限位置数字 直接返回0
                if (Math.abs(bit) > Math.abs(limitBit)) {
                    return 0;
                } else if (Math.abs(bit) < Math.abs(limitBit)) {
                    // 如果没有超过 后续不用再考虑是否超过上限
                    isLess = false;
                }
                // 上限抛出当前最高位置
                limit = limit % (int) Math.pow(10, tmpNumLength - 1);
            }
            //result = result + bit * (int) Math.pow(10, tmpNumLength - 1);
            result = result * 10 + bit;
            num = num / 10;
            tmpNumLength --;
        }
        return result;
    }
    
    public int reverse2(int x) {
        int retValue = 0;
        while (x != 0) {
            if (retValue < Integer.MIN_VALUE / 10 || retValue > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            retValue = retValue * 10 + digit;
            x = x / 10;
        }
        return retValue;
    }
    
    public int reverse3(int x) {
        int result = 0;
        int num = x;
        while (num != 0) {
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            int bit = num % 10;
            result = result * 10 + bit;
            num = num / 10;
        }
        
        return result;
    }
    
    public int reverse4(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            // 表示newResult已经越界
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }
        return result;
    }
    
    public int reverseZuo(int x) {
        // 负数最高位为1 int32位 右移31位 如果32位为1 则为负数
        boolean neg = ((x >>> 31) & 1) == 1;
        // 统一转换为负数处理
        x = neg ? x : -x;
        int result = 0;
        // 倒数第二位值
        int max = Integer.MIN_VALUE / 10;
        // 最后一位值
        int min = Integer.MIN_VALUE % 10;
        while (x != 0) {
            // 如果当前值已经小于Integer.MIN_VALUE前九位值 或者已经等于前九尾 判断第当前要处理的数和Integer.MIN_VALUE最高位比较 如果小于 则越界
            if (result < max || (result == max && x % 10 < min)) {
                return 0;
            }
            int bit = x % 10;
            result = result * 10 + bit;
            x = x / 10;
        }
        return neg ? result : -result;
    }
    
    public static void main(String[] args) {
        RevertInteger integerRevert = new RevertInteger();
        int a = 2147483647;//2143847412
        System.out.println(integerRevert.reverse(-2147483412));
        System.out.println(integerRevert.reverse2(1534236469));
        System.out.println(integerRevert.reverse3(1534236469));
        System.out.println(integerRevert.reverse4(1463847412));
        System.out.println(integerRevert.reverseZuo(1463847412));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
