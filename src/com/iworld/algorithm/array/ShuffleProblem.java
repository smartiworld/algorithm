package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4.6.4 完美洗牌
 * 当数组长度满足3^k-1长度  数组可以通过k次得出洗牌后结果
 * 1.当数组长度满足3^k-1的时候 出发点就为个k  并且所有每次出发点为3^(k-1) k取值0~k
 * 2.如果数组长度不为3^k-1 则找到最接近小于数组长度的3^k-1的值
 * 3.找出数组长度最接近3^k-1
 * 4.将找出3^k-1长度数组后 找出需要旋转数组的长度和中间位置
 * 旋转左边界 l+(3^k-1)/2  旋转中间位置 (l+r)/2  旋转右边界 (l+r)/2 + (3^k-1)/2
 * 5.旋转完成后从l位置长度为3^k-1 开始下标连续推k次
 * @date 2022/10/14 21:23
 */
public class ShuffleProblem {
    
    public void shuffle(int[] arr) {
        if (arr != null && arr.length != 0 && (arr.length & 1) == 0) {
            shuffle(arr, 0, arr.length - 1);
        }
    }
    
    /**
     * l-r区间一定需要为偶数个
     * @param arr
     * @param l
     * @param r
     */
    private void shuffle(int[] arr, int l, int r) {
        // l~r长度需要偶数长度  奇数不处理
        if (((r - l + 1) & 1) == 1) {
            return ;
        }
        // 切成一块一块的解决，每一块的长度满足(3^k)-1
        while (r - l + 1 > 0) {
            int len = r - l + 1;
            // 3^k
            int base = 3;
            int k = 1;
            // 计算小于等于len并且是离len最近的，满足(3^k)-1的数
            // 也就是找到最大的k，满足3^k <= len+1
            // base > (N+1)/3
            // 条件3^k-1<len -> 3^k<len+1 防止预先k=1 3^(k-1)<(len+1)/3
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            // 3^k -1  base - 1 为要处理的长度
            // 当前要解决长度为base-1的块，一半就是再除2  一半后面需要旋转 前面half长度是不需要旋转
            int half = (base - 1) / 2;
            // [L..R]的中点位置
            int mid = (l + r) / 2;
            // 要旋转的左部分为[L+half...mid], 右部分为arr[mid+1..mid+half]
            // 注意在这里，arr下标是从0开始的
            rotate(arr, l + half, mid, mid + half);
            // 旋转完成后，从L开始算起，长度为base-1的部分进行下标连续推
            cycles(arr, l, base - 1, k);
            // 解决了前base-1的部分，剩下的部分继续处理
            // l -> [] [+1...R]
            l = l + base - 1;
        }
    }
    
    /**
     * 数组从m+1~r位置的元素和l~m位置交换位置
     * [0,1,2,3,4,5,6,7,8]m=3
     * 需要结果[4,5,6,7,8,0,1,2,3]
     * [l~m]0位置到3位置反转[3,2,1,0,4,5,6,7,8]
     * [m+1~r]4位置到8位置反转[3,2,1,0,8,7,6,5,4]
     * [l~r]0位置到8位置反转[4,5,6,7,8,0,1,2,3]
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    private void rotate(int[] arr, int l, int m, int r) {
        revert(arr, l, m);
        revert(arr, m + 1, r);
        revert(arr, l, r);
    }
    
    private void revert(int[] arr, int l, int r) {
        if (l < 0 || r > arr.length) {
            return ;
        }
        while (l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }
    
    /**
     * 下标连续推
     * @param arr  连续推的数组
     * @param l    数组左边界
     * @param len  数组处理长度
     * @param k    连续推的次数
     */
    private void cycles(int[] arr, int l, int len, int k) {
        // 找到每一个出发位置trigger，一共k个
        // 每一个trigger都进行下标连续推
        // 出发位置是从1开始算的，而数组下标是从0开始算的。
        // 完美洗牌默认位置为1 初始为trigger 实际数组计算需要减1 实际在数组中的位置
        // 数组长度刚好为3^k-1 偶数时 开始点为k个 开始点为1~3^(k-1)
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = arr[trigger + l - 1];
            int cur = getIndex(trigger, len);
            while (cur != trigger) {
                // 需要去的下一个位置值
                int tmp = arr[cur + l - 1];
                // 将当前要推的值放入到下一个位置上
                arr[cur + l - 1] = preValue;
                // 下一个位置值 放入当前值记录
                preValue = tmp;
                // 计算来到的下一个位置 计算下一个位置的时候是完美洗牌的位置从1开始，计算数组实际位置的时候将当前值-1
                cur = getIndex(cur, len);
            }
            // 来到初始位置 将
            arr[cur + l - 1] = preValue;
        }
    }
    
    /**
     * 计算位置下标从1开始 实际使用需要多减个1
     * 例：{1,2,3,4,5,6,7,8}需要循环推理2次
     * 第一次 开始位置1 3^(k-1)k=1
     * 小于等于长度一半位置的循环位置去向 1->2, 2->4, 4->8
     * 大于长度一半位置循环 8->7, 7->5, 5->1 等于了开始位置 结束
     * 第二次开始位置3 3^(k-1)k=2
     * 小于等于长度一半位置循环位置去向 3->6
     * 大于长度一半位置循环 6->3 等于开始位置结束
     * @param i
     * @param len
     * @return
     */
    private int getIndex2(int i, int len) {
        int n = len >> 1;
        if (i > n) {
            // 大于
            return (i - n) * 2 - 1;
        } else {
            return 2 * i;
        }
    }
    
    /**
     * getIndex2返回结果相同
     * @param i
     * @param len
     * @return
     */
    private int getIndex(int i, int len) {
        return (2 * i) % (len + 1);
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        ShuffleProblem shuffleProblem = new ShuffleProblem();
        shuffleProblem.shuffle(arr);
        //shuffleProblem.rotate(arr, 0, 3, 8);
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }
}
