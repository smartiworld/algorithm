package com.iworld.algorithm.list.ordered;

import java.util.ArrayList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 跳表
 * @date 2022/7/14 11:52
 */
public class SkipListMap<K extends Comparable<K>, V> {
    /**
     * 跳表层数因子 <0.5层加加, >=0.5停止
     */
    private static final double LEVEL_FACTOR = 0.5;
    
    private SkipListMapNode<K, V> head;
    
    private int size;
    
    private int maxLevel;

    static class SkipListMapNode<K extends Comparable<K>, V> {
        K key;
        V value;
        ArrayList<SkipListMapNode<K, V>> nextNode;
        
        public SkipListMapNode(K k, V v) {
            key = k;
            value = v;
            nextNode = new ArrayList<>();
        }
        
        private boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }
        
        public boolean isKeyEquals(K otherKey) {
            return (otherKey == null && key == null) || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }
    
    public SkipListMap() {
        // 调表的头是null
        head = new SkipListMapNode<>(null, null);
        // 默认下一个节点为null
        head.nextNode.add(null);
        size = 0;
        maxLevel = 0;
    }
    
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        // 最底层小于当前key的最右侧节点
        SkipListMapNode<K, V> less = mostRightLessNodeInTree(key);
        // less节点最低层下一个节点
        SkipListMapNode<K, V> lessNextNode = less.nextNode.get(0);
        if(lessNextNode != null && lessNextNode.isKeyEquals(key)) {
            lessNextNode.value = value;
        } else {
            size ++;
            int newNodeLevel = 0;
            // 随机出当前节点的层数
            while (Math.random() < LEVEL_FACTOR) {
                newNodeLevel ++;
            }
            // 高于最高层 头节点要补高
            while (newNodeLevel > maxLevel) {
                head.nextNode.add(null);
                maxLevel ++;
            }
            SkipListMapNode<K, V> newNode = new SkipListMapNode<>(key, value);
            for (int i = 0; i <= newNodeLevel; i ++) {
                newNode.nextNode.add(null);
            }
            int level = maxLevel;
            SkipListMapNode<K, V> pre = head;
            while (level >= 0) {
                pre = mostRightLessNodeInLevel(key, pre, level);
                if (level <= newNodeLevel) {
                    newNode.nextNode.set(level, pre.nextNode.get(level));
                    pre.nextNode.set(level, newNode);
                }
                level--;
            }
        }
    }
    
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        SkipListMapNode<K, V> lessNode = mostRightLessNodeInTree(key);
        return lessNode.nextNode.get(0) != null && lessNode.nextNode.get(0).isKeyEquals(key);
    }
    
    public V get(K key) {
        if (key == null) {
            return null;
        }
        SkipListMapNode<K, V> lessNode = mostRightLessNodeInTree(key);
        SkipListMapNode<K, V> next = lessNode.nextNode.get(0);
        return next != null && next.isKeyEquals(key) ? next.value : null;
    }
    
    public void remove(K key) {
        if (containsKey(key)) {
            size --;
            int level = maxLevel;
            SkipListMapNode<K, V> pre = head;
            // 一层一层处理
            while (level >= 0) {
                // 小于当前key的最右节点 next节点就是要删除的节点
                pre = mostRightLessNodeInLevel(key, pre, level);
                // 要删除的节点
                SkipListMapNode<K, V> next = pre.nextNode.get(level);
                if (next != null && next.isKeyEquals(key)) {
                    // 当前层删除节点 将其当前层next指针指向前当前层next指针
                    pre.nextNode.set(level, next.nextNode.get(level));
                    // set cur next = null
                }
                // 削减顶层 不是最底 如果删除当前节点的当前层后  前面没有可以削减最大高度
                if (level != 0 && pre.nextNode.get(level) == null && pre == head) {
                    pre.nextNode.remove(level);
                    maxLevel--;
                }
                level --;
            }
        }
    }
    
    public K firstKey() {
        return head.nextNode.get(0) != null ? head.nextNode.get(0).key : null;
    }
    
    /**
     * 获取最后位置key
     * @return
     */
    public K lastKey() {
        int level = maxLevel;
        SkipListMapNode<K, V> pre = head;
        while (level >= 0) {
            SkipListMapNode<K, V> next = pre.nextNode.get(level);
            while (next != null) {
                pre = next;
                next = next.nextNode.get(level);
            }
            level --;
        }
        return pre.key;
    }
    
    /**
     * 返回大于当前key的key
     * @param key
     * @return
     */
    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        SkipListMapNode<K, V> lessNode = mostRightLessNodeInTree(key);
        SkipListMapNode<K, V> next = lessNode.nextNode.get(0);
        return next == null ? null : next.key;
    }
    
    /**
     * 返回小于等于当前key的key
     * @param key
     * @return
     */
    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        SkipListMapNode<K, V> lessNode = mostRightLessNodeInTree(key);
        SkipListMapNode<K, V> next = lessNode.nextNode.get(0);
        return next != null && next.isKeyEquals(key) ? next.key : lessNode.key;
    }
    
    public int getSize() {
        return size;
    }
    
    /**
     * 查找小于key的最右侧节点  垂直
     * 最终找到第0层小于当前key的最右侧节点
     * @param key
     * @return
     */
    private SkipListMapNode<K, V> mostRightLessNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        SkipListMapNode<K, V> cur = head;
        int level = maxLevel;
        while (level >= 0) {
            cur = mostRightLessNodeInLevel(key, cur, level--);
        }
        return cur;
    }
    
    /**
     * 在cur的level层找到小于当前key的最右节点 水平
     * @param key
     * @param cur
     * @param level
     * @return
     */
    private SkipListMapNode<K, V> mostRightLessNodeInLevel(K key, SkipListMapNode<K, V> cur, int level) {
        SkipListMapNode<K, V> ans = cur;
        SkipListMapNode<K, V> next = cur.nextNode.get(level);
        while (next != null && next.isKeyLess(key)) {
            ans = next;
            next = next.nextNode.get(level);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        SkipListMap<Integer, String> skipListMap = new SkipListMap<>();
        skipListMap.put(2, "a");
        skipListMap.put(7, "aaaaaaa");
        skipListMap.put(5, "aaaaa");
        skipListMap.put(6, "aaaaaa");
        skipListMap.put(8, "aaaaaaaa");
        skipListMap.put(2, "aa");
        skipListMap.put(1, "a");
        System.out.println(skipListMap.get(6));
        System.out.println(skipListMap.get(2));
        System.out.println(skipListMap.firstKey());
        System.out.println(skipListMap.lastKey());
        skipListMap.remove(5);
        System.out.println(skipListMap.get(5));
        System.out.println(skipListMap.floorKey(4));
        System.out.println(skipListMap.ceilingKey(6));
    }
}
