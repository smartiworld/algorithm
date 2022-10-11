package com.iworld.algorithm.tree.segment;

/**
 * @author gq.cai
 * @version 1.0
 * @description 累加线段数
 * @date 2022/7/27 16:37
 */
public class SumSegmentTree {
    
    private int[] sum;
    
    private int[] lazy;
    
    public SumSegmentTree(int size) {
        int n = size + 1;
        sum = new int[n << 2];
        lazy = new int[n << 2];
    }
    
    public void add(int left, int right, int l, int r, int value, int index) {
        if (left <= l && r <= right) {
            sum[index] = sum[index] + value;
            return ;
        }
    }
    
    private void pushValueDown(int index) {
    
    }
    
}
