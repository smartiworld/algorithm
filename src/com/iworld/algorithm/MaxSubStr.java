package com.iworld.algorithm;

/**
 * 最长有效括号
 * 最大括号子串
 * leetcode https://leetcode-cn.com/problems/longest-valid-parentheses/ 32
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * (()())
 * 第一步先从位置1，遍历找到右括号，然后找当前位置前没有匹配的左括号，如果匹配到，
 * 则将当前左右括号位置差值加上当前左括号前一位置括号最长有效括号，则为当前字符最大有效括号
 * @Autor iworld
 * @Date 2020-07-01 22:57
 */
public class MaxSubStr {

    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length -1, -1, -1, ')'));
    }
    private static int calc(char[] chars , int i ,  int flag,int end, char cTem){
        int max = 0, sum = 0, currLen = 0,validLen = 0;
        for (;i != end; i += flag) {
            sum += (chars[i] == cTem ? 1 : -1);
            currLen ++;
            if(sum < 0){
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
                validLen = 0;
            }else if(sum == 0){
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }

    public static void main(String[] args) {
        MaxSubStr maxSubStr = new MaxSubStr();
        int i = maxSubStr.longestValidParenthesesdp("(()())");
        System.out.println("i==" + i);
        int i2 = maxSubStr.longestValidParentheses("(()())");
        System.out.println("i2==" + i2);
        int i3 = maxSubStr.getMaxSub("(()())");
        System.out.println("i3==" + i3);
    }
    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public int longestValidParenthesesdp(String s) {
        int ans = 0;
        char[] cs = s.toCharArray();
        //当前位置已连续长度
        int[] ints = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            //从第二个位置遍历，匹配‘）’
            if (cs[i] == ')') {
                //匹配到右括号获取当前没有连续括号的位置
                int j = i - ints[i - 1] - 1;
                //如果当前位置大于等于0并且匹配到左括号
                if (j >= 0 && cs[j] == '(')
                    //记录当前位置连续长度 如果当前右括号位置前一位置大于0，当前匹配括号长度加右括号前位置连续括号长度，
                    //否则当前位置数据记录当前括号连续长度
                    ints[i] = (i - j + 1) + ((j - 1) >= 0 ? ints[j - 1] : 0);
            }
            ans = Math.max(ans, ints[i]);
        }
        return ans;
    }

    public int getMaxSub(String s){
        char[] cs = s.toCharArray();
        //标志当前位置前多少位是连续的
        int[] posMax = new int[s.length()];
        int maxSubRelation = 0;
        for (int i = 1; i < s.length(); i++) {
            //从第二个位置开始遍历，是否是右半步括号，如果是，则
            if(cs[i] == ')'){
                //计算前一个没有连续的（左括号的位置
                //posMax[i - 1]表示当前位置前一位置连续括号数量
                int j = i - 1 - posMax[i - 1];
                if(j >= 0 && cs[j] == '('){
                    //posMax[j - 1] 左括号前一位置连续括号长度，当前括号连续数量加上当前括号前位置连续括号字符串数量
                    posMax[i] = (i - j + 1 + (j -1 > 0? posMax[j - 1] : 0));
                }
            }
            maxSubRelation = Math.max(maxSubRelation, posMax[i]);
        }
        return maxSubRelation;
    }















}
