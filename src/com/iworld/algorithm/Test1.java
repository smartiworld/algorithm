package com.iworld.algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2023/7/20 11:28
 */
public class Test1 {
    // 字符串数字
    public static void main(String[] args) {
        String s = "123";
        int i = convertStr(s);
        String ss = ".*/order/kuaishou/.*";
        Pattern p = Pattern.compile(ss);
        String aid = "/action/carrctapi/originalbody/order/kuaishou/createOrder/v1";
        Matcher m = p.matcher(aid);
        
        System.out.println(m.find());
        System.out.println(aid.contains("/order/kuaishou/"));
    }
    
    public static int convertStr(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            int cur = (chars[i] - 48);
            result = result * 10 + cur;
        }
        return result;
    }
}
