package com.iworld.algorithm.manacher;

/**
 * @author gq.cai
 * @version 1.0
 * @description manache 马拉车
 * manache 算法处理回文串
 * 字符必须经过处理 每字符中间填充相同字符
 * 如原字符aa123321转换
 * 回文半径r 为当前字符左右长度是回文
 * 回文中心 以当前字符左右回文对称
 * L 回文串C(R和L以C对称) R
 * 1.如果i在R外 直接对比以i为中心对称字符是否相同
 * 2.i在R内，通过回文中心找到i的对称点 j
 *      1.j的回文半径未超过L 此时i的回文半径和i对称点回文半径相同
 *      [  x j区间 y   c    k i区间 t  ]
 *      x关于c点和t对称 x==t y关于c点和k对称 y==k、
 *      x关于j点和y对称 x==y 所以k==t 如果kt范围超过xy范围 会和之前j的回文半径冲突
 *      2.j的回文半径超过L 此时i的回文半径i-R的距离
 *      x[   j区间 y   c    k i区间   t]
 *      区间L-R关于C对称 i和j关于c点对称 y==k如果i的回文半径超过R 此时和LR区间回文半径长度冲突
 *      3.j的回文半径到L 此时i的回文半径最小到R的位置 从R继续向外扩 i-R中间不需要再验证 一定是回文
 *      [x   j区间 y   c    k i区间   t]
 *      j区间x-y x==y  j和i关于c点对称 y==k 所以i区间最小返回到R位置。
 * @date 2022/6/10 14:37
 */
public class Manacher {
    
    /**
     * 计算一个字符最大回文长度
     * 严格根据逻辑分支
     * @param str
     * @return
     */
    public static int getMaxPalindromeLen(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        // 字符中间插入其他字符
        char[] delStr = dleStr(str);
        // 区间不包含r
        int r = -1;
        // 当前回文半径的中心对称点
        int c = -1;
        int[] cache = new int[delStr.length];
        cache[0] = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < delStr.length; i++) {
            // i在r范围外
            if (i >= r) {
                // i点左右对称点x y
                int x = i - 1;
                int y = i + 1;
                while (x > -1 && y < delStr.length) {
                    if (delStr[x] != delStr[y]) {
                        break;
                    }
                    x --;
                    y ++;
                }
                // 设置i位置字符回文半径
                cache[i] = (y - x) >> 1;
            } else {
                // i在R内
                // 找到i点关于c的对称点j
                // i - c 差值 c-差值为i的对称点j
                int j = (c << 1) - i;
                // 找到r关于c的对称点
                int l = (c << 1) - r;
                // j点回文半径
                int jR = cache[j];
                // j的回文半径未超过l
                if (j - jR > l) {
                    cache[i] = cache[j];
                } else if (j - jR < l) {
                    cache[i] = r - i;
                } else {
                    int x = (i << 1) - r;
                    int y = r;
                    while (x > -1 && y < delStr.length) {
                        if (delStr[x] != delStr[y]) {
                            break;
                        }
                        x --;
                        y ++;
                    }
                    // 设置i位置字符回文半径
                    cache[i] = (y - x) >> 1;
                }
            }
            if (i + cache[i] > r) {
                r = i + cache[i];
                c = i;
            }
            max = Math.max(max, cache[i]);
        }
        return max - 1;
    }
    
    /**
     * 计算一个字符最大回文长度
     * 严格根据逻辑分支
     * @param str
     * @return
     */
    public static int getMaxPalindromeLenOpt(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        char[] delStr = dleStr(str);
        // 区间不包含r
        int r = -1;
        int c = -1;
        int[] cache = new int[delStr.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < delStr.length; i++) {
            // 首先不需要对比的位置 当前最大范围到R边界
            cache[i] = r > i ? Math.min(r - i, cache[(c << 1) - i]) : 1;
            // i加当前位置初始半径 在字符串的范围内
            while (i + cache[i] < delStr.length && i - cache[i] > -1) {
                // 如果以i点为中心 对称点相等 半径增长
                // 否则跳出循环
                if (delStr[i + cache[i]] == delStr[i - cache[i]]) {
                    cache[i] ++;
                } else {
                    break;
                }
            }
            // 如果当前位置加当前半径超过原半径位置 更新
            if (i + cache[i] > r) {
                r = i + cache[i];
                c = i;
            }
            max = Math.max(max, cache[i]);
        }
        return max - 1;
    }
    
    
    /**
     * 处理字符串 将每个字符中间添加一个#
     * @param str
     * @return
     */
    private static char[] dleStr(String str) {
        char[] chars = new char[(str.length() << 1) + 1];
        chars[0] = '#';
        int index = 1;
        for (int i = 0; i < str.length(); i++) {
            chars[index++] = str.charAt(i);
            chars[index++] = '#';
        }
        return chars;
    }
    
    public static void main(String[] args) {
        String str = "abcbactcabcbabc";
        System.out.println(getMaxPalindromeLen(str));
        System.out.println(getMaxPalindromeLenOpt(str));
    }
    
}
