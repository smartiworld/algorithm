package com.iworld.algorithm.tree.segment;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/7/26 11:39
 */
public class MaxSegmentTree {
    
    private int[] max;
    private int[] change;
    private boolean[] update;
    
    public MaxSegmentTree(int size) {
        int n = size + 1;
        max = new int[n << 2];
        change = new int[n << 2];
        update = new boolean[n << 2];
    }
    
    public void update(int left, int right, int l, int r, int value, int index) {
        if (left <= l && r <= right) {
            change[index] = value;
            update[index] = true;
            max[index] = value;
            return ;
        }
        pushValueDown(index);
        int mid = l + ((r - l) >> 1);
        if (left <= mid) {
            update(left, right, l, mid, value, index << 1);
        }
        if (mid < right) {
            update(left, right, mid + 1, r, value, index << 1 | 1);
        }
        pushMaxParent(index);
    }
    
    public int query(int left, int right, int l, int r, int index) {
        if (left <= l && r <= right) {
            return max[index];
        }
        int mid = l + ((r - l) >> 1);
        int leftAns = 0;
        int rightAns = 0;
        pushValueDown(index);
        if (left <= mid) {
            leftAns = query(left, right, l, mid, index << 1);
        }
        if (mid < right) {
            rightAns = query(left, right, mid + 1, r, index << 1 | 1);
        }
        return Math.max(leftAns, rightAns);
    }
    
    private void pushValueDown(int parent) {
        if (update[parent]) {
            update[parent << 1] = true;
            update[parent << 1 | 1] = true;
            change[parent << 1] = change[parent];
            change[parent << 1 | 1] = change[parent];
            max[parent << 1] = change[parent];
            max[parent << 1 | 1] = change[parent];
            update[parent] = false;
        }
    }
    
    private void pushMaxParent(int index) {
        max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
    }
}
