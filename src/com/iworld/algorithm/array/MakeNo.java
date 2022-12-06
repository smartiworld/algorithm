package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个正整数M，返回一个长度为M的数组对任意的i,j,k三个位置
 * 如果i<j<k 都有arr[i]+arr[k]!=2*arr[j] 则返回构造的数组
 * https://github.com/algorithmzuo/trainingcamp003/blob/master/src/class01/Code06_MakeNo.java
 * @date 2022/12/5 22:44
 */
public class MakeNo {
    
    /**
     * 达标：对于任意的 i<k<j，满足   [i] + [j]  != [k] * 2
     * 构造基础数组 返回基础数组构造上层数组 构造上层数组的时候
     * 左半部分使用基础数组的2倍减1 右半部分使用2倍
     * 基础数组不存在nums[i]+nums[k]!=nums[j] 2倍后和2倍-1依然不成立
     * @param size
     * @return
     */
    public int[] makeNo(int size) {
        if (size == 1) {
            return new int[] { 1 };
        }
        //当前size的一半
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        // base -> 等长奇数达标来
        // base -> 等长偶数达标来
        int[] ans = new int[size];
        int index = 0;
        for(; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for(int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }
    
    public boolean isValid(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                for (int j = k + 1; j < n; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int size = 7;
        MakeNo makeNo = new MakeNo();
        int[] ans = makeNo.makeNo(size);
        for (int num : ans) {
            System.out.print(num + ",");
        }
        boolean status = makeNo.isValid(ans);
        System.out.println(status);
    }
}
