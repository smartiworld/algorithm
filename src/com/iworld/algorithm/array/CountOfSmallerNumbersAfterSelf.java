package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 315. Count of Smaller Numbers After Self
 * Hard
 * 7580
 * 204
 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * @date 2022/10/12 14:50
 */
public class CountOfSmallerNumbersAfterSelf {
    
    public static class IndexNum {
        
        private int index;
        
        private int num;
        
        public IndexNum(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        IndexNum[] indexNums = new IndexNum[n];
        for (int i = 0; i < n; i++) {
            ans.add(0);
            indexNums[i] = new IndexNum(i, nums[i]);
        }
//        Map<IndexNum, Integer> counts = new TreeMap<>(new IndexNumComparator());
//        process(indexNums, 0, n - 1, counts);
//        process(indexNums, 0, n - 1, counts);
//        for (Map.Entry<IndexNum, Integer> entry : counts.entrySet()) {
//            ans.set(entry.getKey().index, entry.getValue());
//        }
        process(indexNums, 0, n - 1, ans);
        return ans;
    }
    
    private void process(IndexNum[] indexNums, int l, int r, Map<IndexNum, Integer> counts) {
        if (l == r) {
            return ;
        }
        int mid = l + ((r - l) >> 1);
        process(indexNums, l, mid, counts);
        process(indexNums, mid + 1, r, counts);
        merge(indexNums, l, mid, r, counts);
    }
    
    private void merge(IndexNum[] indexNums, int l, int mid, int r, Map<IndexNum, Integer> counts) {
        IndexNum[] help = new IndexNum[r - l + 1];
        int le = mid;
        int rs = mid + 1;
        int re = r;
        int hIndex = help.length - 1;
        while (l <= le && rs <= re) {
            if (indexNums[le].num > indexNums[re].num) {
                int c = re - rs + 1;
                Integer count = counts.get(indexNums[le]);
                if (count == null) {
                    counts.put(indexNums[le], c);
                } else {
                    counts.put(indexNums[le], count + c);
                }
                help[hIndex--] = indexNums[le--];
            } else {
                help[hIndex--] = indexNums[re--];
            }
        }
        while (l <= le) {
            help[hIndex--] = indexNums[le--];
        }
        while (rs <= re) {
            help[hIndex--] = indexNums[re--];
        }
        for (int i = 0; i < help.length; i++) {
            indexNums[i + l] = help[i];
        }
    }
    
    private void process(IndexNum[] indexNums, int l, int r, List<Integer> ans) {
        if (l == r) {
            return ;
        }
        int mid = l + ((r - l) >> 1);
        process(indexNums, l, mid, ans);
        process(indexNums, mid + 1, r, ans);
        merge(indexNums, l, mid, r, ans);
    }
    
    private void merge(IndexNum[] indexNums, int l, int mid, int r, List<Integer> ans) {
        IndexNum[] help = new IndexNum[r - l + 1];
        int le = mid;
        int rs = mid + 1;
        int re = r;
        int hIndex = help.length - 1;
        while (l <= le && rs <= re) {
            if (indexNums[le].num > indexNums[re].num) {
                int c = re - rs + 1;
                ans.set(indexNums[le].index, ans.get(indexNums[le].index) + c);
                help[hIndex--] = indexNums[le--];
            } else {
                help[hIndex--] = indexNums[re--];
            }
        }
        while (l <= le) {
            help[hIndex--] = indexNums[le--];
        }
        while (rs <= re) {
            help[hIndex--] = indexNums[re--];
        }
        for (int i = 0; i < help.length; i++) {
            indexNums[i + l] = help[i];
        }
    }
    
    public static class IndexNumComparator implements Comparator<IndexNum> {
        
        @Override
        public int compare(IndexNum i1, IndexNum i2) {
            return i1.index - i2.index;
        }
    }
    
    /**
     * 暴力解
     * @param nums
     * @return
     */
    public List<Integer> countSmaller2(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>(n);
        for (int i = 0; i < n - 1; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            ans.add(i, count);
        }
        ans.add(n - 1, 0);
        return ans;
    }
    
    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        CountOfSmallerNumbersAfterSelf countOfSmallerNumbersAfterSelf = new CountOfSmallerNumbersAfterSelf();
        System.out.println(countOfSmallerNumbersAfterSelf.countSmaller(nums));
        System.out.println(countOfSmallerNumbersAfterSelf.countSmaller2(nums));
    }
}
