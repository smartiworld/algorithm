package com.iworld.algorithm.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 6.Z字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * P       H
 * A     S I
 * Y   I   R
 * P L     I G
 * A       N
 *
 * P         R
 * A       I I
 * Y     H   N
 * P   S     G
 * A I       H
 * L         A
 *  
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * 链接：https://leetcode.cn/problems/zigzag-conversion
 * @date 2022/5/28 18:57
 */
public class ZString {
    
    public String convert(String s, int numRows) {
        if (s == null || s.length() <= numRows || numRows == 1) {
            return s;
        }
        int row = 0;
        StringBuilder sb = new StringBuilder();
        while (row < numRows) {
            int index = row;
            while (index < s.length()) {
                sb.append(s.charAt(index));
                // 添加斜线位置字符 只有在不是第一行和不是最后一行的时候存在斜线位置
                // 其他情况满足 index = index + (2 * (numRows - row) - 2);
                if (row != 0 && row != numRows - 1 && index + 2 * (numRows - row - 1) < s.length()) {
                    sb.append(s.charAt(index + 2 * (numRows - row - 1)));
                }
                // 跳到下一个位置
                index = index + (2 * numRows - 2);
            }
            row ++;
        }
        return sb.toString();
    }
    
    public String convert2(String s, int numRows) {
        if (s == null || s.length() <= numRows || numRows == 1) {
            return s;
        }
        //法二：归纳分组法
        StringBuilder sb = new StringBuilder();
        // 按层次进行遍历，每一层元素个数应为2*n-2;
        for(int j = 0; j < numRows; j++){
            for(int i = j; i < s.length(); i = i + 2 * numRows - 2){
                sb.append(s.charAt(i));
                //排除第一层和第n层还有数组越界的情况
                if( j != 0 && j != numRows - 1 && i + 2 * (numRows - 1 - j) < s.length()) {
                    sb.append(s.charAt(i + 2 * (numRows - 1 - j)));
                }
            }
        }
        return sb.toString();
    
    }
    
    public String convert3(String s, int numRows) {
        if (s == null || s.length() <= numRows|| numRows == 1) {
            return s;
        }
        // 每层一个StringBuilder 添加每层字符
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        // index 移动的层数 tmp当前要走到上一层还是下一层 正数为向下走一层 负数为向上走一层
        int index = 0, tmp = 1;
        for (int i = 0; i < s.length(); i++) {
            list.get(index).append(s.charAt(i));
            index += tmp;
            // 当来到最后一层或者是第一层时 需要到上一个位置
            if (index == numRows || index == -1) {
                // 转到前一位置
                index = index - tmp - tmp;
                // 转变方向
                tmp = -tmp;
            }
        }
        StringBuilder sb = new StringBuilder();
        
        return list.stream().reduce(StringBuilder::append).get().toString();
    }
    
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        ZString zString = new ZString();
        System.out.println(zString.convert2(s, numRows));
        System.out.println(zString.convert3(s, numRows));
        System.out.println(zString.convert(s, numRows));
    }
    
}
