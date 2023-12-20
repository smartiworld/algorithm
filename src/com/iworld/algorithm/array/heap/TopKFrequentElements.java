package com.iworld.algorithm.array.heap;

import com.iworld.algorithm.util.ArrayUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 347. Top K Frequent Elements
 * Medium
 * 11461
 * 426
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * https://leetcode.com/problems/top-k-frequent-elements/
 * @date 2022/10/18 21:33
 */
public class TopKFrequentElements {
    
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, count + 1);
            }
        }
        PriorityQueue<CountInfo> heap = new PriorityQueue<>(k, new MinComparator());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            CountInfo countInfo = new CountInfo(entry.getValue(), entry.getKey());
            if (heap.size() < k) {
                heap.add(countInfo);
            } else if (heap.peek().count < countInfo.count) {
                heap.poll();
                heap.add(countInfo);
            }
        }
        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().num;
        }
        return ans;
    }
    
    public static class CountInfo {
        private int count;
        private int num;
        public CountInfo(int count, int num) {
            this.count = count;
            this.num = num;
        }
    }
    
    public static class MinComparator implements Comparator<CountInfo> {
        
        @Override
        public int compare(CountInfo c1, CountInfo c2) {
            return c1.count - c2.count;
        }
    }
    
    public static int[] topKFrequentBest(int[] nums, int k) {
        Map<Integer, CountInfo> map = new HashMap<>();
        for (int num : nums) {
            CountInfo countInfo = map.get(num);
            if (countInfo == null) {
                countInfo = new CountInfo(1, num);
                map.put(num, countInfo);
            } else {
                countInfo.count++;
            }
        }
        CountInfo[] countInfos = new CountInfo[map.size()];
        int index = 0;
        for (Map.Entry<Integer, CountInfo> entry : map.entrySet()) {
            countInfos[index++] = entry.getValue();
        }
        CountInfo kthCountInfo = getKthCountInfo(countInfos, index - k);
        int[] result = new int[k];
        int i = 0;
        if (kthCountInfo != null) {
            for (CountInfo countInfo : countInfos) {
                if (countInfo.count >= kthCountInfo.count) {
                    result[i++] = countInfo.num;
                }
            }
        }
        return result;
    }
    
    /**
     * 返回数组第k+1小元素
     * @param countInfos
     * @param k           数组下标计数 第k
     * @return
     */
    public static CountInfo getKthCountInfo(CountInfo[] countInfos, int k) {
        int l = 0;
        int r = countInfos.length - 1;
        while (l <= r) {
           int less = l;
           int big = r;
           int index = less;
           CountInfo countInfo = countInfos[l + (int)(Math.random() * (r - l + 1))];
           while (index <= big) {
               if (countInfos[index].count < countInfo.count) {
                   swap(countInfos, less++, index++);
               } else if (countInfos[index].count > countInfo.count) {
                   swap(countInfos, index, big--);
               } else {
                   index++;
               }
           }
           if (less <= k && k <= big) {
               return countInfos[k];
           } else if (k < less) {
               r = less - 1;
           } else {
               l = big + 1;
           }
        }
        return null;
    }
    
    private static void swap(CountInfo[] countInfos, int l, int r) {
        CountInfo tmp = countInfos[l];
        countInfos[l] = countInfos[r];
        countInfos[r] = tmp;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] ints = topKFrequent(nums, k);
        ArrayUtil.printArray(ints);
        int[] x = topKFrequentBest(nums, k);
        ArrayUtil.printArray(x);
    }
}
