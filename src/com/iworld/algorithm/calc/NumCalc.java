package com.iworld.algorithm.calc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/9/23 21:55
 */
public class NumCalc {
    
    /**
     * 给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3的x次幂
     *
     * 链接：https://leetcode-cn.com/problems/power-of-three
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        boolean isPower = false;
        double power = 0;
        double num = 3;
        int count = 0;
        while (power <= n) {
            power = Math.pow(num, count);
            if (power == n) {
                isPower = true;
                break;
            }
            count ++;
        }
        return isPower;
    }
    
    public boolean isPowerOfThree2(int n) {
        int num = 3;
        while (n > 0 && n % num == 0) {
            n = n / 3;
        }
        return n == 1;
    }
    
    /**
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        int xorNum = a ^ b;
        int andLeftNum = (a & b) << 1;
        if (andLeftNum != 0) {
            return getSum(xorNum, andLeftNum);
        }
        return xorNum;
    }
    
    public static void main(String[] args) {
//        NumCalc numCalc = new NumCalc();
//        boolean powerOfThree = numCalc.isPowerOfThree2(27);
//        System.out.println(powerOfThree);
//        Integer a = new Integer(1);
//        Integer b = null;
//        System.out.println(a == b);
//        int sum = numCalc.getSum(20, -30);
//        System.out.println(sum);
//        List<String> result = new ArrayList<>();
//        result.add("123abccd");
//        result.add("abcdc123");
//        List<String> strings = filterSameNum(result);
//        System.out.println(strings);
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new TestThread("A", null));
            Thread t2 = new Thread(new TestThread("B", t1));
            Thread t3 = new Thread(new TestThread("C", t2));
            t1.start();
            t2.start();
            t3.start();
        }
        
    }
    public static List<String> filterSameNum(List<String> strs) {
        Set<Integer> strNums = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String str : strs) {
            int start = -1;
            int end = -1;
            for (int i = 0; i < str.length(); i++) {
                if (isNumber(str.charAt(i))) {
                    start = i;
                }
                if (start != -1) {
                    if (!isNumber(str.charAt(i))) {
                        end = i;
                        break;
                    }
                    if (i == str.length() - 1) {
                        end = str.length();
                        break;
                    }
                }
            }
            String substring = str.substring(start, end);
            Integer num = Integer.valueOf(substring);
            if (strNums.contains(num)) {
                continue;
            }
            strNums.add(num);
            result.add(str);
        }
        return result;
    }
    public static boolean isNumber(char ch) {
        if (ch < 48 || ch > 57) {
            return false;
        }
        return true;
    }
    
    static class TestThread implements Runnable {
        String ss;
        Thread tt;
        TestThread(String s, Thread thead) {
            ss = s;
            tt = thead;
        }
        
        @Override
        public void run() {
            if (tt != null) {
                try {
                    tt.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(ss);
            
        }
    }
    
    public void sortArray() {
    
    }
}
