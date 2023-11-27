package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.1.2 单一括号问题
 * 17 经典题
 * @date 2022/7/11 18:37
 */
public class SingleParentheses {
    
    /**
     * 验证括号字符串是否符合规则
     * @param s
     * @return
     */
    public boolean isisValid(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            count = chars[i] == '(' ? count + 1 : count - 1;
        }
        return count == 0;
    }
    
    /**
     * 需要补几个括号 使得整体字符串括号有效
     * @param s
     * @return
     */
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
