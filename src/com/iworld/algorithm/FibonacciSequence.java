package com.iworld.algorithm;

/**
 * 斐波那契数列
 * 0 1 1 2 3 5 8 13 21 34 55
 * @Autor iworld
 * @Date 2020-05-25 18:08
 */
public class FibonacciSequence {

    int[] defaultsArray = {0, 1};

    public static void main(String[] args) {
        FibonacciSequence fibonacciSequence = new FibonacciSequence();
        int fibonacciNum = fibonacciSequence.getFibonacci(6);
        System.out.println(fibonacciNum);
        int fibonacciNum2 = fibonacciSequence.getFibonacciForEache(6);
        System.out.println("fibonacciNum==" + fibonacciNum + "==fibonacciNum2==" + fibonacciNum);
    }

    public void test(){
        int fibonacciNum = getFibonacci(5);
        System.out.println(fibonacciNum);
    }

    /**
     * 递归求位置数列
     * @param n
     * @return
     */
    public int getFibonacci(int n){
        if(n < 2){
            return defaultsArray[n];
        }
        return getFibonacci(n -1) + getFibonacci(n - 2);
    }

    /**
     * 遍历求位置数列
     * @param n
     * @return
     */
    public int getFibonacciForEache(int n){
        if(n < 2){
            return defaultsArray[n];
        }
        int result = 0;
        int fibonacciOne = 0;
        int fibonacciTwo = 1;
        for (int i = 0; i <= n; i++){
            result = fibonacciOne + fibonacciTwo;
            fibonacciOne = fibonacciTwo;
            fibonacciTwo = result;
        }
        return result;
    }

}
