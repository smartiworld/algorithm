package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.6.2 给定表达式多少种计算方式可以得到目标值
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。
 * 返回express能有多少种组合 方式，可以达到desired的结果。
 * 【举例】
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false
 * 无组合则可以得到false，返回0
 * @date 2024/1/22 19:20
 */
public class ExpressComposeCalcToAim {
    
    
    public static int kindComposeToAim(String express, boolean desired) {
        char[] chars = express.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        return process(chars, desired ? 1 : 0, 0, chars.length - 1);
    }
    
    /**
     * 遍历符号位 当来到i位置时 左半部分l~i-1位置 可以得到期望值个数乘 右半部i+1~r可以得到期望值个数
     * 期望值和当前传入期望值和运算富号相关
     *   原期望值为true
     *      当前符号为& 则左右半部期望值都需要为true 才能得到原期望值 结果为f(l, i-1,true)*f(i+1,r,true)
     *      当前符号为| 则左右半部期望值有一个为true 才能得到原期望值 结果为f(l, i-1,true)*f(i+1,r,true)+f(l, i-1,true)*f(i+1,r,false)+f(l, i-1,false)*f(i+1,r,true)
     *      当前符号为^ 则左右半部期望值相反的时候 才能得到原期望值 结果为f(l, i-1,true)*f(i+1,r,false)+f(l, i-1,false)*f(i+1,r,true)
     *   原期望值为false的时候
     *      当前符号为& 则左右半部期望值有一个为false 才能得到原期望值 结果为f(l, i-1,false)*f(i+1,r,false)+f(l, i-1,true)*f(i+1,r,false)+f(l, i-1,false)*f(i+1,r,true)
     *      当前符号为| 则左右半部期望值都为false 才能得到原期望值 结果为f(l, i-1,false)*f(i+1,r,false)
     *      当前符号为^ 则左右半部期望值相同的时候 才能得到原期望值 结果为f(l, i-1,true)*f(i+1,r,true)+f(l, i-1,false)*f(i+1,r,false)
     * @param chars
     * @param desired
     * @param l
     * @param r
     * @return
     */
    private static int process(char[] chars, int desired, int l, int r) {
        if (l == r) {
            return chars[l] - 48 == desired ? 1 : 0;
        }
        int ans = 0;
        for (int i = l + 1; i < r; i = i + 2) {
            if (desired == 1) {
                if (chars[i] == '&') {
                    ans += process(chars, 1, l, i - 1) * process(chars, 1, i + 1, r);
                }
                if (chars[i] == '|') {
                    ans += process(chars, 1, l, i - 1) * process(chars, 1, i + 1, r);
                    ans += process(chars, 1, l, i - 1) * process(chars, 0, i + 1, r);
                    ans += process(chars, 0, l, i - 1) * process(chars, 1, i + 1, r);
                }
                if (chars[i] == '^') {
                    ans += process(chars, 1, l, i - 1) * process(chars, 0, i + 1, r);
                    ans += process(chars, 0, l, i - 1) * process(chars, 1, i + 1, r);
                }
            } else {
                if (chars[i] == '&') {
                    ans += process(chars, 0, l, i - 1) * process(chars, 0, i + 1, r);
                    ans += process(chars, 1, l, i - 1) * process(chars, 0, i + 1, r);
                    ans += process(chars, 0, l, i - 1) * process(chars, 1, i + 1, r);
                }
                if (chars[i] == '|') {
                    ans += process(chars, 0, l, i - 1) * process(chars, 0, i + 1, r);
                }
                if (chars[i] == '^') {
                    ans += process(chars, 0, l, i - 1) * process(chars, 0, i + 1, r);
                    ans += process(chars, 1, l, i - 1) * process(chars, 1, i + 1, r);
                }
            }
            
        }
        return ans;
    }
    
    public static int kindComposeToAimDp(String express, boolean desired) {
        if (express == null || "".equals(express)) {
            return 0;
        }
        char[] chars = express.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        int len = chars.length;
        // 为true的缓存
        int[][] tDp = new int[len][len];
        // 为false的缓存
        int[][] fDp = new int[len][len];
        // 遍历每个数字位置 1如果为true数组则为1 否则0， false数组相反
        for (int i = 0; i < len; i++) {
            tDp[i][i] = chars[i] == '1' ? 1 : 0;
            fDp[i][i] = chars[i] == '0' ? 1 : 0;
        }
        // 行列处理数字位置 范围从row开始到col
        for (int row = len - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < len; col += 2) {
                // 遍历区间范围上 运算符
                for (int i = row + 1; i < col; i += 2) {
                    if (chars[i] == '&') {
                        tDp[row][col] += tDp[row][i - 1] * tDp[i + 1][col];
                        fDp[row][col] += fDp[row][i - 1] * fDp[i + 1][col];
                        fDp[row][col] += tDp[row][i - 1] * fDp[i + 1][col];
                        fDp[row][col] += fDp[row][i - 1] * tDp[i + 1][col];
                    }
                    if (chars[i] == '|') {
                        tDp[row][col] += tDp[row][i - 1] * tDp[i + 1][col];
                        tDp[row][col] += tDp[row][i - 1] * fDp[i + 1][col];
                        tDp[row][col] += fDp[row][i - 1] * tDp[i + 1][col];
                        fDp[row][col] += fDp[row][i - 1] * fDp[i + 1][col];
                    }
                    if (chars[i] == '^') {
                        tDp[row][col] += tDp[row][i - 1] * fDp[i + 1][col];
                        tDp[row][col] += fDp[row][i - 1] * tDp[i + 1][col];
                        fDp[row][col] += fDp[row][i - 1] * fDp[i + 1][col];
                        fDp[row][col] += tDp[row][i - 1] * tDp[i + 1][col];
                    }
                }
            }
        }
        return desired ? tDp[0][len - 1] : fDp[0][len - 1];
    }
    
    public static int num2(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;
        for (int i = 2; i < exp.length; i += 2) {
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }
    
    public static void main(String[] args) {
        String express="1^0|0|1";
        boolean desired=false;
        int i = kindComposeToAim(express, desired);
        System.out.println(i);
        int kindComposeToAimDp = kindComposeToAimDp(express, desired);
        System.out.println(kindComposeToAimDp);
    }
    
    public static boolean isValid(char[] exp) {
        if ((exp.length & 1) == 0) {
            return false;
        }
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }
    
    public static int num1(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        return f(exp, desired, 0, exp.length - 1);
    }
    
    // str[L..R] 返回期待为desired的方法数
    // 潜台词：L R 必须是偶数位置
    public static int f(char[] str, boolean desired, int L, int R) {
        if (L == R) { // base case 1
            if (str[L] == '1') {
                return desired ? 1 : 0;
            } else { // '0'
                return desired ? 0 : 1;
            }
        }
        
        // L..R
        int res = 0;
        if (desired) { // 期待为true
            // i位置尝试L..R范围上的每一个逻辑符号，都是最后结合的
            for (int i = L + 1; i < R; i += 2) {
                // exp[i] 一定压中逻辑符号
                switch (str[i]) {
                    case '&':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '|':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        break;
                }
            }
        } else { // 期待为false
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '|':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }
}
