package com.iworld.algorithm.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.1.5 282. Expression Add Operators
 * 给定一个只由'0'~'9'字符组成的字符串num，和整数target。可以用+、-和*连接，返回num得到target的所有不同方法。
 * Example 1:
 * Input: num = "123", target = 6 Output: ["1+2+3", "1*2*3"]
 * Example 2:
 * Input: num = "232", target = 8 Output: ["2*3+2", "2+3*2"]
 * Example 3:
 * Input: num = "105", target = 5 Output: ["1*0+5","10-5"]
 * Example 4:
 * Input: num = "00", target = 0 Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 * Input: num = "3456237490", target = 9191 Output: []
 *
 * Hard
 * 2.9K
 * 520
 * Companies
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-',
 * and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -2^31 <= target <= 2^31 - 1
 * https://leetcode.com/problems/expression-add-operators/
 * @date 2023/3/15 16:04
 */
public class ExpressionAddOperators {
    
    /**
     * 出现字符就结算的处理
     * 或者运算符优先级相同
     * @param num
     * @param target
     * @return
     */
    public List<String> getOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num == null) {
            return ans;
        }
        char[] chars = num.toCharArray();
        process(chars, 0, 0, target, new StringBuilder(), ans, null);
        return ans;
    }
    
    public void process(char[] chars, int index, int total, int target, StringBuilder sb, List<String> ans, String operator) {
        int cur = 0;
        for (int i = index; i < chars.length; i++) {
            cur = cur * 10 + (chars[i] - '0');
            if ("*".equals(operator)) {
                total = total * cur;
                sb.append("*");
            } else if ("+".equals(operator)){
                sb.append("+");
                total = total + cur;
            } else if ("-".equals(operator)) {
                sb.append("-");
                total = total - cur;
            } else if ("/".equals(operator)) {
                if (cur == 0) {
                    if (i != index) {
                        continue;
                    } else {
                        break;
                    }
                }
                sb.append("/");
                total = total / cur;
            } else {
                total = cur;
            }
            sb.append(cur);
            if (i + 1 == chars.length) {
                if (total == target) {
                    ans.add(sb.toString());
                }
                sb.delete(sb.length() - (i - index + 1), sb.length());
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                return ;
            }
            process(chars, i + 1, total, target, sb, ans, "+");
            process(chars, i + 1, total, target, sb, ans, "-");
            process(chars, i + 1, total, target, sb, ans, "*");
            process(chars, i + 1, total, target, sb, ans, "/");
            sb.delete(sb.length() - (i - index + 1), sb.length());
            if (sb.length() > 0) {
                sb.delete(sb.length() - 1, sb.length());
            }
            if (i == index && chars[i] - '0' == 0) {
                break;
            }
        }
    }
    
    /**
     * 按照字符串优先级
     * @param num
     * @param target
     * @return
     */
    public List<String> getOperatorsPriority(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num == null) {
            return ans;
        }
        char[] chars = num.toCharArray();
        process(chars, 0, target, "", ans, null);
        return ans;
    }
    
    public void process(char[] chars, int index, int target, String path, List<String> ans, String operator) {
        int cur = 0;
        for (int i = index; i < chars.length; i++) {
            if (cur >= Integer.MAX_VALUE / 10 && (chars[i] - '0') > 7) {
                return ;
            }
            cur = cur * 10 + (chars[i] - '0');
            String curPath = path;
            if ("/".equals(operator)) {
                if (cur == 0) {
                    if (i != index) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
            if (operator == null) {
                curPath = Integer.toString(cur);
            } else {
                curPath += operator;
                curPath += cur;
            }
            if (i + 1 == chars.length) {
                if (check(curPath, target)) {
                    ans.add(curPath);
                }
                return ;
            }
            process(chars, i + 1, target, curPath, ans, "+");
            process(chars, i + 1, target, curPath, ans, "-");
            process(chars, i + 1, target, curPath, ans, "*");
            //process(chars, i + 1, target, curPath, ans, "/");
            if (i == index && chars[i] - '0' == 0) {
                break;
            }
        }
        
    }
    
    private boolean check(String path, int target) {
        char[] chars = path.toCharArray();
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                cur = cur * 10 + (chars[i] - '0');
            } else {
                if (!put(queue, cur)) {
                    return false;
                }
                queue.addFirst(String.valueOf(chars[i]));
                cur = 0;
            }
        }
        if (!put(queue, cur)) {
            return false;
        }
        long calcQueue = calcQueue(queue);
        if (calcQueue == target) {
            return true;
        }
        return false;
    }
    
    private boolean put(LinkedList<String> queue, int value) {
        String putValue = Integer.toString(value);
        if (!queue.isEmpty()) {
            String peekFirst = queue.peekFirst();
            if ("*".equals(peekFirst)) {
                queue.pollFirst();
                String pre = queue.pollFirst();
                if (pre != null) {
                    long calc = Long.parseLong(pre) * value;
                    if (calc > Integer.MAX_VALUE) {
                        return false;
                    }
                    putValue = Long.toString(calc);
                }
                
            } else if ("/".equals(peekFirst)) {
                if (value == 0) {
                    return false;
                }
                queue.pollFirst();
                String pre = queue.pollFirst();
                if (pre != null) {
                    int calc = Integer.parseInt(pre) / value;
                    putValue = Integer.toString(calc);
                }
            }
        }
        queue.addFirst(putValue);
        return true;
    }
    
    private long calcQueue(LinkedList<String> queue) {
        long ans = 0;
        while (!queue.isEmpty()) {
            String poll = queue.pollLast();
            if ("+".equals(poll)) {
                ans += Long.parseLong(queue.pollLast());
            } else if ("-".equals(poll)){
                ans -= Long.parseLong(queue.pollLast());
            } else {
                ans = Long.parseLong(poll);
            }
        }
        return ans;
    }
    
    public int ways(String num, int target) {
        char[] str = num.toCharArray();
        int first = str[0] - '0';
        return f(str, 1, 0, first, target);
    }
    
    public int f(char[] str, int index, int left, int cur, int target) {
        if (index == str.length) {
            return (left + cur) == target ? 1 : 0;
        }
        int ways = 0;
        int num = str[index]-'0';
        // 第一个决定 +
        ways += f(str, index+1, left + cur, num, target);
        // 第二个决定-
        ways += f(str, index+1, left + cur, -num, target);
        // 第三个决定*
        ways += f(str, index+1, left, cur * num , target);
        // 第四个决定不加符号
        if(cur != 0) {
            if(cur > 0) {
                ways += f(str, index+1, left, cur * 10 + num , target);
            }else {
                ways += f(str, index+1, left, cur * 10 - num , target);
            }
        }
        return ways;
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> ret = new LinkedList<>();
        if (num.length() == 0) {
            return ret;
        }
        // 沿途的数字拷贝和+ - * 的决定，放在path里
        char[] path = new char[num.length() * 2 - 1];
        // num -> char[]
        char[] digits = num.toCharArray();
        
        long n = 0;
        for (int i = 0; i < digits.length; i++) { // 尝试0~i前缀作为第一部分
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            dfs(ret, path, i + 1, 0, n, digits, i + 1, target); // 后续过程
            if (n == 0) {
                break;
            }
        }
        return ret;
    }
    
    // char[] digits 固定参数，字符类型数组，等同于num
    // int target 目标
    // char[] path 之前做的决定，已经从左往右依次填写的字符在其中，可能含有'0'~'9' 与 * - +
    // int len path[0..len-1]已经填写好，len是终止
    // int pos 字符类型数组num, 使用到了哪
    // left -> 前面固定的部分 cur -> 前一块
    // 默认 left + cur ...
    public void dfs(List<String> res, char[] path, int len,
    
                           long left, // 已经结算的部分
    
                           long cur, // 待定的部分(上一块的值)
    
                           char[] num, int index,
    
                           int aim) {
        if (index == num.length) {
            if (left + cur == aim) {
                res.add(new String(path, 0, len));
            }
            return;
        }
        // num中的index还没结束
        long n = 0;
        int j = len + 1;
        for (int i = index; i < num.length; i++) { // pos ~ i
            n = n * 10 + num[i] - '0';
            path[j++] = num[i];
            path[len] = '+';
            dfs(res, path, j, left + cur, n, num, i + 1, aim);
            path[len] = '-';
            dfs(res, path, j, left + cur, -n, num, i + 1, aim);
            path[len] = '*';
            dfs(res, path, j, left, cur * n, num, i + 1, aim);
            if (num[index] == '0') {
                break;
            }
        }
    }
    
    
    public static void main(String[] args) {
        String num = "232";
        int target = 8;
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        System.out.println(expressionAddOperators.getOperators(num, target));
        System.out.println(expressionAddOperators.getOperatorsPriority(num, target));
        System.out.println(expressionAddOperators.ways(num, target));
        System.out.println(expressionAddOperators.addOperators(num, target));
    }
}
