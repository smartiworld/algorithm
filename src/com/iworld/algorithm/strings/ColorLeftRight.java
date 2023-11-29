package com.iworld.algorithm.strings;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.1.4
 * 一个字符串 每个字符代表一个正方形，每个正方形已经被染成红色或者绿色
 * 现在可以选择任意一个正方形用这两种颜色对字符串进行染色
 * 目标是完成染色之后每个红色R都比每个绿色G距离最左侧近 返回最少需要染色几个正方形
 * 例如s=RGRGR 染色后变成RRRGG 染的个数未2  最少的染色方案
 * https://github.com/algorithmzuo/trainingcamp003/blob/master/src/class01/Code04_ColorLeftRight.java
 * @date 2022/12/4 22:57
 */
public class ColorLeftRight {
    
    public int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        char[] chars = s.toCharArray();
        int n = chars.length;
        int rCount = 0;
        for (int i = 0; i < n; i++) {
            rCount += chars[i] == 'R' ? 1 : 0;
        }
        // 初始默认数组所有的R都变成G
        int min = rCount;
        int gCount = 0;
        // 左边的G变成R 和右边R变成G的和 求最小
        for (int i = 0; i < n - 1; i++) {
            gCount += chars[i] == 'G' ? 1 : 0;
            rCount -= chars[i] == 'R' ? 1 : 0;
            min = Math.min(min, rCount + gCount);
        }
        // 来到最后位置 所有的G变成R
        min = Math.min(min, gCount + (chars[n - 1] == 'G' ? 1 : 0));
        return min;
    }
    
    public int minPaint2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        char[] chars = s.toCharArray();
        int n = chars.length;
        int rCount = 0;
        for (int i = 0; i < n; i++) {
            rCount += chars[i] == 'R' ? 1 : 0;
        }
        // 初始默认数组所有的R都变成G
        int min = rCount;
        int gCount = 0;
        for (int i = 0; i < n; i++) {
            gCount += chars[i] == 'G' ? 1 : 0;
            rCount -= chars[i] == 'R' ? 1 : 0;
            min = Math.min(min, rCount + gCount);
        }
        return min;
    }
    
    public static void main(String[] args) {
        ColorLeftRight colorLeftRight = new ColorLeftRight();
        int times = 1000;
        for (int i = 0; i < times; i++) {
            String s = randomGeneratorStr(20);
            System.out.println(s);
            int x = colorLeftRight.minPaint(s);
            int x1 = colorLeftRight.minPaint2(s);
            if (x != x1) {
                System.out.println("x===" + x + "------x1===" + x1);
            }
        }
        
    }
    
    public static String randomGeneratorStr(int len) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (Math.random() < 0.5) ? 'R' : 'G';
        }
        return new String(chars);
    }
}
