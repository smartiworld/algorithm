package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.5.4
 * 对于一个字符串, 从前开始读和从后开始读是一样的, 我们就称这个字符串是回文串。例如"ABCBA","AA", "A" 是回文串,
 * 而"ABCD", "AAB"不是回文串。牛牛特别喜欢回文串, 他手中有一个字符串s, 牛牛在思考能否从字 符串中移除部分(0个或多个)字符使其变为回文串，
 * 并且牛牛认为空串不是回文串。牛牛发现移除的方案可能有 很多种, 希望你来帮他计算一下一共有多少种移除方案可以使s变为回文串。对于两种移除方案,
 * 如果移除的字 符依次构成的序列不一样就是不同的方案。
 * 例如，XXY 4种 ABA 5种
 * 【说明】 这是今年的原题，提供的说明和例子都很让人费解。现在根据当时题目的所有测试用例，重新解释当时的题目
 * 含义:
 *
 *  1) "1AB23CD21"，你可以选择删除A、B、C、D，然后剩下子序列{1,2,3,2,1}，只要剩下的子序列是同一个，
 * 那么就只算1种方法，和A、B、C、D选择什么样的删除顺序没有关系。
 *
 * 2)"121A1"，其中有两个{1,2,1}的子序列，第一个{1,2,1}是由{位置0，位置1，位置2}构成，
 * 第二个{1,2,1} 是由{位置0，位置1，位置4}构成。这两个子序列被认为是不同的子序列。也就是说在本题中，
 * 认为字面值一样 但是位置不同的字符就是不同的。
 *
 * 3)其实这道题是想求，str中有多少个不同的子序列，每一种子序列只对应一种删除方法，那就是把多余的东 西去掉，而和去掉的顺序无关。
 *
 * 4)也许你觉得我的解释很荒谬，但真的是这样，不然解释不了为什么，XXY 4种 ABA 5种，而且其他的测 试用例都印证了这一点。
 *
 * 暴力方法尝试删除每个位置字符 连续两个 连续n-1个
 * @date 2023/9/20 19:51
 */
public class PalindromeWays {
    /**
     * 0个字符 空字符不是回文串
     * 1个字符 默认就是回文串
     * 删除字符变成回文串 多少种方式删除方式 也就是保留哪些字符可以组成回文串
     * 左到右的范围
     * 1.不保留l  不保留r
     * 2.保留l    不保留r
     * 3.不保留l  保留r
     * 4.保留l    保留r
     * 上述 互斥不重复 所有和即为dp[l][r]范围内保留种数
     * dp[l][r]=【1】+【2】+【3】+【4】（l位置字符和r位置字符相等的时候）
     * dp[l][r] 包括dp[i+1][r]和dp[i][r-1]
     * dp[l+1][r] 表示不保留l位置字符，但是r位置字符可以保留也可以不保留 结果就是【1】+【3】
     * dp[l][r-1] 表示不保留r位置字符，但是l位置字符可以保留也可以不保留 结果就是【1】+【2】
     * dp[l+1][r]+dp[l][r-1]=【1】+【3】+【1】+【2】
     * dp[l][r]=dp[l+1][r]+dp[l][r-1]-【1】=dp[l+1][r]+dp[l][r-1]-dp[l+1][r-1]
     * 【4】=dp[l+1][r-1]+1
     * @param s
     * @return
     */
    public static int ways(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        // 含义字符串长度多少的时候 多少种剪裁可以做成回文串
        // 也就是保留的种数
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
            if (i < len - 1) {
                dp[i][i + 1] = chars[i] == chars[i + 1] ? 3 : 2;
            }
        }
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                if (chars[i] == j) {
                    dp[i][j] += dp[i + 1][j - 1] + 1;
                }
            }
        }
        return dp[0][len - 1];
    }
    
    public static int way2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;
        }
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                dp[L][R] = dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
                if (s[L] == s[R]) {
                    dp[L][R] += dp[L + 1][R - 1] + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
    
    public static void main(String[] args) {
        String s = "XXY";
        System.out.println(ways(s));
        System.out.println(way2(s));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
