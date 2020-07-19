package com.iworld.algorithm;

/**
 * 跳水板
 *
 * https://leetcode-cn.com/problems/diving-board-lcci/
 *
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
 * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。返回的长度需要从小到大排列。
 *
 * 示例：
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * @Autor iworld
 * @Date 2020-07-08 14:41
 */
public class DivingBoard {

    public static void main(String[] args) {
        DivingBoard divingBoard = new DivingBoard();
        int[] result = divingBoard.divingBoard(1, 2, 3);
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1){
                System.out.print(result[i] + "]");
            }else{
                System.out.print(result[i] + ",");
            }
        }
    }
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0){
            return new int[]{};
        }
        if(shorter == longer){
            return new int[]{shorter * k};
        }
        int[] result  = new int[k + 1];
        for (int i = 0; i <= k ; i++) {
            result[i] = longer * i + shorter * (k - i);
        }
        return result;
    }
}
