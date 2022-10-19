package com.iworld.algorithm.array.heap;

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
    
    public int[] topKFrequent(int[] nums, int k) {
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
}
