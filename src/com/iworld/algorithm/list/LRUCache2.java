package com.iworld.algorithm.list;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author gq.cai
 * @version 1.0
 * @description lru cache
 * @date 2022/3/3 16:58
 */
public class LRUCache2 {
    
    private int capacity;
    
    private Entry[] nodes;
    
    private Entry head;
    
    private Entry tail;
    
    private int size;
    
    private int length;
    
    public LRUCache2(int capacity) {
        this.capacity = capacity;
        int n = capacity - 1;
        n |= n >>>1;
        n |= n >>>2;
        n |= n >>>4;
        n |= n >>>8;
        n |= n >>>16;
        this.length = (n > Integer.MAX_VALUE) ? Integer.MAX_VALUE : ((n < 0) ? 1 : n + 1);
    }
    
    public int get(int key) {
        if (nodes == null) {
            return -1;
        }
        int index = ((length - 1) & key);
        Entry node = nodes[index];
        int value = -1;
        Entry entry = null;
        while (node != null) {
            if (node.key == key) {
                entry = node;
                value = node.value;
                break;
            }
            node = node.next;
        }
        // 将当前节点放放链表最后
        if (entry != null) {
            moveNodeLast(entry);
        }
        return value;
    }
    
    /**
     * 将当前节点移动到链表尾部
     * @param entry
     */
    private void moveNodeLast(Entry entry) {
        if (entry == tail || entry == null) {
            return;
        }
        Entry before = entry.before, after = entry.after;
        entry.after = null;
        if (before == null) {
            head = after;
            if (after != null) {
                after.before = null;
            }
        } else {
            before.after = after;
            if (after != null) {
                after.before = before;
            }
        }
        if (tail != null) {
            tail.after = entry;
            entry.before = tail;
        }
        tail = entry;
    }
    
    public void put(int key, int value) {
        if (nodes == null) {
            nodes = new Entry[length];
        }
        Entry node;
        int n = nodes.length;
        int index;
        if ((node = nodes[index = ((n - 1) & key)]) == null) {
            nodes[index] = newNode(key, value);
        } else {
            Entry next = node;
            while (next != null) {
                if (key == next.key) {
                    next.value = value;
                    moveNodeLast(next);
                    return ;
                }
                if (next.next == null) {
                    break;
                }
                next = next.next;
            }
            next.next = newNode(key, value);
        }
        // 删除节点最近最不常用
        if (++size > capacity) {
            moverFirstNode();
        }
    }
    
    private void moverFirstNode () {
        if (head != null) {
            Entry entry = head;
            if (nodes == null || nodes.length < 1) {
                return;
            }
            int index = (length - 1) & entry.key;
            // 删除数组中位置
            Entry cur = nodes[index];
            if (cur == entry) {
                nodes[index] = entry.next;
            } else {
                while (cur.next !=null && cur.next != entry) {
                    cur = cur.next;
                }
                if (cur.next == entry) {
                    cur.next = entry.next;
                }
            }
            // 删除链表位置
            Entry after = entry.after;
            entry.after = entry.before = null;
            if (after != null) {
                head = after;
                after.before = null;
            } else {
                head = tail = null;
            }
            size--;
        }
    }
    
    private Entry newNode(int key, int value) {
        Entry node = new Entry(key, value, null);
        putNodeLast(node);
        return node;
    }
    
    private void putNodeLast(Entry node) {
        if (node == null) {
            return;
        }
        if (head == null) {
            head = node;
        } else {
            tail.after = node;
            node.before = tail;
        }
        tail = node;
    }
    
    static class Entry {
        Entry before, after;
        final int key;
        int value;
        Entry next;
    
        Entry(int key, int value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    
        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    //["LRUCache","put","put","put","get","put","put","get","put","put","get","put","get","get","get","put","put","get","put","get"]
    //[[10],[7,28],[7,1],[8,15],[6],[10,27],[8,10],[8],[6,29],[1,9],[6],[10,7],[1],[2],[13],[8,30],[1,5],[1],[13,2],[12]]
    //预期[null,null,null,null,-1,null,null,10,null,null,29,null,9,-1,-1,null,null,5,null,-1]
    //实际[null,null,null,null,-1,null,null,10,null,null,29,null,9,-1,-1,null,null,9,null,-1]
    // [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],
    // [9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],
    // [11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],
    // [8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],
    // [9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],
    // [2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],
    // [1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
    //["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
    //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    //[null,null,null,null,null,4,3,2,1,null,1,2,3,-1,5]
    //[null,null,null,null,4,3,2,-1,null,-1,2,3,-1,5]
    //[null,null,null,null,4,3,2,-1,null,-1,2,3,-1,5,]
    public static void main(String[] args) throws NoSuchMethodException {
        String[] inputMethod = {"put","put","put","put","get","get","get","get","put","get","get","get","get","get"};
        Integer[][] inputArgs = {{1,1},{2,2},{3,3},{4,4},{4},{3},{2},{1},{5,5},{1},{2},{3},{4},{5}};
        Object[] result = new Object[inputArgs.length];
        int i = 0;
        try {
            Class<LRUCache2> lruCacheClass = LRUCache2.class;
            Constructor<LRUCache2> declaredConstructor = lruCacheClass.getDeclaredConstructor(int.class);
            LRUCache2 lruCache = declaredConstructor.newInstance(3);
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
                Object invoke = method.invoke(lruCache, inputArgs[i]);
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
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Entry curNode = head;
        while (curNode != null) {
            stringBuilder.append("{");
            stringBuilder.append(curNode.key);
            stringBuilder.append("====");
            stringBuilder.append(curNode.value);
            stringBuilder.append("}.");
            curNode = curNode.after;
        }
        return "LRUCache{" +
                "nodes=" + stringBuilder +
                '}';
    }
}
