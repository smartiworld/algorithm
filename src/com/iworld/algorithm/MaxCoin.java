package com.iworld.algorithm;

/**
 * 动态规划
 * 求最大不相邻币种和
 * 给定一排硬币，面值为正数，硬币并不一定两辆相同，如果选择硬币在原始位置不相邻的条件下，所选币种总和最大
 * 分为最后一枚硬币和和不包括最后一枚硬币，包括最后一枚硬币表达式为c(n) + f(n-2),包括最后一枚硬币表达式
 * f(n-1)
 *      F(n)=max(c(n) + F(n-2),F(n-1)) F(0)=0,F(1)=c(1)
 *
 * 例 5,1,2,10,6,2 硬币
 * F(0)=0,F(1)=5
 * F(2)=max(1+0,5)=5
 * F(3)=max(2+5,5)=7
 * F(4)=max(10+5,7)=15
 * F(5)=max(6+7,15)=15
 * F(6)=max(2+15,15)=17
 * @Autor iworld
 * @Date 2020-06-10 13:26
 */
public class MaxCoin {

    public static void main(String[] args) {
        //int[] ints = new int[]{9, 5, 12, 16, 19, 7, 8, 2, 1};
        MaxCoin maxCoin = new MaxCoin();
        int[] ints = new int[]{5,1,2,10,6,2};
        int maxCoinNum = maxCoin.maxCoinNum(ints);
        System.out.println("maxCoinNum==" + maxCoinNum);
    }

    /**
     * 计算所有硬币可取出最大金额
     * @param ints
     * @return
     */
    public int maxCoinNum(int[] ints){
        int intsZero = 0;
        int intsOne = ints[0];
        int maxCoin = 0;
        int[] fns = new int[ints.length];
        fns[0] = intsOne;
        for (int i = 1; i < ints.length; i++) {
            if(i == 1){
                maxCoin = ints[i] > intsOne ? ints[i] : intsOne;
            }else{
                maxCoin = ints[i] + fns[i - 2] > ints[i - 1] ? ints[i] + fns[i - 2] : ints[i - 1];
            }
            fns[i] = maxCoin;
        }
        return maxCoin;
    }

    /**
     * 计算所有硬币可取出最大金额
     * @param ints
     * @return
     */
    public int maxCoinNum(int[] ints, int position){
        int intsZero = 0;
        int intsOne = ints[0];
        int maxCoin = 0;
        int[] fns = new int[position];
        fns[0] = intsOne;
        for (int i = 1; i < position; i++) {
            if(i == 1){
                maxCoin = ints[i] > intsOne ? ints[i] : intsOne;
            }else{
                maxCoin = ints[i] + fns[i - 2] > ints[i - 1] ? ints[i] + fns[i - 2] : ints[i - 1];
            }
            fns[i] = maxCoin;
        }
        return maxCoin;
    }
}
