package com.iworld.algorithm.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.4.5 最大嵌套信封
 * 每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。
 * 如果给你一批信封，返回最大的嵌套层数
 * @date 2024/1/4 18:10
 */
public class MaxCoverLetter {
    
    /**
     * 将信封长按照从小到大的顺序 宽度按照从大到小的顺序
     * 取排序好的信封宽度 求最长递增子序列长度
     * 宽度从小到大 长度肯定也可以覆盖  因为在后面的长度 只能大于等于前面长度
     * 而如果长度相等宽度大的在前面 长度相等后面宽度只能是递减 不可能最长连续 覆盖
     * @param letters
     * @return
     */
    public static int maxCover(int[][] letters) {
        int len = letters.length;
        Letter[] letterArray = new Letter[len];
        for (int i = 0; i < len; i++) {
            Letter letter = new Letter(letters[i][0], letters[i][1]);
            letterArray[i] = letter;
        }
        Arrays.sort(letterArray, new LetterComparator());
        return longIncreaseSubsequenceBest(letterArray, len);
    }
    
    /**
     * ends 数组记录原数组 从大到小的排序 当前数字来了后 使用二分查找ends数组中大于等于当前数的最左位置 然后将当前数字覆盖原位置
     * 当前填充位置+1 即为当前位置最长递增序列
     * @param letterArray
     * @param len
     * @return
     */
    private static int longIncreaseSubsequenceBest(Letter[] letterArray, int len) {
        int[] ends = new int[len];
        ends[0] = letterArray[0].width;
        int max = 1;
        int end = 0;
        for (int i = 1; i < len; i++) {
            int l = 0;
            int r = end;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (ends[mid] < letterArray[i].width) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            ends[l] = letterArray[i].width;
            end = Math.max(l, end);
            max = Math.max(max, l + 1);
        }
        return max;
    }
    
    public static class Letter {
        
        private int length;
        
        private int width;
        
        public Letter(int length, int width) {
            this.length = length;
            this.width = width;
        }
    }
    
    public static class LetterComparator implements Comparator<Letter> {
        
        @Override
        public int compare(Letter l1, Letter l2) {
            return l1.length == l2.length ? l2.width - l1.width : l1.length - l2.length;
        }
    }
}
