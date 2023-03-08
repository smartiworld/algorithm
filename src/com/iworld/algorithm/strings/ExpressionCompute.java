package com.iworld.algorithm.strings;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个字符串str，str表示一个公式，公式里面可能有数字、加减乘除和左右括号，返回公式计算结果
 * 例1：3+1*5 结果返回8
 * 例2：3+2*（1+3） 结果返回11
 * 可以认为公式是合法有效公式 如果是负数并且不在开头位置就需要用括号括起来
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class05/Code03_ExpressionCompute.java
 * @date 2023/3/7 17:44
 */
public class ExpressionCompute {
    
    public int calcExpression(String expression) {
        if (expression == null || expression.length() < 1) {
            return 0;
        }
        char[] chars = expression.toCharArray();
        return process(chars, 0)[0];
    }
    
    /**
     * 返回数组第一个位置为当前串计算的记过 第二个位置为计算当前结果来到了哪个位置
     * @param chars
     * @return
     */
    private int[] process(char[] chars, int index) {
        LinkedList<String> stack = new LinkedList<>();
        int curNum = 0;
        int i = index;
        for (; i < chars.length; i++) {
            if (chars[i] == ')') {
                break;
            }
            if (chars[i] >= '0' && chars[i] <= '9') {
                curNum = curNum * 10 + (chars[i] - '0');
            } else if (chars[i] != '(') {
                // 遇到了运算符号
                pushValue(stack, curNum);
                stack.push(String.valueOf(chars[i]));
                curNum = 0;
            } else {
                int[] process = process(chars, i + 1);
                curNum = process[0];
                i = process[1];
            }
        }
        pushValue(stack, curNum);
        int calc = calc(stack);
        return new int[]{calc, i};
    }
    
    /**
     * 放入数字
     * @param stack
     * @param value
     */
    private void pushValue(LinkedList<String> stack, int value) {
        String putValue = String.valueOf(value);
        if (!stack.isEmpty()) {
            String pop = stack.peek();
            if ("*".equals(pop) || "/".equals(pop)) {
                pop = stack.pop();
                BigDecimal preValue = new BigDecimal(stack.pop());
                if ("*".equals(pop)) {
                    putValue = preValue.multiply(BigDecimal.valueOf(value)).toString();
                } else {
                    putValue = preValue.divide(BigDecimal.valueOf(value), 0, RoundingMode.CEILING).toString();
                }
            }
        }
        stack.push(putValue);
    }
    
    private int calc(LinkedList<String> stack) {
        int ans = 0;
        while (!stack.isEmpty()) {
            String pop = stack.pollLast();
            if ("+".equals(pop)) {
                String calcValue = stack.pollLast();
                ans += Integer.parseInt(calcValue);
            } else if ("-".equals(pop)) {
                String calcValue = stack.pollLast();
                ans -= Integer.parseInt(calcValue);
            } else {
                ans = Integer.parseInt(pop);
            }
        }
        return ans;
    }
    
    public static int getValue(String str) {
        return value(str.toCharArray(), 0)[0];
    }
    
    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] value(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int cur = 0;
        int[] bra = null;
        // 从i出发，开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                bra = value(str, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[] { getNum(que), i };
    }
    
    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }
    
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        String expression = "48*((70-65)-43)+8*1";
        ExpressionCompute expressionCompute = new ExpressionCompute();
        System.out.println(expressionCompute.calcExpression(expression));
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));
        System.out.println(expressionCompute.calcExpression(exp));
        System.out.println("================");
        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));
        System.out.println(expressionCompute.calcExpression(exp));
        System.out.println("================");
        exp = "10-5*3";
        System.out.println(getValue(exp));
        System.out.println(expressionCompute.calcExpression(exp));
        System.out.println("================");
        exp = "-3*4";
        System.out.println(getValue(exp));
        System.out.println(expressionCompute.calcExpression(exp));
        System.out.println("================");
        exp = "3+1*4";
        System.out.println(getValue(exp));
        System.out.println(expressionCompute.calcExpression(exp));
    }
    
    
    
}
