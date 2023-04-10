package com.iworld.algorithm.dp.string;

/**
 * @author gq.cai
 * @version 1.0
 * @description 10. Regular Expression Matching
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * https://leetcode.com/problems/regular-expression-matching/
 * @date 2022/6/23 17:23
 */
public class DpRegularExpressionMatching {
    
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        return isValid(sChars, pChars) && process(sChars, 0, pChars ,0);
       
    }
    
    /**
     * 当前s来到x位置 x-1位置已经处理完毕 当前p来到了y位置 y-1位置已经处理完毕
     * *字符不可单独处理 需有前字符
     * 1.两个字符都来到结尾处的时候
     * 2.s串字符来到结尾 如果p字符没有结束 并且p下一位字符不是*字符则返回false p下一位为*匹配0个字符 然后来到p字符+2位置
     * 3.下一位是*字符
     *  3.1当前位置字符不匹配 包括字符两个字符串不相等或者匹配字符不为。符号 p字符可以跳过当前两位，继续和被匹配字符当前位置匹配
     *  3.2如果当前字符匹配也可能存在*匹配0个字符场景s=aaabc p=a*aaabc p字符可以跳过当前两位，继续和被匹配字符当前位置匹配
     *  3.3然后从s当前x位置开始匹配 表示p下一位*可以匹配1-x个字符 匹配条件匹配的字符都为相等或者 p当前字符为。符号
     *  3.4其他条件则为false
     * 4.下一位不是*字符 要么是字符要么是。 只匹配当前位置
     *  4.1 如果当前位置字符匹配 或者匹配字符是。 则当前位置匹配成功 然后继续下一个位置字符匹配
     * @param s   原字符
     * @param x   x位置
     * @param p   匹配字符
     * @param y   p位置
     * @return
     */
    private boolean process(char[] s, int x, char[] p, int y) {
        if (y == p.length) {
            return x == s.length;
        }
        if (x == s.length) {
            if (y + 1 < p.length && p[y + 1] == '*') {
                return process(s, x, p, y + 2);
            }
            return false;
        }
        // 下一位一定是*
        if (y + 1 < p.length && p[y + 1] == '*')  {
            if (s[x] != p[y] && p[y] != '.') {
                // 如果当前位置s和p不匹配并且p当前不是. y跳到*后一位置
                return process(s, x, p, y + 2);
            }
            // 下面是匹配 但是可能也存在*需要前面字符是0个的情况
            // 在匹配的情况下 需要0个*前字符 s=aaabc p=a*aaabc 此时*需要0个a 后面继续匹配
            if (process(s, x, p, y + 2)) {
                return true;
            }
            // 需要1，2，。。 有一个匹配则表示成功个*前字符
            // s=aaabc p=a*aabc，s=aaabc p=a*abc，s=aaabc p=a*bc 此时*需要0个a 后面继续匹配
            while (x < s.length && (s[x] == p[y] || p[y] == '.')) {
                if (process(s, x + 1, p, y + 2)) {
                    return true;
                }
                x++;
            }
            return false;
        } else {
            return (s[x] == p[y] || p[y] == '.') && process(s, x + 1, p, y + 1);
        }
    }
    
    // 以下的代码是课后的优化
    // 有代码逻辑精简的优化，还包含一个重要的枚举行为优化
    // 请先理解课上的内容
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return isValid(str, pattern) && process2(str, 0, pattern, 0);
    }
    
    // 举例说明枚举行为优化
    // 求状态(x = 3, y = 7)时，假设状况如下
    // s  : a a a b ...
    // x  : 3 4 5 6 ...
    // pat: a * ? ...
    // y  : 7 8 9 ...
    // 状态(x = 3, y = 7)会依赖：
    //   状态(x = 3, y = 9)
    //   状态(x = 4, y = 9)
    //   状态(x = 5, y = 9)
    //   状态(x = 6, y = 9)
    //
    // 求状态(x = 2, y = 7)时，假设状况如下
    // s  : a a a a b ...
    // x  : 2 3 4 5 6 ...
    // pat: a * ? ...
    // y  : 7 8 9 ...
    // 状态(x = 2, y = 7)会依赖：
    //   状态(x = 2, y = 9)
    //   状态(x = 3, y = 9)
    //   状态(x = 4, y = 9)
    //   状态(x = 5, y = 9)
    //   状态(x = 6, y = 9)
    //
    // 注意看状态(x = 2, y = 7)依赖的后4个，其实就是状态(x = 3, y = 7)
    // 所以状态(x = 2, y = 7)的依赖可以化简为：
    //   状态(x = 2, y = 9)
    //   状态(x = 3, y = 7)
    // 这样枚举行为就被化简成了有限两个状态，详细情况看代码
    public static boolean process2(char[] s, int x, char[] p, int y) {
        // 字符str走完 并且pattern走完 表示匹配成功
        if (x == s.length && y == p.length) {
            return true;
        }
        // 字符str走完 并且pattern没有走完 查看剩余pattern是否是每隔一个字符有一个*直到结束
        if (x == s.length) {
            return (y + 1 < p.length && p[y + 1] == '*') && process2(s, x, p, y + 2);
        }
        // pattern走完并且str没有走完 直接返回false
        if (y == p.length) {
            return false;
        }
        // 当前位置来到最后一个字符位置 或者下一位不是* 在当前位置匹配的情况下  然后匹配下一字符
        if (y + 1 >= p.length || p[y + 1] != '*') {
            return ((s[x] == p[y]) || (p[y] == '.')) && process2(s, x + 1, p, y + 1);
        }
        // 此时p的y+1位一定是*
        // 此处为枚举行为优化，含义看函数注释
        // 此时表示当前位置匹配 s走下位置匹配 如果不匹配成功 则走下面 y直接跳*下一位置
        if ((s[x] == p[y] || p[y] == '.') && process2(s, x + 1, p, y)) {
            return true;
        }
        // 此时*表示0字符
        return process2(s, x, p, y + 2);
    }
    
    public boolean isValid(char[] str, char[] pattern) {
        // 原字符不会有.和*特殊符号
        for (char cha : str) {
            if (cha == '.' || cha == '*') {
                return false;
            }
        }
        for (int i = 0; i < pattern.length; i++) {
            // 开头位置不能为* 不能连续*
            if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isMatchDp2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int x = s.length();
        int y = p.length();
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (!isValid(sChars, pChars)) {
            return false;
        }
        boolean[][] dp = new boolean[x + 1][y + 1];
        dp[x][y] = true;
        for (int j = y - 2; j >= 0; j--) {
            dp[x][j] = pChars[j + 1] == '*' && dp[x][j + 2];
        }
        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                if (j + 1 >= y || pChars[j + 1] != '*') {
                    dp[i][j] = ((sChars[i] == pChars[j]) || (pChars[j] == '.')) && dp[i + 1][j + 1];
                    continue;
                }
                if ((sChars[i] == pChars[j] || pChars[j] == '.') && dp[i + 1][j]) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i][j + 2];
                }
            }
        }
        return dp[0][0];
    }
    
    public boolean isMatchDp3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (!isValid(sChars, pChars)) {
            return false;
        }
        int N = sChars.length;
        int M = pChars.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int j = M - 1; j >= 0; j--) {
            dp[N][j] = (j + 1 < M && pChars[j + 1] == '*') && dp[N][j + 2];
        }
        // dp[0..N-2][M-1]都等于false，只有dp[N-1][M-1]需要讨论
        if (N > 0 && M > 0) {
            dp[N - 1][M - 1] = (sChars[N - 1] == pChars[M - 1] || pChars[M - 1] == '.');
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (pChars[j + 1] != '*') {
                    dp[i][j] = ((sChars[i] == pChars[j]) || (pChars[j] == '.')) && dp[i + 1][j + 1];
                } else {
                    if ((sChars[i] == pChars[j] || pChars[j] == '.') && dp[i + 1][j]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
        DpRegularExpressionMatching dpRegularExpressionMatching = new DpRegularExpressionMatching();
        String s = "abccc";
        String p = ".*c";
        System.out.println(dpRegularExpressionMatching.isMatch(s, p));
        System.out.println(dpRegularExpressionMatching.isMatch2(s, p));
        System.out.println(dpRegularExpressionMatching.isMatchDp2(s, p));
        System.out.println(dpRegularExpressionMatching.isMatchDp3(s, p));
    }
}
