package com.iworld.algorithm.util;

import java.util.Random;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2023/12/20 19:30
 */
public class SmartStringUtils {
    
    public static final char[] LOWER_CASE_LETTERS = {'a', 'b', 'c', 'd', 'e'};
    
    public static String generatorLowerCaseLetters(int len) {
        Random random = new Random();
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) (random.nextInt(26) + 97);
        }
        return new String(chars);
    }
    
    public static void main(String[] args) {
        int len = 5;
        String s = generatorLowerCaseLetters(len);
        System.out.println(s);
    }
}
