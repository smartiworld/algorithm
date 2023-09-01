package com.iworld.algorithm;

/**
 * 欧几里得计算最大公约数算法
 *
 * @Autor iworld
 * @Date 2020-05-09 14:10
 */
public class EuclidsAlgorithm {

    public static void main(String[] args) {
        EuclidsAlgorithm euclidsAlgorithm = new EuclidsAlgorithm();
        int heightDivisor = euclidsAlgorithm.getHeightDivisor(13, 6);
        System.out.println("heightDivisor==" + heightDivisor);
    }

    public int getHeightDivisor(int m, int n){
        m = Math.max(m, n);
        n = Math.min(m, n);
        if(n == 0){
            return m;
        }
        if(m % n == 0){
            return n;
        }
        int heigjtDivisor = getHeightDivisor(n, m % n);
        return heigjtDivisor;
    }
    
    public int getHeightDivisor2(int m, int n){
        return n == 0 ? m : getHeightDivisor2(n, m % n);
    }
}
