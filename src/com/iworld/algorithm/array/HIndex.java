package com.iworld.algorithm.array;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 274. H-Index
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * Example 2:
 *
 * Input: citations = [1,3,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 * https://leetcode.com/problems/h-index/description/
 * @date 2024/10/14 11:06
 */
public class HIndex {
    
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < citations.length; i++) {
            max = Math.max(max, Math.min(citations.length - i, citations[i]));
        }
        return max;
    }
    
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            // citations[n - 1 - i] 逆序 如果后面引用小于发布 直接返回当前发布数量
            if (citations[n - 1 - i] < i + 1) {
                return i;
            }
        }
        return n;
    }
    
    /**
     * 桶排序 按照数组下标天然有序
     * @param citations
     * @return
     */
    public int hIndexBest(int[] citations) {
        int n = citations.length;
        // citation count 下标citation >n的citation 划分一类 下标被引用数量 值为发布数量
        int[] buckets = new int[n + 1];
        for (int citation : citations) {
            if (citation >= n){
                buckets[n]++;
            } else {
                buckets[citation]++;
            }
        }
        // 引用大于等于buckets[i]的总量
        int count = 0;
        for (int i = n; i >= 0; i--){
            // 计算桶中值
            count += buckets[i];
            // 发布数量大于等于引用数量返回 引用数量
            if (count >= i){
                return i;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        int[] citations = {1,3,1};
        System.out.println(hIndex.hIndex(citations));
    }

}
