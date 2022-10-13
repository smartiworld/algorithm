package com.iworld.algorithm.tree.index;

/**
 * @author gq.cai
 * @version 1.0
 * @description 树状树
 * 1.支持区间查询
 * 2.只支持单点更新
 * 3.不如线段树那么强大，容易改写多维树
 * 内部数组空间需要从1开始
 * 想象空间2的整数次方一定包含开始位置到当前位置所有和
 * 4.假设某一个index的二进制表示形式如下，index为help数组中的下标i，那么index位置的值表示记录原数组哪些范围的值呢？010110001 ~ 010111000
 * 5.如果想求原数组中从 1 ~ i 位置的前缀和，假设 i 为33，如何利用help数组求？
 *  i 转换为二进制形式，33位置上只管自己，但是32位置上管的是从1到32位置上的所有，所以它两一累加就实现了1到33的累加
 * 原数组：  1,2,  3,4,  5,6,  7,8,  9,10,  11,12,  13,14,   15,16
 * 辅助数组：1,1~2,3,1~4,5,5~6,7,1~8,9,9~10,11,9~12,13,13~14,15,1~16
 * @date 2022/10/13 13:15
 */
public class IndexTree {
    
    private int[] tree;
    
    private int size;
    
    public IndexTree(int size) {
        this.size = size + 1;
        tree = new int[this.size];
    }
    
    /**
     * 1~index
     * 计算1-index范围的累加和
     * 计算当前位置如果 已经包含所有位置和  则此时为2的整数次方二进制一个1
     * @param index
     * @return
     */
    public int sum(int index) {
        int ans = 0;
        while (index > 0) {
            ans += tree[index];
            index -= (index & (-index));
        }
        return ans;
    }
    
    /**
     * index位置增加一个值
     * 当前位置 和后面包含此位置的位置都需要加上当前数
     * @param index
     */
    public void add(int index, int value) {
        while (index < size) {
            tree[index] += value;
            index += (index & (-index));
        }
    }
    
}
