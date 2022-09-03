package com.iworld.algorithm.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 166. Fraction to Recurring Decimal
 * Medium
 * 1720
 * 3151
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 *
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 *
 * Constraints:
 *
 * -231 <= numerator, denominator <= 231 - 1
 * denominator != 0
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * @date 2022/9/3 14:42
 */
public class FractionToRecurringDecimal {
    /**
     * 记录小数后除数放入map 如果出现相同的分子了
     * 后面就表示要循环了 此时从map中取出第一次出现的位置从当前位置后到结尾就需要加（）
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        long head = num / den;
        long mod = num % den;
        // 判断结果是否为负数 为负数需要在结果前添加负号
        boolean isNegative = numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0;
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        sb.append(head);
        if (mod == 0) {
            return sb.toString();
        }
        sb.append(".");
        int startIndex = 0;
        int endIndex = 0;
        boolean isCycle = false;
        // 记录生成余数字符串位置
        Map<Long, Integer> indexMap = new HashMap<>();
        // 生成的余数串
        List<Long> decimals = new ArrayList<>();
        while (mod != 0) {
            // 余数乘10接着除分母
            num = mod * 10;
            // 判断当前分子是否出现过 出现过不需要处理
            if (indexMap.containsKey(num)) {
                startIndex = indexMap.get(num);
                isCycle = true;
                break;
            }
            long decimal = num / den;
            mod = num % den;
            decimals.add(decimal);
            indexMap.put(num, endIndex++);
        }
        for (int i = 0; i < decimals.size(); i++) {
            if (isCycle && i == startIndex) {
                sb.append("(");
            }
            sb.append(decimals.get(i));
        }
        if (isCycle) {
            sb.append(")");
        }
        return sb.toString();
    }
    
    public String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
    
    public static void main(String[] args) {
        int numerator = -2147483648;
        int denominator = 1;
        FractionToRecurringDecimal fractionToRecurringDecimal = new FractionToRecurringDecimal();
        System.out.println(fractionToRecurringDecimal.fractionToDecimal(numerator, denominator));
        
    }
}
