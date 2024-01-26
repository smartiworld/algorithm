package com.iworld.algorithm.array.heap;

import com.iworld.algorithm.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.6.4 前k大两排序数组相加和
 * 给定两个有序数组arr1和arr2，再给定一个正数K
 * 求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
 * @date 2024/1/25 16:37
 */
public class TopKSumFromTwoArray {
    /**
     * 将两个数组看成一个矩阵 最大值必然在矩阵的右下角的位置
     * 根据有序数组单调性 进行移动计算
     * 1.创建大根堆 堆根据两数相加和排序
     * 2.从数组右下角开始 将最大值和两个数在两个数组下标放入大根堆中
     * 3.弹出大根堆 堆顶 结算当前弹出相加和 然后将弹出位置附近可能产生相加和的位置放入大根堆
     * 4.根据大根堆排序 弹出堆顶最大值 结算弹出相加后的值  将弹出附近值放入大根堆
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static int[] topKSumQueue(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new MaxNodeComparator());
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = Math.min(len1 * len2, k);
        boolean[][] set = new boolean[len1][len2];
        int r1 = len1 - 1;
        int r2 = len2 - 1;
        set[r1][r2] = true;
        queue.add(new Node(r1, r2, nums1[r1] + nums2[r2]));
        int[] ans = new int[len];
        int ansIndex = 0;
        while (ansIndex < len) {
            Node node = queue.poll();
            ans[ansIndex++] = node.sum;
            r1 = node.index1;
            r2 = node.index2;
            if (r1 - 1 >= 0 && !set[r1 - 1][r2]) {
                set[r1 - 1][r2] = true;
                queue.add(new Node(r1 - 1, r2, nums1[r1 - 1] + nums2[r2]));
            }
            if (r2 - 1 >= 0 && !set[r1][r2 - 1]) {
                set[r1][r2 - 1] = true;
                queue.add(new Node(r1, r2 - 1, nums1[r1] + nums2[r2 - 1]));
            }
        }
        return ans;
    }
    
    public static class Node {
        public int index1;
        public int index2;
        public int sum;
        
        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    
        @Override
        public String toString() {
            return "Node{" +
                    "index1=" + index1 +
                    ", index2=" + index2 +
                    ", sum=" + sum +
                    '}';
        }
    }
    
    public static class MaxNodeComparator implements Comparator<Node> {
        
        @Override
        public int compare(Node n1, Node n2) {
            return n2.sum - n1.sum;
        }
    }
    
    public static void main(String[] args) {
        int times = 1000;
        int len = 10;
        int max = 50;
        int k = 5;
        i1 : for (int i = 0; i < times; i++) {
                int[] nums1 = ArrayUtil.generateSortArray(len, max);
                int[] nums2 = ArrayUtil.generateSortArray(len, max);
                int[] ans1 = topKSum(nums1, nums2, k);
                int[] ans2 = topKSumQueue(nums1, nums2, k);
                for (int j = 0; j < ans1.length; j++) {
                    if (ans1[j] != ans2[j]) {
                        ArrayUtil.printArray(nums1);
                        ArrayUtil.printArray(nums2);
                        ArrayUtil.printArray(ans1);
                        ArrayUtil.printArray(ans2);
                        break i1;
                    }
                }
        }
        // {6,10,22,23,32,34,38,39,42,44}
        //{0,0,8,13,15,17,23,40,46,46}
        int[] nums1 = {0,9,11,18,22,31,40,43,47,48};
        int[] nums2 = {8,10,26,27,28,34,36,39,42,44};
        int[] ans1 = topKSum(nums1, nums2, k);
        int[] ans2 = topKSumQueue(nums1, nums2, k);
        ArrayUtil.printArray(ans1);
        ArrayUtil.printArray(ans2);
    }
    
    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }
}