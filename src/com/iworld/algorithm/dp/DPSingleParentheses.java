package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单一括号动态规划
 * @date 2022/7/13 11:30
 */
public class DPSingleParentheses {
    
    /**
     * 返回括号最长有效长度 当前字符来到'('有效长度为0 来到')'时查看前括号有效长度前字符如果匹配则长度为前字符有效长度+2+前有效长度
     * @return
     */
    public int maxEffectiveLength(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[ i -1] == '(') {
                    dp[i] = 2;
                } else if (dp[i - 1] > 0) {
                    if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                        dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 1) > 0 ? dp[i - dp[i - 1] - 2] : 0);
                        max = Math.max(max, dp[i]);
                    }
                }
                
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        DPSingleParentheses dpSingleParentheses = new DPSingleParentheses();
        // ))()((())))()((())()
        System.out.println(dpSingleParentheses.maxEffectiveLength("))()((())))()((())()"));
    }
}
