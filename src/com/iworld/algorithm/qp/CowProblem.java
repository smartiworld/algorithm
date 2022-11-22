package com.iworld.algorithm.qp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 假设一头奶牛每年可以生一个小牛，小牛成长三年后可以生小牛
 * 假设中间没有牛死亡 计算n年后一共多少头牛
 * @date 2022/11/21 0:04
 */
public class CowProblem {


    public int calveCow(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return calveCow(n - 1) + calveCow(n - 3);
    }
    
    public int calveCowDp(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int prePre = 1;
        int pre = 2;
        int cur = 3;
        for (int i = 4; i <= n; i++) {
            int tmp = cur;
            cur = cur + prePre;
            prePre = pre;
            pre = tmp;
        }
        return cur;
    }
    
    /**
     * F(n) = F(n-1)+F(n-3)
     * FnFn-1Fn-2=F3F2F1*|base|^n-3
     * Fn=F3*base[0][0]+F2*base[1][0]+F1*base[2][0];
     * F3=3;F2=2;F1=1
     * @param n
     * @return
     */
    public int calveCowMath(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {{ 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 }};
        int[][] basePow = matrixPow(base, n - 3);
        return 3 * basePow[0][0] + 2 * basePow[1][0] + basePow[2][0];
    }
    
    private int[][] matrixPow(int[][] matrix, int pow) {
        int[][] ans = new int[matrix.length][matrix[0].length];
        // 初始化单位矩阵
        for (int i = 0; i < matrix.length; i++) {
            ans[i][i] = 1;
        }
        int[][] base = matrix;
        while (pow != 0) {
            // 表示当前位需要乘base
            if ((pow & 1) == 1) {
                ans = multiMatrix(ans, base);
            }
            // 进下一位 需要将base相乘
            base = multiMatrix(base, base);
            pow >>= 1;
        }
        return ans;
    }
    
    /**
     * m1矩阵的行 要和m2矩阵的列相同
     * m1矩阵的列乘m2矩阵的行
     * m1的行决定结果的行 m2的列决定结果的列
     * m1的行分别和m2列相乘 然后相加 结果放入m1的行m2的列
     * @param m1
     * @param m2
     * @return
     */
    private int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] ans = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        CowProblem cowProblem = new CowProblem();
        for (int i = 1; i <= 80; i++) {
            int i1 = cowProblem.calveCow(i);
            int i2 = cowProblem.calveCowDp(i);
            int i3 = cowProblem.calveCowMath(i);
            if (i1 != i2) {
                System.out.println(i);
            }
            if (i1 != i3) {
                System.out.println("math" + i);
            }
        }
        System.out.println("执行完毕");
    }
}
