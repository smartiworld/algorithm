package com.iworld.algorithm.recursion;

/**
 * @author gq.cai
 * @version 1.0
 * @description N皇后
 * 在指定N*N的棋盘上摆放N个皇后 要求两个皇后不能同行同列，也不能在同斜线上
 * 给定一个整数n 返回n皇后的摆法有多少种
 * n=1返回1  n=2或者3返回0
 * @date 2022/5/9 16:47
 */
public class NQueen {
    
    public static int queenArrangement(int n) {
        if (n < 1) {
            return 0;
        }
        // 皇后放的位置 下标为行 value为列,record[2]=3 2行3列放了皇后
        // 默认不会出现共行的问题 同行出现会覆盖列位置
        int[] record = new int[n];
        return process(0, record, n);
    }
    
    /**
     * 决定在row行哪里放皇后，0--（row-1）已放好皇后位置，任何皇后不共行 不共列 不在同斜线
     * 现在到了row行位置
     * record潜台词 不会同行
     * @param row     当前来的行的位置
     * @param record  之前皇后拜访位置
     * @param n       一共多少皇后
     * @return
     */
    private static int process(int row, int[] record, int n) {
        // 终止位置 说明最后row-1位置成功摆放
        if (row == n) {
            return 1;
        }
        int result = 0;
        // 尝试row行所有的列
        for (int i = 0; i < n; i++) {
            // 校验当前行和列是否符合要求摆放皇后问题 和之前皇后不共行 不共列 不同斜线
            // 当前列不行尝试下一列 如果当前列可以 直接到下行摆放皇后
            // 下面所有行走完 会尝试当前行下一列摆放尝试
            if (validate(row, i, record)) {
                // 将当前行和列放上皇后
                record[row] = i;
                // 到下一行摆放皇后
                result += process(row + 1, record, n);
            }
        }
        return result;
    }
    
    /**
     * 校验当前行列是否可以摆放皇后
     * 是否同列和同斜线
     * (x,y),(a,b)
     * |x-a|==|y-b|同斜线条件
     * @param row     行
     * @param col     列
     * @param record
     * @return
     */
    private static boolean validate(int row, int col, int[] record) {
        for (int i = 0; i < row; i++) {
            if (record[i] == col || Math.abs(row - i) == Math.abs(col - record[i])) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * n皇后 使用位运算加速
     * @param n
     * @return
     */
    public static int queenArrangementUseBit(int n) {
        if (n < 0 || n > 32) {
            return 0;
        }
        // 几皇后取几位1表示放满n皇后的结果
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0, 0);
    }
    
    /**
     * 当前行哪些位置放皇后 1不可以放 0可以放
     * @param limit      总的限制 当前一行所有列放的皇后  1代表不可以放皇后的位置0代表可以放皇后位置 不可放皇后规模
     * @param rowLimit    当前一行哪些列放了皇后 1表示放了皇后
     * @param leftLimit   左斜线位置放皇后限制  rowLimit左移一位 表示放在rowLimit上的左下斜线不可以放皇后
     * @param rightLimit  右斜线位置放皇后限制  rowLimit右移一位 表示放在rowLimit上的左下斜线不可以放皇后
     * @return
     */
    private static int process(int limit, int rowLimit, int leftLimit, int rightLimit) {
        // 当前所有列已经都不可以放皇后
        if (rowLimit == limit) {
            return 1;
        }
        // 表示当前不可以放皇后的位置 总限制
        // 00100|01000|00010=01110 表示当前只有最左和最右0的位置可以放皇后
        int allLimit = rowLimit | leftLimit | rightLimit;
        // allLimit取反0-1，1表示哪些位置可以放皇后 int为32位此时可能会有前面不占位的1影响结果
        // ~01110=11111111|11111111|11111111|11110001
        int allAllow = ~allLimit;
        // limit只有实际位上为1 其他多余位为0
        // 截取掉左边多余位置的1置为0表示不可以放皇后 实际位置1的为可以放皇后
        int allBitAllow = limit & allAllow;
        // 表示最右侧可以放皇后的位置
        int mostRightAllow = 0;
        int result = 0;
        while (allBitAllow > 0) {
            // 找出最右侧allBitAllow为1可以放皇后的位置
            mostRightAllow = allBitAllow & (~allBitAllow + 1);
            // 剩余可以放皇后的位置
            allBitAllow = allBitAllow ^ mostRightAllow;
            // rowLimit | mostRightAllow 当前行多方了一列皇后
            //result += process(limit, rowLimit | mostRightAllow, (mostRightAllow << 1), (mostRightAllow >> 1)); 会漏掉跨行斜线
            result += process(limit, rowLimit | mostRightAllow, (leftLimit | mostRightAllow) << 1, (rightLimit | mostRightAllow) >> 1);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int queen = 16;
        long s = System.currentTimeMillis();
        System.out.println(queenArrangement(queen));
        System.out.println("cost time:" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        System.out.println(queenArrangementUseBit(queen));
        System.out.println("cost time:" + (System.currentTimeMillis() - s));
    }
}
