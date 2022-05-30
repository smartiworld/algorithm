package com.iworld.algorithm.calc;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/5/29 23:25
 */
public class IntegerRevert {
    
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
        int limit = x < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        while (num != 0) {
            int bit = num % 10;
            if (numLength == 10 && isLess) {
                int limitBit = limit / (int) Math.pow(10, tmpNumLength - 1);
                if (Math.abs(bit) > Math.abs(limitBit)) {
                    return 0;
                } else if (Math.abs(bit) < Math.abs(limitBit)) {
                    isLess = false;
                }
                limit = limit % (int) Math.pow(10, tmpNumLength - 1);
            }
            result = result + bit * (int) Math.pow(10, tmpNumLength - 1);
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
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE) {
                return 0;
            }
            int bit = num % 10;
            result = result * 10 + bit;
            num = num / 10;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        IntegerRevert integerRevert = new IntegerRevert();
        System.out.println(integerRevert.reverse(-2147483412));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
