package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个整数 计算有多少种裂开方式 自己算一种 数字无顺序要求 相同个数的数字算一种
 * 例1： 1 裂开方式有一种1
 * 例2： 3 裂开方式 {1，1，1} {1，2} {3} 3种  {1.2}和{2，1}属于同种类
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class06/Code01_SplitNumer1.java
 * @date 2023/3/8 19:00
 */
public class SplitNumber {
    
    public int split(int num) {
        return process(1, num);
    }
    
    /**
     * 用pre小于等于rest 保证分裂后数字顺序 不会出现重复结果
     * @param pre   前面需要拆的数字
     * @param rest  拆完pre 后还剩多少数字
     * @return
     */
    private int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ans = 0;
        for (int i = pre; i <= rest; i++) {
            ans += process(i, rest - i);
        }
        return ans;
    }
    
    public int splitDp(int num) {
        // pre做行 rest做列 0行弃用  下半矩阵都是0
        int[][] dp = new int[num + 1][num + 1];
        // rest=0 dp=1 第一列结果都是1
        for (int i = 0; i <= num; i++) {
            dp[i][0] = 1;
        }
        for (int i = num; i >= 1; i--) {
            for (int j = i; j <= num; j++) {
                int ans = 0;
                for (int k = i; k <= j; k++) {
                    ans += dp[k][j - k];
                }
                dp[i][j] = ans;
            }
        }
        return dp[1][num];
    }
    
    /**
     * 斜率优化 可以省略枚举行为
     * pre 1 rest 7依赖
     *     1      6
     *     2      5
     *     3      4
     *     5      2
     *     6      1
     *     7      0
     * pre 2 rest 7 依赖
     *     2      5
     *     3      4
     *     4      3
     *     5      2
     *     6      1
     *     7      0
     * 1，7需要的2，7已经计算得出 所以1，7计算只需要1，6和2，7即可 不需要再遍历
     * pre 3 rest 7依赖
     *     3      4
     *     4      3
     *     5      2
     *     6      1
     *     7      0
     * 2，7需要3，7和2，5
     * 可以得出过程公式dp[i][j]=dp[i+1][j]+dp[i][j-i]
     * @param num
     * @return
     */
    public int splitDpBest(int num) {
        // pre做行 rest做列 0行弃用  下半矩阵都是0
        int[][] dp = new int[num + 1][num + 1];
        // rest=0 dp=1 第一列结果都是1
        for (int i = 0; i <= num; i++) {
            dp[i][0] = 1;
        }
        dp[num][num] = 1;
        for (int i = num - 1; i >= 1; i--) {
            for (int j = i; j <= num; j++) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - i];
            }
        }
        return dp[1][num];
    }
    
    public static void main(String[] args) {
        SplitNumber splitNumber = new SplitNumber();
        int num = 110;
        for (int i = 1; i < num; i++) {
            System.out.println(i);
            System.out.println(splitNumber.split(i));
            System.out.println(splitNumber.splitDp(i));
            System.out.println(splitNumber.splitDpBest(i));
            System.out.println();
        }
        
    }
}
