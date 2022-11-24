package com.iworld.algorithm.qp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个数N，只能由0和1组成长度为N的字符
 * 如果字符串中任何0字符的左边都有1紧挨着，认为这个字符串达标，求返回达标字符串的个数
 * @date 2022/11/23 23:25
 */
public class OneZeroOrdered {
    
    /**
     * 递归思路 如果是n，认为最左侧一定要保证是1，长度就变为n-1
     * 如果n-1长度时如果是1右侧填充是0 则需要n-3长度计算
     * @param n
     * @return
     */
    public int observeStrCount(int n) {
        return processn(n - 1, 1);
    }
    
    /**
     * 此时填的是字符第二位置 第一个位置默认就是1，如果第二个位置为1 下一步则为n-1长度递归
     * 如果为0 则下一位一定需要是1，此时就在长度为n-2长度递归
     * @param n
     * @return
     */
    public int processn(int n, int preNum) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        // 当前位置填1的时候 processn(n - 1);
        // 当前位置填0的时候 processn(n - 2);
        if (preNum == 0) {
            return processn(n - 1, 1);
        }
        return processn(n - 1, 1) + processn(n - 1, 0);
    }
    
    public int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }
    
    public int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }
    
    public int observeStrCountDp(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int next = 1;
        int cur = 2;
        for (int i = n - 2; i > 0; i--) {
            int tmp = cur;
            cur = cur + next;
            next = tmp;
        }
        return cur;
    }
    
    public int observeStrCountMath(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }
    
    public int[][] matrixPower(int[][] matrix, int pow) {
        int[][] ans = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            ans[i][i] = 1;
        }
        int[][] base = matrix;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                ans = multiMatrix(ans, base);
            }
            base = multiMatrix(base, base);
            pow >>= 1;
        }
        return ans;
    }
    
    private int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] ans = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        OneZeroOrdered oneZeroOrdered = new OneZeroOrdered();
        System.out.println(oneZeroOrdered.observeStrCount(4));
        System.out.println(oneZeroOrdered.getNum1(4));
        for (int i = 1; i < 40; i++) {
            int i1 = oneZeroOrdered.observeStrCount(i);
            int i2 = oneZeroOrdered.getNum1(i);
            if (i1 != i2) {
                System.out.println("失败" + i + ",i1==" + i1 + ",i2=" + i2);
            }
            int i3 = oneZeroOrdered.observeStrCountMath(i);
            if (i3 != i2) {
                System.out.println("失败2===" + i + ",i3==" + i3 + ",i2=" + i2);
            }
            int i4 = oneZeroOrdered.observeStrCountDp(i);
            if (i4 != i2) {
                System.out.println("失败4===" + i + ",i4==" + i4 + ",i2=" + i2);
            }
        }
        System.out.println("end");
    }
    
}
