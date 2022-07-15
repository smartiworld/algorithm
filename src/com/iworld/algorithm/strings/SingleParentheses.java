package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单一括号问题
 * 17 经典题
 * @date 2022/7/11 18:37
 */
public class SingleParentheses {
    
    public boolean isisValid(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            count = chars[i] == '(' ? count + 1 : count - 1;
        }
        return count == 0;
    }
    
    public int needParenthesesCount(String s) {
        char[] chars = s.toCharArray();
        int needCont = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (count == 0) {
                    needCont ++;
                } else {
                    count --;
                }
            } else {
                count ++;
            }
        }
        return needCont + count;
    }
    
    public static void main(String[] args) {
        SingleParentheses singleParentheses = new SingleParentheses();
        System.out.println(singleParentheses.isisValid("(()))"));
        System.out.println(singleParentheses.needParenthesesCount("(())))(("));
    }
}
