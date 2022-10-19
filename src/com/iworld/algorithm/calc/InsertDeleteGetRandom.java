package com.iworld.algorithm.calc;

import java.util.HashMap;

/**
 * @author gq.cai
 * @version 1.0
 * @description 380. Insert Delete GetRandom O(1)
 * Medium
 * 6151
 * 321
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * Example 1:
 *
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 *
 * Constraints:
 *
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 105 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * @date 2022/10/19 16:42
 */
public class InsertDeleteGetRandom {
    /**
     * 放入数字增加一个索引 从0开始 维护一个索引map random可以快速取出
     * 一个key->num value-> index  map 维护保存值对应map
     * 删除的时候如果是删除的index为size-1 最后位置数字 直接将
     */
    public class RandomizedSet {
        // 数字 索引map
        private HashMap<Integer, Integer> numIndexMap;
        private HashMap<Integer, Integer> indexNumMap;
        private int size = 0;
        public RandomizedSet() {
            numIndexMap = new HashMap<>();
            indexNumMap = new HashMap<>();
        }
        
        public boolean insert(int val) {
            if (!numIndexMap.containsKey(val)) {
                numIndexMap.put(val, size);
                indexNumMap.put(size++, val);
                return true;
            }
            return false;
        }
        
        public boolean remove(int val) {
            Integer removeIndex = numIndexMap.get(val);
            if (removeIndex != null) {
                int endVal = indexNumMap.get(size - 1);
                indexNumMap.remove(size - 1);
                numIndexMap.remove(val);
                if (removeIndex < size - 1) {
                    numIndexMap.put(endVal, removeIndex);
                    indexNumMap.put(removeIndex, endVal);
                }
                size--;
                return true;
            }
            return false;
        }
        
        public int getRandom() {
            if (size == 0) {
                return -1;
            }
            int index = (int) (Math.random() * size);
            return indexNumMap.get(index);
        }
    }
}
