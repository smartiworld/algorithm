package com.iworld.algorithm.calc;

import com.iworld.algorithm.util.ArrayUtil;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.6.1 最大异或和子数组
 * 一个数组的异或和是指数组中所有的数异或在一起的结果
 * 给定一个数组arr，求最大子数组异或和。
 * @date 2024/1/22 18:02
 */
public class MaxEorSubArray {
    
    public static int maxEor(int[] nums) {
        int len = nums.length;
        int[] preEor = new int[len];
        preEor[0] = nums[0];
        int maxEor = preEor[0];
        for (int i = 1; i < len; i++) {
            preEor[i] = nums[i] ^ preEor[i - 1];
        }
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                // preEor[j] = eor(num[0~i-1]) ^ eor(num[i~j])
                // preEor[i~j]= eor(num[i~j]) = eor(num[0~i-1]) ^ eor(num[i~j]) ^ eor(num[0~i-1])
                //  = preEor[j] ^ preEor[0~i-1]       a~a=0  0^a=a
                int subEor = i == 0 ? preEor[j] : preEor[j] ^ preEor[i - 1];
                maxEor = Math.max(maxEor, subEor);
            }
        }
        return maxEor;
    }
    
    public static int maxEorPrefixTree(int[] nums) {
        NumPrefixTree numPrefixTree = new NumPrefixTree();
        int eor = 0;
        numPrefixTree.add(eor);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
            int maxEor = numPrefixTree.maxEor(eor);
            ans = Math.max(ans, maxEor);
            numPrefixTree.add(eor);
        }
        return ans;
    }
    
    /**
     * 保存所有的前缀异或和
     * 0~0 0~1 0~2 。。。。0~i
     * 当来到i位置时 找出一个最合适的前缀异或和 计算0~i范围 某个区间最大异或和
     * 0~i尝试和所有的前缀异或和 异或计算得出最大范围异或和
     * 如果j<i 0~i区间和0~j区间异或和最大，则表示j+1~i区间异或和最大
     * 如果j为0 则表示0~i区间范围异或和最大
     */
    public static class NumPrefixTree {
        
        public NumNode root;
        
        public NumPrefixTree() {
            this.root = new NumNode();
        }
        
        public void add(int total) {
            NumNode cur = root;
            for (int move = 31; move >= 0; move--) {
                int path = (total >> move) & 1;
                if (cur.next[path] == null) {
                    cur.next[path] = new NumNode();
                }
                cur = cur.next[path];
            }
        }
        
        public int maxEor(int total) {
            int result = 0;
            NumNode cur = root;
            for (int move = 31; move >= 0; move--) {
                int path = (total >> move) & 1;
                int best = move == 31 ? path : (path ^ 1);
                if (cur.next[best] == null) {
                    best = best ^ 1;
                }
                result |= (path ^ best) << move;
                cur = cur.next[best];
            }
            return result;
        }
    }
    
    public static class NumNode {
        /**
         * 两个路径分支 只有0 或者1
         */
        public NumNode[] next;
        
        public NumNode() {
            next = new NumNode[2];
        }
    }
    
    public static void main(String[] args) {
        int len = 5;
        int max = 10;
        int[] ints = ArrayUtil.generateIntArray(len, max);
        ArrayUtil.printArray(ints);
        int maxEor = maxEor(ints);
        System.out.println(maxEor);
        int maxEorPrefixTree = maxEorPrefixTree(ints);
        System.out.println(maxEorPrefixTree);
        int times = 1000;
        for (int i = 0; i < times; i++) {
            len = ((int)(Math.random() * 10)) + 5;
            max = ((int)(Math.random() * 10)) + 10;
            int[] randomArray = ArrayUtil.generateIntArray(len, max);
            int maxEor1 = maxEor(ints);
            int maxEorPrefixTree2 = maxEorPrefixTree(ints);
            if (maxEor1 != maxEorPrefixTree2) {
                ArrayUtil.printArray(randomArray);
                System.out.println(maxEor1 + "====" + maxEorPrefixTree2);
            }
        }
        System.out.println("end");
    }
}
