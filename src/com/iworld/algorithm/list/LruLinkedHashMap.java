package com.iworld.algorithm.list;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description lru hashmap
 * @date 2022/3/3 15:00
 */
public class LruLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    static LruLinkedHashMap<Integer, Integer> lru;
    
    private int capacity;
    public LruLinkedHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, true);
    }
    
    public LruLinkedHashMap(int capacity, float loadFactor, boolean accessOrder) {
        super(capacity, loadFactor, accessOrder);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > capacity) {
            System.out.println("removeEldestEntry" + eldest.getValue());
            System.out.println("removeEldestEntry===" + lru.values());
        }
        return size() > capacity;
    }
    
    public static void main(String[] args) {
        lru = new LruLinkedHashMap<Integer, Integer>(4, DEFAULT_LOAD_FACTOR, true);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        System.out.println(lru.get(2));
        lru.put(6, 6);
        System.out.println(lru.values());
        System.out.println(lru.get(1));
    }
}
