package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 135.Candy
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 * Constraints:
 *
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 * @date 2024/10/22 13:45
 */
public class Candy {
    
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 1) {
            return 1;
        }
        List<Integer> startIndexList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (ratings[i + 1] >= ratings[i]) {
                    startIndexList.add(i);
                }
            } else if (i == n - 1) {
                if (ratings[i] <= ratings[i - 1]) {
                    startIndexList.add(i);
                }
            } else if (ratings[i + 1] >= ratings[i] && ratings[i] <= ratings[i - 1]) {
                startIndexList.add(i);
            }
        }
        int[] help = new int[n];
        for (int k = 0; k < startIndexList.size(); k++) {
            Integer minIndex = startIndexList.get(k);
            help[minIndex] = 1;
            if (minIndex < n - 1) {
                for (int i = minIndex + 1; i < n; i++) {
                    int curCandy = 1;
                    if (ratings[i] > ratings[i - 1]) {
                        curCandy = help[i - 1] + 1;
                    }
                    help[i] = Math.max(help[i], curCandy);
                }
            }
            if (minIndex <= n - 1 && minIndex > 0) {
                for (int i = minIndex - 1; i >= 0; i--) {
                    int curCandy = 1;
                    if (ratings[i] > ratings[i + 1]) {
                        curCandy = help[i + 1] + 1;
                    }
                    help[i] = Math.max(help[i], curCandy);
                }
            }
        }
        int minCandy = 0;
        for (int i = 0; i < n; i++) {
            minCandy += help[i];
        }
        return minCandy;
    }
    
    public int candy2(int[] ratings) {
        int n = ratings.length;
        if ( n == 1) {
            return 1;
        }
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            int cur = 1;
            if (ratings[i] > ratings[i - 1]) {
                cur = left[i - 1] + 1;
            }
            left[i] = cur;
        }
        int[] right = new int[n];
        right[n - 1] = 1;
        int res = Math.max(left[n - 1], right[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            int cur = 1;
            if (ratings[i] > ratings[i + 1]) {
                cur = right[i + 1] + 1;
            }
            right[i] = cur;
            res += Math.max(left[i], right[i]);
        }
        return res;
    }
    
    public int candy3(int[] ratings) {
        int n = ratings.length;
        if ( n == 1) {
            return 1;
        }
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            int cur = 1;
            if (ratings[i] > ratings[i - 1]) {
                cur = left[i - 1] + 1;
            } else if (ratings[i] == ratings[i - 1]) {
                cur = left[i - 1];
            }
            left[i] = cur;
        }
        int[] right = new int[n];
        right[n - 1] = 1;
        int res = Math.max(left[n - 1], right[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            int cur = 1;
            if (ratings[i] > ratings[i + 1]) {
                cur = right[i + 1] + 1;
            } else if (ratings[i] == ratings[i + 1]) {
                cur = right[i + 1];
            }
            right[i] = cur;
            res += Math.max(left[i], right[i]);
        }
        return res;
    }
    
    public static int candyOpt(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex2(arr, 0);
        int[] data = rightCandysAndBase(arr, 0, index++);
        int res = data[0];
        int lbase = 1;
        // 相同的数
        int same = 1;
        int next = 0;
        while (index != arr.length) {
            // 上坡
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                same = 1;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                // 下坡
                next = nextMinIndex2(arr, index - 1);
                data = rightCandysAndBase(arr, index - 1, next++);
                if (data[1] <= lbase) {
                    res += data[0] - data[1];
                } else {
                    res += -lbase * same + data[0] - data[1] + data[1] * same;
                }
                index = next;
                lbase = 1;
                same = 1;
            } else {
                // 平峰
                res += lbase;
                same++;
                index++;
            }
        }
        return res;
    }
    
    /**
     * 在数组中从当前位置向右找出上坡点
     * @param arr
     * @param start
     * @return
     */
    public static int nextMinIndex2(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }
    
    public static int[] rightCandysAndBase(int[] arr, int left, int right) {
        int base = 1;
        int cands = 1;
        for (int i = right - 1; i >= left; i--) {
            if (arr[i] == arr[i + 1]) {
                cands += base;
            } else {
                cands += ++base;
            }
        }
        return new int[] { cands, base };
    }
    
    public static int candyBest(int[] ratings) {
        int len = ratings.length;
        // 每个位置默认是1 每个位置从0开始
        int candies = len;
        // 默认每个位置糖果从0开始
        int[] candy = new int[len];
        // 上坡
        for(int i=1;i<len;i++){
            // 上坡 并且较大值分配的糖果小于等于小值糖果 需要给当前位置分配糖果
            if(ratings[i]>ratings[i-1] && candy[i]<=candy[i-1]){
                candy[i] = candy[i-1]+1;
            }
        }
        // 下坡 同样逻辑分配糖果
        for(int i=len-2;i>=0;i--){
            if(ratings[i]>ratings[i+1] && candy[i]<=candy[i+1]){
                candy[i] = candy[i+1]+1;
            }
        }
        // 收集糖果数量
        for(int i=0;i<len;i++){
            candies += candy[i];
        }
        return candies;
    }
    
    public static void main(String[] args) {
        int[] ratings = {1,2,3,5,4,3,2,1,4,3,2,1,3,2,1,1,2,3,4};
        Candy candy = new Candy();
        System.out.println(candy.candy(ratings));
        System.out.println(candy.candy2(ratings));
        System.out.println(candy.candy3(ratings));
        candyOpt(ratings);
    }
    
    
}
