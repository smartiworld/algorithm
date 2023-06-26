package com.iworld.algorithm.array.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description Traveling Salesman Problem 旅行商问题
 * 矩阵中 行列代表两个城市 值代表距离
 * @date 2023/6/8 17:07
 */
public class TSP {
    
    public int tsp(int[][] matrix) {
        int row = matrix.length;
        // 城市集合
        List<Integer> citys = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            citys.add(i);
        }
        return process(matrix, 0, citys, 0);
    }
    
    /**
     * 初始状态
     * f({0,1,2,3,4},0)
     * 表示从0来到了1城市 未走的城市{1,2,3,4}
     * f({1,2,3,4},1)
     * 表示从0来到了2城市 未走的城市{1,2,3,4}
     * f({1,2,3,4},2)
     * 表示从0来到了3城市 未走的城市{1,2,3,4}
     * f({1,2,3,4},3)
     * 表示从0来到了4城市 未走的城市{1,2,3,4}
     * f({1,2,3,4},4)
     * @param matrix    城市间距离矩阵
     * @param city      当前城市 走完citys城市列表最短距离
     * @param citys     当前未走完城市
     * @param startCity 开始城市
     */
    private int process(int[][] matrix, int city, List<Integer> citys, int startCity) {
        int cityNum = 0;
        for (int i = 0; i < citys.size(); i++) {
            if (citys.get(i) != null) {
                cityNum++;
            }
        }
        // 来到了最后一个城市 此时计算当前最后一个城市到开始城市距离
        if (cityNum == 1) {
            return matrix[city][startCity];
        }
        citys.set(city, null);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < citys.size(); i++) {
            if (citys.get(i) != null) {
                min = Math.min(min, matrix[city][i] + process(matrix, i, citys, startCity));
            }
        }
        citys.set(city, city);
        return min;
    }
    
    public int tspOpt(int[][] matrix) {
        int row = matrix.length;
        return process(matrix, 0, (1 << row) - 1, 0);
    }
    
    /**
     * citys 每个bit位表示当前城市是否已经走过1位表示未走
     * @param matrix    城市间距离矩阵
     * @param city      当前城市
     * @param citys     当前未走完城市 使用整数位信息1代表还走过当前城市0代表已经走过当前城市
     * @param startCity 开始城市
     */
    private int process(int[][] matrix, int city, int citys, int startCity) {
        // 来到了最后一个城市 此时计算当前最后一个城市到开始城市距离
        if (1 << city == citys) {
            return matrix[city][startCity];
        }
        // 将当前城市从所有城市中去除~(1 << city)除了当前位置其他都是1&citys 清除当前位置1
        // newCitys 剩余未走的城市
        int newCitys = citys & (~(1 << city));
        int min = Integer.MAX_VALUE;
//        int newCity = 0;
//        while (citys != 0) {
//            if ((citys & 1) != 0) {
//                min = Math.min(min, matrix[city][newCity] + process(matrix, newCity, newCitys, startCity));
//            }
//            citys >>= 1;
//            newCity++;
//        }
        for (int move = 0; move < matrix.length; move++) {
            // 不再走当前城市 (newCitys & (1 << move))当前位置状态位 为1才会继续
            if (move != city && (newCitys & (1 << move)) != 0) {
                // matrix[city][move] city城市位置来到move位置
                int cur = matrix[city][move] + process(matrix, move, newCitys, startCity);
                min = Math.min(min, cur);
            }
        }
        return min;
    }
    
    /**     0              1           2
     * 000  X              X           X
     * 001  0              X           X
     * 010  X              1->0        X
     * 011  0->1=0010|1
     * 100  X              X           2->0
     * 101  0->2=0100|2
     * 110  X
     * 111  0->1=0110|1
     *      0->2=0110|2
     * @param matrix
     * @return
     */
    public int tspDp(int[][] matrix) {
        int row = matrix.length;
        int citys = 1 << row;
        int[][] dp = new int[citys][row];
        for (int status = 0; status < citys; status++) {
            // 当前走的城市
            for (int city = 0; city < row; city++) {
                // 表示除了当前位置还有其他城市
                if ((status & (1 << city)) != 0) {
                    // 当前来到最后一个城市
                    if (1 << city == status) {
                        dp[status][city] = matrix[city][0];
                    } else {
                        // 去掉当前城市 处理剩余城市
                        int newCitys = status & (~(1 << city));
                        int min = Integer.MAX_VALUE;
                        // 把status未走完的可达城市走一遍
                        for (int move = 0; move < row; move++) {
                            if (move != city && (newCitys & (1 << move)) != 0) {
                                min = Math.min(min, matrix[city][move] + dp[newCitys][move]);
                            }
                        }
                        dp[status][city] = min;
                    }
                }
                
            }
        }
        return dp[citys - 1][0];
    }
    
    public static void main(String[] args) {
        TSP tsp = new TSP();
        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 20000; i++) {
            int[][] matrix = generateGraph(len, value);
            int origin = (int) (Math.random() * matrix.length);
            int ans1 = tsp.tsp(matrix);
            int ans2 = tsp.tspOpt(matrix);
            int ans5 = tsp.tspDp(matrix);
            int ans3 = tsp2(matrix, origin);
//            if (ans1 != ans3) {
//                System.out.println("fuck");
//            }
//            if (ans2 != ans3) {
//                System.out.println("fuck");
//            }
            if (ans5 != ans3) {
                System.out.println("fuck");
            }
//            System.out.println(ans5);
//            System.out.println(ans3);
//            System.out.println();
        }
        System.out.println("功能测试结束");
        
//        len = 22;
//        System.out.println("性能测试开始，数据规模 : " + len);
//        int[][] matrix = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                matrix[i][j] = (int) (Math.random() * value) + 1;
//            }
//        }
//        for (int i = 0; i < len; i++) {
//            matrix[i][i] = 0;
//        }
//        long start;
//        long end;
//        start = System.currentTimeMillis();
//        t4(matrix);
//        end = System.currentTimeMillis();
//        System.out.println("运行时间 : " + (end - start) + " 毫秒");
//        System.out.println("性能测试结束");
        
    }
    
    public static int tsp2(int[][] matrix, int origin) {
        if (matrix == null || matrix.length < 2 || origin < 0 || origin >= matrix.length) {
            return 0;
        }
        int N = matrix.length - 1; // 除去origin之后是n-1个点
        int S = 1 << N; // 状态数量
        int[][] dp = new int[S][N];
        int icity = 0;
        int kcity = 0;
        for (int i = 0; i < N; i++) {
            icity = i < origin ? i : i + 1;
            // 00000000 i
            dp[0][i] = matrix[icity][origin];
        }
        for (int status = 1; status < S; status++) {
            // 尝试每一种状态 status = 0 0 1 0 0 0 0 0 0
            // 下标 8 7 6 5 4 3 2 1 0
            for (int i = 0; i < N; i++) {
                // i 枚举的出发城市
                dp[status][i] = Integer.MAX_VALUE;
                if ((1 << i & status) != 0) {
                    // 如果i这座城是可以枚举的，i = 6 ， i对应的原始城的编号，icity
                    icity = i < origin ? i : i + 1;
                    for (int k = 0; k < N; k++) { // i 这一步连到的点，k
                        if ((1 << k & status) != 0) { // i 这一步可以连到k
                            kcity = k < origin ? k : k + 1; // k对应的原始城的编号，kcity
                            dp[status][i] = Math.min(dp[status][i], dp[status ^ (1 << i)][k] + matrix[icity][kcity]);
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            icity = i < origin ? i : i + 1;
            ans = Math.min(ans, dp[S - 1][i] + matrix[origin][icity]);
        }
        return ans;
    }
    
    public static int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }
}
