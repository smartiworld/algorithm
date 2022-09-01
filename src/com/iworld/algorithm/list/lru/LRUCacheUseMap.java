package com.iworld.algorithm.list.lru;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 146. LRU Cache
 * Medium
 * 14884
 * 603
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise,
 * add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
 * evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put
 * @date 2022/8/31 13:32
 */
public class LRUCacheUseMap {
    
    private Map<Integer, Entry> map;
    
    private int capacity;
    
    private Entry head;
    
    private Entry tail;
    
    private int size;
    
    public LRUCacheUseMap(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Entry entry = map.get(key);
        if (entry == null) {
            return -1;
        }
        moveEntryLast(entry);
        return entry.val;
    }
    
    public void put(int key, int value) {
        Entry entry = map.get(key);
        if (entry == null) {
            entry = createEntry(key, value);
            map.put(key, entry);
            if (++size > capacity) {
                removeFirst();
            }
        } else {
            entry.val = value;
            moveEntryLast(entry);
        }
        
    }
    
    private void moveEntryLast(Entry entry) {
        if (entry == tail || entry == null) {
            return;
        }
        Entry after = entry.after;
        Entry before = entry.before;
        entry.after = null;
        if (after != null) {
            if (before == null) {
                head = after;
                after.before = null;
            } else {
                before.after = after;
                after.before = before;
            }
            tail.after = entry;
            entry.before = tail;
            tail = entry;
        }
    }
    
    private void removeFirst() {
        if (head != null) {
            Entry after = head.after;
            // 删除hashmap
            map.remove(head.key);
            // 删除链表
            head.after = null;
            if (after != null) {
                after.before = null;
                head = after;
            } else {
                head = null;
                tail = null;
            }
            size--;
        }
    }
    
    private Entry createEntry(int key, int value) {
        Entry newEntry = new Entry(key, value);
        if (tail == null) {
            head = newEntry;
        } else {
            tail.after = newEntry;
            newEntry.before = tail;
        }
        tail = newEntry;
        return newEntry;
    }
    
    static class Entry {
        int key;
        int val;
        Entry before;
        Entry after;
        Entry(int key, int value) {
            this.key = key;
            this.val = value;
        }
        Entry(int key, int value, Entry before, Entry after) {
            this.key = key;
            this.val = value;
            this.before = before;
            this.after = after;
        }
    }
    
    //["LRUCache","put","put","get","put","get","put","get","get","get"]
    //[[2],       [1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
    //Output
    //[null,null,null,1,null,-1,null,1,-1,4]
    //Expected
    //[null,null,null,1,null,-1,null,-1,3,4]
    public static void main(String[] args) {
        //String[] inputMethod = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
        String[] inputMethod = {"put","put","get","put","get","put","get","get","get"};
        //Integer[][] inputArgs = {{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};
        Integer[][] inputArgs = {{1,1},{2,2},{1},{3,3},{2},{4,4},{1},{3},{4}};
        Object[] result = new Object[inputArgs.length];
        int i = 0;
        try {
            Class<LRUCacheUseMap> lruCacheClass = LRUCacheUseMap.class;
            Constructor<LRUCacheUseMap> declaredConstructor = lruCacheClass.getDeclaredConstructor(int.class);
            LRUCacheUseMap lruCacheUseMap = declaredConstructor.newInstance(2);
            for (i = 0; i < inputMethod.length; i++) {
                if (i == 48) {
                    System.out.println();
                }
                Method method = null;
                if ("get".equals(inputMethod[i])) {
                    method = lruCacheClass.getDeclaredMethod(inputMethod[i], int.class);
                } else {
                    method = lruCacheClass.getDeclaredMethod(inputMethod[i], int.class, int.class);
                }
                Object invoke = method.invoke(lruCacheUseMap, inputArgs[i]);
                result[i] = invoke;
            }
            System.out.print("[");
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[j] + ",");
            }
            System.out.println("]");
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }
}
