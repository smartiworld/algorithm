package com.iworld.algorithm;

/**
 * 最小顺序子串
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有顺序字符的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANCBC", T = "ABC"
 * 输出: "ANCBC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * @Autor iworld
 * @Date 2020-07-04 16:59
 */
public class FindMixOrderSubStr {

    public static void main(String[] args) {
        FindMixOrderSubStr matchMixOrderSubStr = new FindMixOrderSubStr();
        String minSubStr = matchMixOrderSubStr.minSubStrOpt("ADOBEACBCODEBANCBC", "ABC");
        System.out.println("minSubStr==" + minSubStr);
    }

    /**
     * 两层循环，内层控制当前匹配字符串后依次取出包含当前子字符串的字符串，外层循环控制最小字符串，导致多次无用循环
     * minLenPositions记录当前位置前多少个字符是包含当前子字符串的字符串。
     * @param s 目标字符串
     * @param t 子字符串
     * @return 返回目标字符串包含子字符串的最小字符串
     */
    public String minSubStr(String s, String t){
        String result = "";
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] minLenPositions = new int[s.length()];
        for (int x = 0; x <= s.length() - t.length(); x++) {
            int tp = 0;
            int start = 0;
            innerLop : for (int i = x; i <= s.length() - t.length() + tp; i++) {
                char ci = cs[i];
                char ctp = ct[tp];
                if(ci == ctp){
                    if(tp == 0){
                        start = i;
                    }
                    if(tp == t.length() - 1){
                        minLenPositions[i] = minLenPositions[i] == 0? i - start + 1 : Math.min(minLenPositions[i], i - start + 1);
                        break innerLop;
                    }
                    tp ++;
                }
            }
        }
        int minLen = -1;
        int minLenPosition = -1;
        for (int i = 0; i < minLenPositions.length; i++) {
            if(minLenPositions[i] > 0 && minLen < 0){
                minLen = minLenPositions[i];
                minLenPosition = i;
            }
            if(minLenPositions[i] > 0 && minLenPositions[i] < minLen){
                minLen = minLenPositions[i];
                minLenPosition = i;
            }
        }
        if(minLen > 0){
            result = new String(cs,minLenPosition - minLen + 1, minLen);
        }
        return result;
    }

    /**
     * 动态变化初始位置 减少非必要循环
     * @param s 目标字符串
     * @param t 子字符串
     * @return 返回目标字符串包含子字符串的最小字符串
     */
    public String minSubStrOpt(String s, String t){
        String result = "";
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] minLenPositions = new int[s.length()];
        outLop:for (int x = 0; x <= s.length() - t.length(); x++) {
            int tp = 0;
            int start = 0;
            int xp = -1;
            innerLop : for (int i = x; i <= s.length() - t.length() + tp; i++) {
                char ci = cs[i];
                char ctp = ct[tp];
                if(tp > 0 && ct[0] == ci){
                    xp = i;
                    continue innerLop;
                }
                if(ci == ctp){
                    if(tp == 0){
                        start = i;
                    }
                    if(tp == t.length() - 1){
                        minLenPositions[i] = minLenPositions[i] == 0? i - start + 1 : Math.min(minLenPositions[i], i - start + 1);
                        if(i == s.length() -1){
                            break outLop;
                        }
                        if(xp == -1){
                            x = i;
                        }
                        break innerLop;
                    }
                    tp ++;
                }
            }
            if(xp > -1){
                x = xp -1;
            }
        }
        int minLen = -1;
        int minLenPosition = -1;
        for (int i = 0; i < minLenPositions.length; i++) {
            if(minLenPositions[i] > 0 && minLen < 0){
                minLen = minLenPositions[i];
                minLenPosition = i;
            }
            if(minLenPositions[i] > 0 && minLenPositions[i] < minLen){
                minLen = minLenPositions[i];
                minLenPosition = i;
            }
        }
        if(minLen > 0){
            result = new String(cs,minLenPosition - minLen + 1, minLen);
        }
        return result;
    }

    /**
     * 优化循环
     * @param s 目标字符串
     * @param t 子字符串
     * @return 返回目标字符串包含子字符串的最小字符串
     */
    public String minSubStrOptSimLop(String s, String t){
        String result = "";
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] minLenPositions = new int[s.length()];
        int tp = 0;
        int start = 0;
        for (int i = 0; i <= s.length() - t.length() + tp; i++) {
            char ci = cs[i];
            char ctp = ct[tp];
            if(tp > 0 && ct[0] == ci){
                start = i;
                continue ;
            }
            if(ci == ctp){
                if(tp == 0){
                    start = i;
                }
                if(tp == t.length() - 1){
                    minLenPositions[i] = minLenPositions[i] == 0? i - start + 1 : Math.min(minLenPositions[i], i - start + 1);
                    tp = 0;
                    start = 0;
                    continue;
                }
                tp ++;
            }
        }
        int minLen = -1;
        int minLenPosition = -1;
        for (int i = 0; i < minLenPositions.length; i++) {
            if(minLenPositions[i] > 0 && minLen < 0){
                minLen = minLenPositions[i];
                minLenPosition = i;
            }
            if(minLenPositions[i] > 0 && minLenPositions[i] < minLen){
                minLen = minLenPositions[i];
                minLenPosition = i;
            }
        }
        if(minLen > 0){
            result = new String(cs,minLenPosition - minLen + 1, minLen);
        }
        return result;
    }
}
